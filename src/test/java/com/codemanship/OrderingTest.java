package com.codemanship;

import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderingTest {

    @Test
    void addHoldOnItem() {
        Product product = createProduct();
        Order order = createEmptyOrder();

        order.add(product, 1);

        assertEquals(1, product.onHoldQuantity());
    }

    @Test
    void addingItemWithSufficientStock() {
        Product product = createProduct();
        Order order = createEmptyOrder();

        order.add(product, 1);

        assertEquals(1, order.items().size());
        assertEquals(product.id(), order.items().get(0).product().id());
        assertEquals(1, order.items().get(0).quantity());
    }

    private static Product createProduct() {
        Product product = new Product(327, 7, 0);
        return product;
    }

    private static Order createEmptyOrder() {
        Order order = new Order(List.of());
        return order;
    }
}
