package net.snet.accountant.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.json.JsonSnakeCase;

import java.sql.Timestamp;
import java.util.ArrayList;

@JsonSnakeCase
public class Bill {

	private long id;
	private String number;
	@JsonProperty
	private Timestamp billingDate;
	@JsonProperty
	private Timestamp purgeDate;
	@JsonProperty
	private long customerId;
	@JsonProperty
	private Timestamp periodFrom;
	@JsonProperty
	private Timestamp periodTo;
	private int vat;
	@JsonProperty
	private String hashCode;
	private boolean isConfirmed;
	private boolean isSent;
	private boolean isDelivered;
	private boolean isArchived;
	@JsonProperty
	private boolean deliverByMail;
	@JsonProperty
	private String customerName;
	@JsonProperty
	private long invoicingId;
	@JsonProperty("synchronized")
	private Timestamp synchronizedBill;
	private ArrayList<BillItem> lines;
	private Customer customer;

	public Bill() {
	}

	public Bill(long id, String number, Timestamp billingDate, Timestamp purgeDate, long customerId, Timestamp periodFrom, Timestamp periodTo, int vat, String hashCode, boolean isConfirmed, boolean isSent, boolean isDelivered, boolean isArchived, boolean deliverByMail, String customerName, long invoicingId, Timestamp synchronizedBill) {
		this.id = id;
		this.number = number;
		this.billingDate = billingDate;
		this.purgeDate = purgeDate;
		this.customerId = customerId;
		this.periodFrom = periodFrom;
		this.periodTo = periodTo;
		this.vat = vat;
		this.hashCode = hashCode;
		this.isConfirmed = isConfirmed;
		this.isSent = isSent;
		this.isDelivered = isDelivered;
		this.isArchived = isArchived;
		this.deliverByMail = deliverByMail;
		this.customerName = customerName;
		this.invoicingId = invoicingId;
		this.synchronizedBill = synchronizedBill;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Timestamp getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(Timestamp billingDate) {
		this.billingDate = billingDate;
	}

	public Timestamp getPurgeDate() {
		return purgeDate;
	}

	public void setPurgeDate(Timestamp purgeDate) {
		this.purgeDate = purgeDate;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public Timestamp getPeriodFrom() {
		return periodFrom;
	}

	public void setPeriodFrom(Timestamp periodFrom) {
		this.periodFrom = periodFrom;
	}

	public Timestamp getPeriodTo() {
		return periodTo;
	}

	public void setPeriodTo(Timestamp periodTo) {
		this.periodTo = periodTo;
	}

	public int getVat() {
		return vat;
	}

	public void setVat(int vat) {
		this.vat = vat;
	}

	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	@JsonProperty("isConfirmed")
	public boolean isConfirmed() {
		return isConfirmed;
	}

	@JsonProperty("isConfirmed")
	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	@JsonProperty("isSent")
	public boolean isSent() {
		return isSent;
	}

	@JsonProperty("isSent")
	public void setSent(boolean isSent) {
		this.isSent = isSent;
	}

	@JsonProperty("isDelivered")
	public boolean isDelivered() {
		return isDelivered;
	}

	@JsonProperty("isDelivered")
	public void setDelivered(boolean isDelivered) {
		this.isDelivered = isDelivered;
	}

	@JsonProperty("isArchived")
	public boolean isArchived() {
		return isArchived;
	}

	@JsonProperty("isArchived")
	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}

	public boolean isDeliverByMail() {
		return deliverByMail;
	}

	public void setDeliverByMail(boolean deliverByMail) {
		this.deliverByMail = deliverByMail;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public long getInvoicingId() {
		return invoicingId;
	}

	public void setInvoicingId(long invoicingId) {
		this.invoicingId = invoicingId;
	}

	public Timestamp getSynchronizedBill() {
		return synchronizedBill;
	}

	public void setSynchronizedBill(Timestamp synchronizedBill) {
		this.synchronizedBill = synchronizedBill;
	}

	public ArrayList<BillItem> getLines() {
		return lines;
	}

	public void setLines(ArrayList<BillItem> lines) {
		this.lines = lines;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
