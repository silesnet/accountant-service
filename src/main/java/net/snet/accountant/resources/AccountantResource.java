package net.snet.accountant.resources;

import com.yammer.metrics.annotation.Timed;
import net.snet.accountant.bo.Invoice;
import net.snet.accountant.bo.Invoices;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Path("/invoicings/{invoicingId}/invoices")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountantResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountantResource.class);
    private DBI dbiConn;
    private Invoices gInvoices;

    public AccountantResource(DBI dbi) {
        dbiConn = dbi;
    }

    @GET
    @Timed(name = "get-requests")
    public Invoices getInvoice(@PathParam("invoicingId") int id) {
        LOGGER.debug("invoices called");
        gInvoices = new Invoices();
        for (int i = 0; i < id; i++) {
            Invoice test = new Invoice();
            test.setInvId(12345);
            test.setInvDescription("test invoice");
            gInvoices.addInvoice(test);
        }
        return gInvoices;
    }
}
