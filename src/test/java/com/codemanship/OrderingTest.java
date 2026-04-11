package com.codemanship;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void addingItemWithInsufficientStockDueToHold() {

        Product product = new Product(327, 2, 1, "Ibanez Tube Screamer");
        Order order = createEmptyOrder();

        InsufficientStockException ex = assertThrows(InsufficientStockException.class, () -> order.add(product, 2));
        assertEquals("Insufficient stock of Ibanez Tube Screamer. Only 1 currently available.", ex.getMessage());
    }

    @Test
    void removeItemPlacedOnHold() throws InsufficientStockException {
        Product product = new Product(327, 7, 0, null);
        Order order = createEmptyOrder();
        order.add(product, 2);
        order.remove(product, 2);

        assertEquals(0, product.onHoldQuantity());
    }

    @Test
    void removeItemFromOrder() throws InsufficientStockException {
        Product product = new Product(327, 7, 0, null);
        Order order = createEmptyOrder();
        order.add(product, 2);
        order.remove(product, 2);

        assertTrue(order.items().isEmpty());
    }

    @Test
    void orderTotalOfEmptyOrderIsZero() {
        Order order = createEmptyOrder();
        assertEquals(0.0, order.total(), 0);
    }

    @Test
    void orderTotaOfOrderWithOneItem() throws InsufficientStockException {
        Product product = new Product(327, 7, 0, null);
        Order order = createEmptyOrder();

        order.add(product, 1);

        assertEquals(159.95, order.total(), 0);
    }
    private static Product createProduct() {
        Product product = new Product(327, 7, 0, null);
        return product;
    }

    private static Order createEmptyOrder() {
        Order order = new Order(new ArrayList<>());
        return order;
    }
}
