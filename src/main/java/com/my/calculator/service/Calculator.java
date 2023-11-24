package com.my.calculator.service;

import com.my.calculator.entity.Inflation;
import com.my.calculator.entity.SharePrice;
import com.my.calculator.exception.CalculatorException;

public class Calculator {

    private Inflation inflation;
    private SharePrice sharePrice;

    private static final double STEP = 0.5, MAX_PERCENT_VALUE = 100;

    public Calculator() {
        inflation = new Inflation();
        sharePrice = new SharePrice();
    }

    /**
     * Перебирает все возможные проценты изымания начиная с 0.5 до 100
     * @param startYear год начала жизни на проценты
     * @param endYear год конца жизни на проценты
     * @return percent - stop возвращает максимальный валидный процент
     * @throws CalculatorException
     */
    public double findOptimalPercent(int startYear, int endYear) throws CalculatorException {
        checkValid(startYear, endYear);
        double percent = STEP;
        while (percent <= MAX_PERCENT_VALUE) {
            if (checkPercent(percent, startYear, endYear)) {
                break;
            }
            percent += STEP;
        }
        return percent - STEP;
    }

    /**
     * Проверяет ликвидность подобранного процента изымания
     * @param percent процент изымания
     * @param startYear год начала жизни на проценты
     * @param endYear год окончания жизни на проценты
     * @return false если по окончанию капитал имеет отрицательное значение
     * @throws CalculatorException
     */
    private boolean checkPercent(double percent, int startYear, int endYear) throws CalculatorException {
        double capital = 100;
        for (int checkYear = startYear; checkYear < endYear; checkYear++) {
            capital -= percent;
            capital *= sharePrice.getYearSharePrice(checkYear + 1) / sharePrice.getYearSharePrice(checkYear);
            percent = calculateNewPercent(inflation.getYearInflation(checkYear), percent);
        }
        return (capital < 0);
    }

    /**
     * Проверяет наличие данных об инфляции и цене ценных бумаг с startYear по endYear
     * @param startYear год начала жизни на проценты
     * @param endYear год окончания жизни на проценты
     * @throws CalculatorException
     */
    private void checkValid(int startYear, int endYear) throws CalculatorException {
        if (startYear >= endYear) {
            throw new CalculatorException("input data is not correct");
        }
        for (int checkYear = startYear; checkYear <= endYear; checkYear++) {
            if (!sharePrice.yearIsExist(checkYear) || !inflation.yearIsExist(checkYear)) {
                System.out.println(sharePrice.yearIsExist(checkYear) + " " + inflation.yearIsExist(checkYear));
                throw new CalculatorException("input data is not correct");
            }
        }
    }

    /**
     * перерасчитывает процент с учетом инфляции
     * @param inflation - инфляция
     * @param percent - текущий процент
     * @return percent - новый процент с учетом инфляции
     */
    private double calculateNewPercent(double inflation, double percent){
        percent *= (double) 1 + (inflation / 100);
        return percent;
    }
}
