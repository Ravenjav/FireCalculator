package com.my.calculator;

import com.my.calculator.exception.CalculatorException;
import com.my.calculator.service.Calculator;

import java.util.Scanner;

public class Main {

    public static final int END_YEAR = 2022;
    public static void main(String[] args) throws CalculatorException {
        Scanner scanner = new Scanner(System.in);
        int startYear = scanner.nextInt();
        Calculator calculator = new Calculator();
        System.out.println(calculator.findOptimalPercent(startYear, END_YEAR));
    }
}
