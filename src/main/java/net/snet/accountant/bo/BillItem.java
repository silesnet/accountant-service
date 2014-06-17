package net.snet.accountant.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.json.JsonSnakeCase;

@JsonSnakeCase
public class BillItem {

	@JsonProperty
	private long billId;
	private String text;
	private float amount;
	private int price;
	private boolean isDisplayUnit;

	public BillItem() {
	}

	public BillItem(long billId, String text, float amount, int price, boolean isDisplayUnit) {
		this.billId = billId;
		this.text = text;
		this.amount = amount;
		this.price = price;
		this.isDisplayUnit = isDisplayUnit;
	}

	public long getBillId() {
		return billId;
	}

	public void setBillId(long billId) {
		this.billId = billId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@JsonProperty("isDisplayUnit")
	public boolean isDisplayUnit() {
		return isDisplayUnit;
	}

	@JsonProperty("isDisplayUnit")
	public void setDisplayUnit(boolean isDisplayUnit) {
		this.isDisplayUnit = isDisplayUnit;
	}
}
