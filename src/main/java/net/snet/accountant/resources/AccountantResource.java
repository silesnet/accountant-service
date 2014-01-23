package net.snet.accountant.resources;

import com.yammer.metrics.annotation.Timed;
import net.snet.accountant.bo.Bill;
import net.snet.accountant.bo.BillItem;
import net.snet.accountant.bo.Customer;
import net.snet.accountant.bo.Invoices;
import net.snet.accountant.dao.BillDAO;
import net.snet.accountant.dao.BillItemDAO;
import net.snet.accountant.dao.CustomerDAO;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

@Path("/invoicings/{invoicingId}/invoices")
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

    @GET
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
