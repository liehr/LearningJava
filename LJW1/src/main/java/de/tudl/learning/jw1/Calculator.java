package de.tudl.learning.jw1;

/**
 * A simple calculator for performing basic arithmetic operations such as addition,
 * subtraction, multiplication, and division.
 *
 * <p>This calculator handles basic mathematical operations on {@code double} values.
 * It throws exceptions for invalid operations such as division by zero or square root of
 * a negative number.
 *
 * <p>Usage example:
 * <pre>{@code
 * Calculator calc = new Calculator();
 * double sum = calc.add(5.5, 4.5);
 * }</pre>
 *
 * @author Florian Liehr
 * @version 1.0
 * @since 1.0
 */
public class Calculator
{
    /**
     * Adds two double values.
     *
     * @param a the first value to add
     * @param b the second value to add
     * @return the result of {@code a + b}
     */
    public double add(double a, double b)
    {
        return a + b;
    }

    /**
     * Subtracts two double values.
     *
     * @param a the first value to subtract
     * @param b the second value to subtract
     * @return the result of {@code a - b}
     */
    public double subtract(double a, double b)
    {
        return a - b;
    }

    /**
     * Multiply two double values.
     *
     * @param a the first value to multiply
     * @param b the second value to multiply
     * @return the result of {@code a * b}
     */
    public double multiply(double a, double b)
    {
        return a * b;
    }

    /**
     * Divides one value by another.
     *
     * @param a the numerator
     * @param b the denominator, which must not be zero
     * @return the result of {@code a / b}
     * @throws ArithmeticException if {@code b} is zero
     */
    public double divide(double a, double b)
    {
        if (b == 0)
            throw new ArithmeticException("Error: Don't divide a number by zero!");

        return a / b;
    }

    /**
     * Square root of a given number.
     *
     * @param a number square root would be taken off
     * @return the result of {@code √a}
     * @throws ArithmeticException if {@code a} is smaller then 0
     */
    public double sqrt(double a)
    {
        if (a < 0)
            throw new ArithmeticException("Error: Cannot compute the square root of a negative number!");

        return Math.sqrt(a);
    }

    /**
     * Square a number.
     *
     * @param a number to be squared
     * @return the result of {@code a * a}
     */
    public double sqr(double a)
    {
        return a * a;
    }


    /**
     * Raises a base to the power of an exponent.
     *
     * <p>This method returns the result of raising {@code a} to the power of {@code b}.
     * It supports positive, negative, and fractional exponents. If {@code a} is zero
     * and {@code b} is non-positive, this may result in an {@link ArithmeticException}.
     * Special cases include:
     * <ul>
     *     <li>If {@code a} is zero and {@code b} is positive, the result is zero.</li>
     *     <li>If {@code a} is zero and {@code b} is zero, the result is typically 1.</li>
     *     <li>If {@code b} is negative, the result is the reciprocal of the positive power.</li>
     * </ul>
     *
     * @param a the base to be raised to the power of {@code b}
     * @param b the exponent to which {@code a} is raised
     * @return the value of {@code a} raised to the power of {@code b}
     * @throws ArithmeticException if {@code a} is zero and {@code b} is negative
     */
    public double pow(double a, double b) {

        if (a == 0 && b < 0)
            throw new ArithmeticException("When the base is 0 the power must be >= 0!");

        return Math.pow(a, b);
    }

}
