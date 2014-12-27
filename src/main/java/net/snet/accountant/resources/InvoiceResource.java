package net.snet.accountant.resources;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yammer.metrics.annotation.Timed;
import net.snet.accountant.bo.Bill;
import net.snet.accountant.bo.BillItem;
import net.snet.accountant.dao.BillDAO;
import net.snet.accountant.dao.CustomerDAO;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Path("/invoices")
@Consumes({"application/json; charset=UTF-8"})
@Produces({"application/json; charset=UTF-8"})
public class InvoiceResource {
	private final static Logger LOGGER = LoggerFactory.getLogger(InvoiceResource.class);
	private final Pattern LEDGER_IMPORT = Pattern.compile("ledger-(\\w\\w)-import");
	private final Map<String, Integer> COUNTRY_MAP = ImmutableMap.of("cs", 10, "pl", 20, "sk", 30);

	private final CustomerDAO customerReporitory;
	private final BillDAO invoiceRepository;

	public InvoiceResource(DBI dbi) {
		this.customerReporitory = dbi.onDemand(CustomerDAO.class);
		this.invoiceRepository = dbi.onDemand(BillDAO.class);
	}

	@GET
	@Timed(name = "get-request")
	public Map<String, Object> getInvoicesByQuery(@QueryParam("qn") Optional<String> queryName) {
		final List<Bill> invoices = Lists.newArrayList();
		if (queryName.isPresent()) {
			final String query = queryName.get().toLowerCase();
			LOGGER.debug("querying invoices with named query '{}'", query);
			final Matcher ledgerMatcher = LEDGER_IMPORT.matcher(query);
			if (ledgerMatcher.matches()) {
				final String country = ledgerMatcher.group(1);
				if (COUNTRY_MAP.containsKey(country)) {
					final long countryId = COUNTRY_MAP.get(country);
					final List<Bill> bills = invoiceRepository.findNotSynchronizedBillsByCountry(countryId);
					for (Bill bill : bills) {
						final Iterator<BillItem> items = invoiceRepository.findBillItemById(bill.getId());
						bill.setLines(Lists.newArrayList(items));
						bill.setCustomer(customerReporitory.findById(bill.getCustomerId()));
						invoices.add(bill);
					}
				}
			}
		}
		return ImmutableMap.of("invoices", (Object) invoices);
	}

	@PUT
	@Timed(name = "put-requests")
	public Response updateCustomers(Map<String, Object> updates) {
		Map<String, Object> response = Maps.newHashMap();
		List<Map> updated = Lists.newArrayList();
		try {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> invoiceUpdates = (List<Map<String, Object>>) updates.get("invoices");
			for (Map<String, Object> invoiceUpdate : invoiceUpdates) {
				try {
					final String number = invoiceUpdate.get("id").toString();
					final DateTime synchronizedOn = DateTime.parse(invoiceUpdate.get("synchronized").toString());
					LOGGER.debug("updating invoice '{}' synchronization time stamp '{}'", number, synchronizedOn);
					final int changed = invoiceRepository.updateInvoiceSynchronizedTime(number, new Timestamp(synchronizedOn.toDate().getTime()));
					if (changed != 1) {
						throw new RuntimeException("invoice '" + number + "' not found, cannot update");
					}
					LOGGER.debug("invoice '{}' was synchronized on '{}'", number, synchronizedOn);
				} catch (Exception e) {
					LOGGER.error(e.getMessage());
					invoiceUpdate.put("errors", ImmutableList.of(ImmutableMap.of("message", "" + e.getMessage())));
				}
				updated.add(invoiceUpdate);
			}
		} catch (Exception e) {
			response.put("errors", ImmutableList.of(ImmutableMap.of("message", "" + e.getMessage())));
			throw new WebApplicationException(
					Response.status(Response.Status.BAD_REQUEST)
							.entity(response)
							.build());
		}
		response.put("invoices", updated);
		return Response.ok(response).build();
	}

}
