package com.codemanship;

import java.util.List;

public class Order {
    private final List<Item> items;

    public Order(List<Item> items) {
        this.items = items;
    }

    public List<Item> items() {
        return items;
    }

    public void add(Product product, int quantity) throws InsufficientStockException {
        if(quantity > product.available()){
            String message = "Insufficient stock of " + product.description() + ". Only " + product.available() + " currently available.";
            throw new InsufficientStockException(message);
        }
        product.increaseOnHoldQuantity(quantity);
        items.add(new Item(product, quantity));
    }

    public void remove(Product product, int quantity) {
        product.decreaseOnHoldQuantity(quantity);
        items.removeIf(i -> i.product().equals(product));
    }

    public double total() {
        return 0;
    }
}
