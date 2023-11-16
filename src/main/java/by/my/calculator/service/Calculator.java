package by.my.calculator.service;

import by.my.calculator.exception.CalculatorException;

public class Calculator {
    private static final double[] MOEX_RATE = {
            417.42,
            673.72,
            722.81,
            1323.32,
            2216.63,
            2472.38,
            810.922,
            1793.24,
            2209.46,
            1835.14,
            1934.43,
            1967.83,
            1828.06,
            2305.50,
            3042.00,
            3015.71,
            3564.05,
            4887.25,
            5567.28,
            6731.43,
            4170.35
    };
    private static final double[] INFLATION_RATE = {
            15.06,
            11.99,
            11.74,
            10.91,
            9.00,
            11.87,
            13.28,
            8.80,
            8.78,
            6.10,
            6.58,
            6.45,
            11.36,
            12.91,
            5.38,
            2.52,
            4.27,
            03.05,
            4.91,
            8.39,
            11.92,
    };

    public double findOptimalPercent(int year) throws CalculatorException {
        checkValid(year);
        year = year - 2002;
        double percent = 0.5;
        while (percent <= 100){
            if (checkPercent(percent, year) < 0)
                break;
            percent += 0.5;
        }
        return percent - 0.5;
    }

    private double checkPercent(double percent, int year){
        double capital = 100;
        for (int checkYear = year; checkYear < 20; checkYear++){
            capital -= percent;
            capital *= MOEX_RATE[checkYear + 1] / MOEX_RATE[checkYear];
            percent *= (double) 1 + (INFLATION_RATE[checkYear] / 100);
        }
        return capital;
    }

    private void checkValid(int year) throws CalculatorException {
        if (year < 2002 || year > 2021)
            throw new CalculatorException("wrong year");
    }
}
