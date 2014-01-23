package net.snet.accountant.bo;

import java.util.ArrayList;

public class Invoices {

    private ArrayList<Bill> invoices;

    public Invoices() {
        this.invoices = new ArrayList<Bill>();
    }

    public ArrayList<Bill> getInvoices() {
        return invoices;
    }

    public void addInvoice(Bill bill) {
        this.invoices.add(bill);
    }

}
