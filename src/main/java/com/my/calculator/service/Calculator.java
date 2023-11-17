package com.my.calculator.service;

import com.my.calculator.entity.Inflation;
import com.my.calculator.entity.SharePrice;
import com.my.calculator.exception.CalculatorException;

import java.util.HashMap;
import java.util.Map;

public class Calculator {
    public double findOptimalPercent(int startYear, int endYear) throws CalculatorException {
        checkValid(startYear);
        double percent = 0.5;
        while (percent <= 100) {
            if (checkPercent(percent, startYear, endYear) < 0) {
                break;
            }
            percent += 0.5;
        }
        return percent - 0.5;
    }

    private double checkPercent(double percent, int startYear, int endYear) {
        double capital = 100;
        Map<Integer, Double> inflation = new Inflation().getInflation();
        Map<Integer, Double> sharePrice = new SharePrice().getSharePrice();
        for (int checkYear = startYear; checkYear < endYear; checkYear++) {
            capital -= percent;
            capital *= sharePrice.get(checkYear + 1) / sharePrice.get(checkYear);
            percent *= (double) 1 + (inflation.get(checkYear) / 100);
        }
        return capital;
    }

    private void checkValid(int year) throws CalculatorException {
        if (year < 2002 || year > 2021) throw new CalculatorException("wrong year");
    }
}
