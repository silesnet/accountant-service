package net.snet.accountant.bo;

import java.util.ArrayList;

public class InvoicesPatch {

    private ArrayList<BillPatch> invoices;

    public ArrayList<BillPatch> getInvoices() {
        return invoices;
    }

    public void setInvoices(ArrayList<BillPatch> invoices) {
        this.invoices = invoices;
    }
}
