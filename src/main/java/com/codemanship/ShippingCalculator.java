package com.codemanship;

public class ShippingCalculator {
    private final Regions regionsApi;

    public ShippingCalculator(Regions regionsApi) {
        this.regionsApi = regionsApi;
    }

    public double shippingCost(Order order) {
        Region region = regionsApi.getRegionOf(order.country());
        return switch (region) {
            case UK -> order.total() < 100.0 ? 5.99 : 0.0;
            case EU -> order.total() < 100.0 ? 9.99 : 5.99;
            case OTHER -> order.total() < 100.0 ? 12.99 : 9.99;
        };
    }
}
