package com.frontiertechnologypartners.beganretail.model;

public class SalesItem {
    private String price;
    private String amount;
    private String quantity;
    public SalesItem() {
    }

    public SalesItem(String price, String amount, String quantity) {
        this.price = price;
        this.amount = amount;
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
