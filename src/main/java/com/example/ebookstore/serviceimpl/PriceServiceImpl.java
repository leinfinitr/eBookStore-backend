package com.example.ebookstore.serviceimpl;

import java.math.BigDecimal;
import java.util.List;

public class PriceServiceImpl {

    // set some profit into the investment
    double investmentRate = 0.35;
    double inflationRate = 0.05;
    double []investorRate = {0.1, 0.2, 0.45, 0.05, 0.2};
    double []investorConfidence = {0.05, 0.1, 0.2, 0.35, 0.3};

    // For DD-path testing
    public double ifContinuedInvestment(double []prices, double []costs, int []quantities, double lastYearProfit) {
        BigDecimal orderTotal = BigDecimal.ZERO;
        for (int i = 0; i < prices.length; i++) {
            BigDecimal itemTotal;
            if(prices[i] < 0 ) {
                System.out.println("Invalid price: " + prices[i]);
                itemTotal = BigDecimal.ZERO;
            } else if(costs[i] < 0) {
                System.out.println("Invalid cost: " + costs[i]);
                itemTotal = BigDecimal.ZERO;
            } else if(quantities[i] < 0) {
                System.out.println("Invalid quantity: " + quantities[i]);
                itemTotal = BigDecimal.ZERO;
            } else {
                System.out.println("Valid input");
                itemTotal = BigDecimal.valueOf((prices[i] - costs[i]) * quantities[i]);
                if(prices[i] < costs[i]) {
                    System.out.println("Price is less than cost, loss: " + itemTotal);
                } else {
                    System.out.println("Price is greater than cost, profit: " + itemTotal);
                }
                orderTotal = orderTotal.add(itemTotal);
            }
            System.out.println("Item total: " + itemTotal);
        }
        System.out.println("Order total: " + orderTotal);
        BigDecimal lastYearProfitToThisYear = BigDecimal.valueOf(lastYearProfit * (1 + inflationRate));
        System.out.println("Last year profit to this year: " + lastYearProfitToThisYear);
        if(orderTotal.compareTo(lastYearProfitToThisYear) > 0) {
            System.out.println("Order profit is greater than last year profit");
            BigDecimal investment = orderTotal.subtract(lastYearProfitToThisYear).multiply(BigDecimal.valueOf(investmentRate));
            System.out.println("Investment: " + investment);
            for(int i = 0; i < investorRate.length; i++) {
                BigDecimal investorProfit = orderTotal.multiply(BigDecimal.valueOf(investorRate[i]));
                System.out.println("Investor " + i + " profit: " + investorProfit);
            }
            // generate invector confidence
            for(int i = 0; i < investorConfidence.length; i++) {
                investorConfidence[i] = investorConfidence[i] + investorRate[i] * investorConfidence[i];
                System.out.println("Investor " + i + " confidence: " + investorConfidence[i]);
                if(investorConfidence[i] > 0.3) {
                    System.out.println("Investor " + i + " confidence is greater than 0.3, very good!");
                }
            }
        } else {
            System.out.println("Order profit is less than last year profit");
            BigDecimal investment = lastYearProfitToThisYear.subtract(orderTotal).multiply(BigDecimal.valueOf(investmentRate));
            System.out.println("Withdraw investment: " + investment);
            for(int i = 0; i < investorRate.length; i++) {
                BigDecimal investorProfit = orderTotal.multiply(BigDecimal.valueOf(investorRate[i]));
                System.out.println("Investor " + i + " profit: " + investorProfit);
            }
            // generate invector confidence
            for(int i = 0; i < investorConfidence.length; i++) {
                investorConfidence[i] = investorConfidence[i] - investorRate[i] * investorConfidence[i];
                System.out.println("Investor " + i + " confidence: " + investorConfidence[i]);
                if(investorConfidence[i] < 0.1) {
                    System.out.println("Investor " + i + " confidence is less than 0.1, need to increase");
                }
            }
        }

        return orderTotal.doubleValue();
    }

}


