package com.codemanship;

public class Product {
    private int inStock;
    private int onHold;
    private double price;

    public Product(int id, int inStock, int onHold, String description, double price) {
        this.inStock = inStock;
        this.onHold = onHold;
        this.price = price;
    }

    public int onHoldQuantity() {
        return onHold;
    }

    public int id() {
        return 0;
    }

    public double price() {
        return price;
    }

    public int inStock() {
        return inStock;
    }

    public String description() {
        return "Ibanez Tube Screamer";
    }

    public int available() {
        return inStock - onHold;
    }

    public void increaseOnHoldQuantity(int quantity) {
        onHold = onHold + quantity;
    }

    public void decreaseOnHoldQuantity(int quantity) {
        onHold = onHold - quantity;
    }

    public void decreaseInStockQuantity(int quantity) {
        inStock = inStock - quantity;
    }
}
