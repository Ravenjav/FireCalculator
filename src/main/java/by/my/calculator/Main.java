package by.my.calculator;

import by.my.calculator.exception.CalculatorException;
import by.my.calculator.service.Calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws CalculatorException {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        Calculator calculator = new Calculator();
        System.out.println(calculator.findOptimalPercent(year));
    }
}
