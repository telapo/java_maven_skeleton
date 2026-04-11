package com.codemanship;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderingTest {

    @Test
    void addHoldOnItem() throws InsufficientStockException {
        Product product = createProduct();
        Order order = createEmptyOrder();

        order.add(product, 1);

        assertEquals(1, product.onHoldQuantity());
    }

    @Test
    void addingItemWithSufficientStock() throws InsufficientStockException {
        Product product = createProduct();
        Order order = createEmptyOrder();

        order.add(product, 1);

        assertEquals(1, order.items().size());
        assertEquals(product.id(), order.items().get(0).product().id());
        assertEquals(1, order.items().get(0).quantity());
    }

    @Test
    void addingItemWithInsufficientStock(){
        Product product = new Product(327, 1, 0, "Ibanez Tube Screamer");
        Order order = createEmptyOrder();

        InsufficientStockException ex = assertThrows(InsufficientStockException.class, () -> order.add(product, 2));
        assertEquals("Insufficient stock of Ibanez Tube Screamer. Only 1 currently available.", ex.getMessage());
    }

    private static Product createProduct() {
        Product product = new Product(327, 7, 0, null);
        return product;
    }

    private static Order createEmptyOrder() {
        Order order = new Order(List.of());
        return order;
    }
}
