package by.my.calculator;

import java.util.Scanner;

public class Main {
    public static final double[] MOEX_RATE = {
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
    public static final double[] INFLATION_RATE = {
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

    public static void main(String[] args) throws CalculatorException {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        if (year < 2002 || year > 2021)
            throw new CalculatorException("wrong year");
        System.out.println(check(year - 2002));
    }

    public static double check(int year){
        double start_percent = 0.5;
        double percent;
        while (start_percent <= 100){
            double capital = 100;
            percent = start_percent;
            for (int check_Year = year; check_Year < 20; check_Year++){
                capital -= percent;
                capital *= MOEX_RATE[check_Year + 1] / MOEX_RATE[check_Year];
                percent *= (double) 1 + (INFLATION_RATE[check_Year] / 100);
            }
            if (capital < 0)
                break;
            start_percent += 0.5;
        }
        return start_percent - 0.5;
    }
}
