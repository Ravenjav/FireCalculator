package com.my.calculator.service;

import com.my.calculator.entity.Inflation;
import com.my.calculator.entity.SharePrice;
import com.my.calculator.exception.CalculatorException;

public class Calculator {

    private Inflation inflation;
    private SharePrice sharePrice;

    public Calculator() {
        inflation = new Inflation();
        sharePrice = new SharePrice();
    }

    public double findOptimalPercent(int startYear, int endYear) throws CalculatorException {
        checkValid(startYear, endYear);
        double step = 0.5, maxPercentValue = 100;
        double percent = step;
        while (percent <= maxPercentValue) {
            if (checkPercent(percent, startYear, endYear) < 0) {
                break;
            }
            percent += step;
        }
        return percent - step;
    }

    private double checkPercent(double percent, int startYear, int endYear) throws CalculatorException {
        double capital = 100;
        for (int checkYear = startYear; checkYear < endYear; checkYear++) {
            capital -= percent;
            capital *= sharePrice.getYearSharePrice(checkYear + 1) / sharePrice.getYearSharePrice(checkYear);
            percent = calculateNewPercent(inflation.getYearInflation(checkYear), percent);
        }
        return capital;
    }

    private void checkValid(int startYear, int endYear) throws CalculatorException {
        if (startYear >= endYear)
            throw new CalculatorException("input data is not correct");
        for (int checkYear = startYear; checkYear <= endYear; checkYear++) {
            if (!sharePrice.yearIsExist(checkYear) || !inflation.yearIsExist(checkYear)) {
                System.out.println(sharePrice.yearIsExist(checkYear) + " " + inflation.yearIsExist(checkYear));
                throw new CalculatorException("input data is not correct");
            }
        }
    }

    private double calculateNewPercent(double inflation, double percent){
        percent *= (double) 1 + (inflation / 100);
        return percent;
    }
}
