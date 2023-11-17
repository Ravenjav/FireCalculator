package com.my.calculator.entity;

import com.my.calculator.exception.CalculatorException;

import java.util.HashMap;
import java.util.Map;

public class Inflation {
    private static final double[] INFLATION_RATE = {15.06, 11.99, 11.74, 10.91, 9.00, 11.87, 13.28, 8.80, 8.78, 6.10, 6.58, 6.45, 11.36, 12.91, 5.38, 2.52, 4.27, 03.05, 4.91, 8.39, 11.92,};

    private Map<Integer, Double> inflation;

    public Inflation() {
        inflation = new HashMap<>();
        for (int i = 0; i < 21; i++) {
            inflation.put(i + 2002, INFLATION_RATE[i]);
        }
    }

    public Map<Integer, Double> getInflation() {
        return inflation;
    }

    public Double getYearInflation(Integer year) throws CalculatorException {
        if (inflation.containsKey(year)) {
            return inflation.get(year);
        } else {
            throw new CalculatorException("This year inflation do not exist");
        }
    }

    public Boolean yearIsExist(Integer year){
        return inflation.containsKey(year);
    }

    public void setYearInflation(Integer year, Double percent) throws CalculatorException {
        if (inflation.containsKey(year)) {
            throw new CalculatorException("This year inflation already exist");
        } else {
            inflation.put(year, percent);
        }
    }
}
