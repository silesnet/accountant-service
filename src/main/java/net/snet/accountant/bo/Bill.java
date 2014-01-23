package net.snet.accountant.bo;


import java.sql.Timestamp;
import java.util.ArrayList;

public class Bill {

    private long id;
    private String number;
    private Timestamp billing_date;
    private Timestamp purge_date;
    private long customer_id;
    private Timestamp period_from;
    private Timestamp period_to;
    private int vat;
    private String hash_code;
    private boolean is_confirmed;
    private boolean is_sent;
    private boolean is_delivered;
    private boolean is_archived;
    private boolean deliver_by_mail;
    private String customer_name;
    private long invoicing_id;
    private Timestamp synchronized_bill;
    private ArrayList<BillItem> lines;
    private Customer customer;

    public Bill() {
    }

    public Bill(long id, String number, Timestamp billing_date, Timestamp purge_date, long customer_id, Timestamp period_from, Timestamp period_to, int vat, String hash_code, boolean is_confirmed, boolean is_sent, boolean is_delivered, boolean is_archived, boolean deliver_by_mail, String customer_name, long invoicing_id, Timestamp synchronized_bill) {
        this.id = id;
        this.number = number;
        this.billing_date = billing_date;
        this.purge_date = purge_date;
        this.customer_id = customer_id;
        this.period_from = period_from;
        this.period_to = period_to;
        this.vat = vat;
        this.hash_code = hash_code;
        this.is_confirmed = is_confirmed;
        this.is_sent = is_sent;
        this.is_delivered = is_delivered;
        this.is_archived = is_archived;
        this.deliver_by_mail = deliver_by_mail;
        this.customer_name = customer_name;
        this.invoicing_id = invoicing_id;
        this.synchronized_bill = synchronized_bill;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String invDescription) {
        this.number = invDescription;
    }

    public Timestamp getBilling_date() {
        return billing_date;
    }

    public void setBilling_date(Timestamp billing_date) {
        this.billing_date = billing_date;
    }

    public Timestamp getPurge_date() {
        return purge_date;
    }

    public void setPurge_date(Timestamp purge_date) {
        this.purge_date = purge_date;
    }

    public Timestamp getPeriod_from() {
        return period_from;
    }

    public void setPeriod_from(Timestamp period_from) {
        this.period_from = period_from;
    }

    public Timestamp getPeriod_to() {
        return period_to;
    }

    public void setPeriod_to(Timestamp period_to) {
        this.period_to = period_to;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    public String getHash_code() {
        return hash_code;
    }

    public void setHash_code(String hash_code) {
        this.hash_code = hash_code;
    }

    public boolean isIs_confirmed() {
        return is_confirmed;
    }

    public void setIs_confirmed(boolean is_confirmed) {
        this.is_confirmed = is_confirmed;
    }

    public boolean isIs_sent() {
        return is_sent;
    }

    public void setIs_sent(boolean is_sent) {
        this.is_sent = is_sent;
    }

    public boolean isIs_delivered() {
        return is_delivered;
    }

    public void setIs_delivered(boolean is_delivered) {
        this.is_delivered = is_delivered;
    }

    public boolean isIs_archived() {
        return is_archived;
    }

    public void setIs_archived(boolean is_archived) {
        this.is_archived = is_archived;
    }

    public boolean isDeliver_by_mail() {
        return deliver_by_mail;
    }

    public void setDeliver_by_mail(boolean deliver_by_mail) {
        this.deliver_by_mail = deliver_by_mail;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public Timestamp getSynchronized_bill() {
        return synchronized_bill;
    }

    public void setSynchronized_bill(Timestamp synchronized_bill) {
        this.synchronized_bill = synchronized_bill;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public Long getInvoicing_id() {
        return invoicing_id;
    }

    public void setInvoicing_id(long invoicing_id) {
        this.invoicing_id = invoicing_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<BillItem> getLines() {
        return lines;
    }

    public void setLines(ArrayList<BillItem> lines) {
        this.lines = lines;
    }
}
