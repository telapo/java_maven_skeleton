package com.codemanship;

import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Locale;

import static com.codemanship.Region.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShippingCalculatorTest {

    @Test
    void ukUnder100PoundsTest() {
        Order order = new Order(new ArrayList<>(), "UK");
        ShippingCalculator shippingCalculator = new ShippingCalculator(mockRegionsAPI());

        assertEquals(5.99, shippingCalculator.shippingCost(order), 0.0);
    }

    @Test
    void ukAbove100PoundsTest() throws InsufficientStockException {
        Order order = new Order(new ArrayList<>(), "UK");
        order.add(new Product(125, 10, 0, null, 25.0), 6);
        ShippingCalculator shippingCalculator = new ShippingCalculator(mockRegionsAPI());

        assertEquals(0.0, shippingCalculator.shippingCost(order), 0.0);
    }

    @Test
    void euUnder100PoundsTest() {
        Order order = new Order(new ArrayList<>(), "Spain");
        ShippingCalculator shippingCalculator = new ShippingCalculator(mockRegionsAPI());

        assertEquals(9.99, shippingCalculator.shippingCost(order), 0.0);
    }

    @Test
    void euAbove100PoundsTest() throws InsufficientStockException {
        Order order = new Order(new ArrayList<>(), "Spain");
        order.add(new Product(125, 10, 0, null, 25.0), 6);
        ShippingCalculator shippingCalculator = new ShippingCalculator(mockRegionsAPI());

        assertEquals(5.99, shippingCalculator.shippingCost(order), 0.0);
    }

    @Test
    void otherUnder100PoundsTest() {
        Order order = new Order(new ArrayList<>(), "Japan");
        ShippingCalculator shippingCalculator = new ShippingCalculator(mockRegionsAPI());

        assertEquals(12.99, shippingCalculator.shippingCost(order), 0.0);
    }

    @Test
    void otherAbove100PoundsTest() throws InsufficientStockException {
        Order order = new Order(new ArrayList<>(), "Japan");
        order.add(new Product(125, 10, 0, null, 25.0), 6);
        ShippingCalculator shippingCalculator = new ShippingCalculator(mockRegionsAPI());

        assertEquals(9.99, shippingCalculator.shippingCost(order), 0.0);
    }

    private static @NonNull Regions mockRegionsAPI() {
        return country -> switch (country.toUpperCase(Locale.ROOT)) {
            case "UK" -> UK;
            case "SPAIN" -> EU;
            default -> OTHER;
        };
    }
}
