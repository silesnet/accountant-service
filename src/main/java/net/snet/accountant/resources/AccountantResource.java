package net.snet.accountant.resources;

import com.yammer.metrics.annotation.Timed;
import net.snet.accountant.bo.*;
import net.snet.accountant.dao.BillDAO;
import net.snet.accountant.dao.CustomerDAO;
import net.snet.accountant.util.PATCH;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Path("/invoicings")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountantResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountantResource.class);
	private CustomerDAO customerDAO;
	private BillDAO billDAO;
	private Invoices invoices;


	public AccountantResource(DBI dbi) {
		this.customerDAO = dbi.onDemand(CustomerDAO.class);
		this.billDAO = dbi.onDemand(BillDAO.class);
	}

	@PATCH
	@Path("/{invoicingId}")
	@Timed(name = "post-requests")
	public Response synchonizedBills(InvoicesPatch list) {
		LOGGER.debug("synchronized bills called");

		for (BillPatch bill : list.getInvoices()) {

			billDAO.updateBillTime(bill.getId(), bill.getUpdates().get(0).getSynchronizedOn());
		}
		return Response.ok().build();
	}

	@GET
	@Produces({"application/json; charset=UTF-8"})
	@Path("/{invoicingId}/invoices")
	@Timed(name = "get-requests")
	public Invoices getInvoices(@PathParam("invoicingId") long invoicing_id) {
		LOGGER.debug("invoices called");

		invoices = new Invoices();

		Iterator<Bill> bills = billDAO.findAllBillsByInvoicingId(invoicing_id);
		List<Bill> billsList = new ArrayList<Bill>();
		while (bills.hasNext()) {
			billsList.add(bills.next());
		}

		for (Bill bill : billsList) {
			bill.setLines(getBillItems(bill.getId()));
			bill.setCustomer(getCustomer(bill.getCustomerId()));
			invoices.addInvoice(bill);
		}
		return invoices;
	}

	/**
	 * Return an ArrayList with BillItems
	 *
	 * @param id Bill Id
	 * @return ArrayList with BillItems
	 */
	private ArrayList<BillItem> getBillItems(long id) {
		ArrayList<BillItem> retBillItems = new ArrayList<BillItem>();

		Iterator<BillItem> billItems = billDAO.findBillItemById(id);

		while (billItems.hasNext()) {
			retBillItems.add(billItems.next());
		}
		return retBillItems;
	}

	/**
	 * Return a Customer object
	 *
	 * @param id Customer Id
	 * @return Customer object
	 */
	private Customer getCustomer(long id) {
		Customer retCustomer = customerDAO.findById(id);
		return retCustomer;
	}
}
