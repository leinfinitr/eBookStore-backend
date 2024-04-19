package com.example.ebookstore.serviceimpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceServiceImplTest {
    private PriceServiceImpl priceService;

    @BeforeEach
    void setUp() {
        priceService = new PriceServiceImpl();
    }

    @Test
    void calculatesProfitWhenPricesAreGreaterThanCosts() {
        double[] prices = {10, 20, 30};
        double[] costs = {5, 10, 15};
        int[] quantities = {1, 2, 3};
        double lastYearProfit = 100;

        double result = priceService.ifContinuedInvestment(prices, costs, quantities, lastYearProfit);

        assertEquals(70, result);
    }

    @Test
    void calculatesProfitWhenPricesAreLessThanCosts() {
        double[] prices = {5, 10, 15};
        double[] costs = {10, 20, 30};
        int[] quantities = {1, 2, 3};
        double lastYearProfit = 100;

        double result = priceService.ifContinuedInvestment(prices, costs, quantities, lastYearProfit);

        assertEquals(-70, result);
    }

    @Test
    void calculatesProfitWhenPricesAreEqualToCosts() {
        double[] prices = {10, 20, 30};
        double[] costs = {10, 20, 30};
        int[] quantities = {1, 2, 3};
        double lastYearProfit = 100;

        double result = priceService.ifContinuedInvestment(prices, costs, quantities, lastYearProfit);

        assertEquals(0, result);
    }

    @Test
    void calculatesProfitWhenQuantitiesAreZero() {
        double[] prices = {10, 20, 30};
        double[] costs = {5, 10, 15};
        int[] quantities = {0, 0, 0};
        double lastYearProfit = 100;

        double result = priceService.ifContinuedInvestment(prices, costs, quantities, lastYearProfit);

        assertEquals(0, result);
    }

    @Test
    void calculatesProfitWhenLastYearProfitIsZero() {
        double[] prices = {10, 20, 30};
        double[] costs = {5, 10, 15};
        int[] quantities = {1, 2, 3};
        double lastYearProfit = 0;

        double result = priceService.ifContinuedInvestment(prices, costs, quantities, lastYearProfit);

        assertEquals(70, result);
    }

    @Test
    void calculatesProfitWhenSomeNumIsNegative() {
        double[] prices = {-10, 20, 30};
        double[] costs = {-5, -10, 15};
        int[] quantities = {1, 2, -3};
        double lastYearProfit = 0;

        double result = priceService.ifContinuedInvestment(prices, costs, quantities, lastYearProfit);

        assertEquals(0, result);
    }

}