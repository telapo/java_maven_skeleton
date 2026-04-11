package com.codemanship;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderingTest {

    @Test
    void addingItemWithSufficientStock(){
        Product product = new Product(327, 7, 0);
        Order order = new Order(List.of());

        order.add(product, 1);

        assertEquals(1, product.onHoldQuantity());
        assertEquals(1, order.items().size());
        assertEquals(product.id(), order.items().get(0).product().id());
        assertEquals(1, order.items().get(0).quantity());
    }
}
