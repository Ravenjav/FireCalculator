package com.my.calculator;

import com.my.calculator.exception.CalculatorException;
import com.my.calculator.service.Calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws CalculatorException {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        Calculator calculator = new Calculator();
        System.out.println(calculator.findOptimalPercent(year));
    }
}
