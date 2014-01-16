package net.snet.accountant.bo;

import java.util.ArrayList;

public class Invoices {

    private ArrayList<Invoice> invoices;

    public Invoices() {
        this.invoices = new ArrayList<Invoice>();
    }

    public ArrayList<Invoice> getInvoices() {
        return invoices;
    }

    public void addInvoice(Invoice invoice) {
        this.invoices.add(invoice);
    }

}
