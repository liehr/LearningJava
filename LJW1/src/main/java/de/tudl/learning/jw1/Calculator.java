package de.tudl.learning.jw1;

public class Calculator
{
    public int add(int a, int b)
    {
        return a + b;
    }

    public int subtract(int a, int b)
    {
        return a - b;
    }

    public int multiply(int a, int b)
    {
        return a * b;
    }

    public int divide(int a, int b)
    {
        if (b == 0) throw new ArithmeticException("Division with 0");

        return a / b;
    }
}
