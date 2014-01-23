package net.snet.accountant.bo;

public class BillItem {

    private long bill_id;
    private String text;
    private float amount;
    private int price;
    private boolean is_display_unit;

    public BillItem() {
    }

    public BillItem(long bill_id, String text, float amount, int price, boolean is_display_unit) {
        this.bill_id = bill_id;
        this.text = text;
        this.amount = amount;
        this.price = price;
        this.is_display_unit = is_display_unit;
    }

    public Long getBill_id() {
        return bill_id;
    }

    public void setBill_id(long bill_id) {
        this.bill_id = bill_id;
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

    public boolean isIs_display_unit() {
        return is_display_unit;
    }

    public void setIs_display_unit(boolean is_display_unit) {
        this.is_display_unit = is_display_unit;
    }
}
