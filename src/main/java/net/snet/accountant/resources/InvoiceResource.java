package net.snet.accountant.resources;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.yammer.metrics.annotation.Timed;
import net.snet.accountant.bo.Bill;
import net.snet.accountant.bo.BillItem;
import net.snet.accountant.dao.BillDAO;
import net.snet.accountant.dao.CustomerDAO;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
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
}
