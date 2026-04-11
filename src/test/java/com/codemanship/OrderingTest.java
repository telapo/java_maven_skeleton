package com.codemanship;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderingTest {

    @Test
    void addHoldOnItem() {
        Product product = new Product(327, 7, 0);
        Order order = new Order(List.of());

        order.add(product, 1);

        assertEquals(1, product.onHoldQuantity());
    }

    @Test
    void addingItemWithSufficientStock() {
        Product product = new Product(327, 7, 0);
        Order order = new Order(List.of());

        order.add(product, 1);

        assertEquals(1, order.items().size());
        assertEquals(product.id(), order.items().get(0).product().id());
        assertEquals(1, order.items().get(0).quantity());
    }
}
