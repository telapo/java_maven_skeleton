package com.codemanship;

import java.util.List;

public class Order {
    public <T> Order(List<T> items) {
    }

    public List<Item> items() {
        return List.of(new Item());
    }

    public void add(Product product, int quantity) throws InsufficientStockException {
        if(quantity > product.available()){
            String message = "Insufficient stock of " + product.description() + ". Only " + product.available() + " currently available.";
            throw new InsufficientStockException(message);
        }
    }
}
