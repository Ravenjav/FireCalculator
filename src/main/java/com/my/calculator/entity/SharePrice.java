package com.my.calculator.entity;

import com.my.calculator.exception.CalculatorException;

import java.util.HashMap;
import java.util.Map;

public class SharePrice {
    private static final double[] BASIC_SHARE_PRICE = {417.42, 673.72, 722.81, 1323.32, 2216.63, 2472.38, 810.922, 1793.24, 2209.46, 1835.14, 1934.43, 1967.83, 1828.06, 2305.50, 3042.00, 3015.71, 3564.05, 4887.25, 5567.28, 6731.43, 4170.35};

    private static final int BASE_SHARE_YEAR = 2002;
    private Map<Integer, Double> sharePrice;

    public SharePrice() {
        sharePrice = new HashMap<>();
        for (int i = 0; i < BASIC_SHARE_PRICE.length; i++) {
            sharePrice.put(i + BASE_SHARE_YEAR, BASIC_SHARE_PRICE[i]);
        }
    }

    public Map<Integer, Double> getSharePrice() {
        return sharePrice;
    }

    public Double getYearSharePrice(Integer year) throws CalculatorException {
        if (!sharePrice.containsKey(year)) {
            throw new CalculatorException("Share price do not exist this year");
        }
        return sharePrice.get(year);
    }

    public Boolean yearIsExist(Integer year) {
        return sharePrice.containsKey(year);
    }

    public void setYearSharePrice(Integer year, Double price) throws CalculatorException {
        if (sharePrice.containsKey(year)) {
            throw new CalculatorException("Share price already exist this year");
        }
        sharePrice.put(year, price);
    }
}
