package net.snet.accountant.resources;

import com.yammer.metrics.annotation.Timed;
import net.snet.accountant.bo.*;
import net.snet.accountant.dao.BillDAO;
import net.snet.accountant.dao.BillItemDAO;
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

@Path("/invoicings")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountantResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountantResource.class);
    private DBI dbi;
    private CustomerDAO customerDAO;
    private BillItemDAO billItemDAO;
    private BillDAO billDAO;
    private Invoices gInvoices;

    public AccountantResource(DBI dbi) {
        this.dbi = dbi;
    }

    @PATCH
    @Path("/{invoicingId}")
    @Timed(name = "post-requests")
    public Response synchonizedBills(InvoicesPatch list) {
        LOGGER.debug("synchronized bills called");

        billDAO = dbi.onDemand(BillDAO.class);


        for (BillPatch bill : list.getInvoices()) {

            billDAO.updateTime(bill.getId(), bill.getUpdates().get(0).getSynchronized_on());
        }

        billDAO.close();
        return Response.ok().build();
    }

    @GET
    @Path("/{invoicingId}/invoices")
    @Timed(name = "get-requests")
    public Invoices getInvoice(@PathParam("invoicingId") long invoicing_id) {
        LOGGER.debug("invoices called");

        billDAO = dbi.onDemand(BillDAO.class);

        gInvoices = new Invoices();

        // Get all bills to billing period
        /*
        Iterator<Integer> bills = billDAO.findAllBills(date);

        while (bills.hasNext()) {
            Bill tmpBill = billDAO.findById(bills.next());
            tmpBill.setLines(getBillItems(tmpBill.getId()));
            tmpBill.setCustomer(getCustomer(tmpBill.getCustomer_id()));
            gInvoices.addInvoice(tmpBill);
        }
        */

        // Get all bills to invoicing_id
        Iterator<Bill> bills = billDAO.findAllBillsByInvoicingId(invoicing_id);

        while (bills.hasNext()) {
            Bill tmpBill = bills.next();
            tmpBill.setLines(getBillItems(tmpBill.getId()));
            tmpBill.setCustomer(getCustomer(tmpBill.getCustomer_id()));
            gInvoices.addInvoice(tmpBill);
        }


        billDAO.close();
        return gInvoices;
    }

    private ArrayList<BillItem> getBillItems(long id) {
        billItemDAO = dbi.onDemand(BillItemDAO.class);

        ArrayList<BillItem> retBillItems = new ArrayList<BillItem>();

        Iterator<Integer> billItems = billItemDAO.findAllBillItems(id);

        while (billItems.hasNext()) {
            retBillItems.add(billItemDAO.findById(billItems.next()));
        }

        billItemDAO.close();
        return retBillItems;
    }

    private Customer getCustomer(long id) {
        customerDAO = dbi.onDemand(CustomerDAO.class);
        Customer retCustomer = customerDAO.findById(id);
        customerDAO.close();
        return retCustomer;
    }
}
