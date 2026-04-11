package com.codemanship;

public class Product {
    private final int inStock;
    private int onHold;

    public Product(int id, int inStock, int onHold, String description) {
        this.inStock = inStock;
        this.onHold = onHold;
    }

    public int onHoldQuantity() {
        return onHold;
    }

    public int id() {
        return 0;
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
}
