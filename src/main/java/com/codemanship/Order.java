package com.codemanship;

import java.util.List;

public class Order {
    private final List<Item> items;
    private final String country;
    private OrderStatus status;

    public Order(List<Item> items, String country) {
        this.items = items;
        this.country = country;
    }

    public List<Item> items() {
        return items;
    }

    public String country() {
        return country;
    }

    public void add(Product product, int quantity) throws InsufficientStockException {
        if (quantity > product.available()) {
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
        return items.isEmpty() ? 0 : items.stream().map(i -> i.product().price() * i.quantity()).reduce(Double::sum).get();
    }

    public void confirm() {
        for (Item i : items) {
            i.product().decreaseOnHoldQuantity(i.quantity());
            i.product().decreaseInStockQuantity(i.quantity());
        }
        status = OrderStatus.CONFIRMED;
    }

    public OrderStatus status() {
        return status;
    }
}
