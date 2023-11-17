package com.my.calculator.service;

import com.my.calculator.entity.Inflation;
import com.my.calculator.entity.SharePrice;
import com.my.calculator.exception.CalculatorException;

public class Calculator {

    private Inflation inflation;
    private SharePrice sharePrice;

    public Calculator(){
        inflation = new Inflation();
        sharePrice = new SharePrice();
    }
    public double findOptimalPercent(int startYear, int endYear) throws CalculatorException {
        checkValid(startYear, endYear);
        double percent = 0.5;
        while (percent <= 100) {
            if (checkPercent(percent, startYear, endYear) < 0) {
                break;
            }
            percent += 0.5;
        }
        return percent - 0.5;
    }

    private double checkPercent(double percent, int startYear, int endYear) throws CalculatorException {
        double capital = 100;
        for (int checkYear = startYear; checkYear < endYear; checkYear++) {
            capital -= percent;
            capital *= sharePrice.getYearSharePrice(checkYear + 1) / sharePrice.getYearSharePrice(checkYear);
            percent *= (double) 1 + (inflation.getYearInflation(checkYear) / 100);
        }
        return capital;
    }

    private void checkValid(int startYear, int endYear) throws CalculatorException {
        for (int checkYear = startYear; checkYear <= endYear; checkYear++){
            if (!sharePrice.yearIsExist(checkYear) || !inflation.yearIsExist(checkYear)){
                throw new CalculatorException("input data is not correct");
            }
        }
    }
}
