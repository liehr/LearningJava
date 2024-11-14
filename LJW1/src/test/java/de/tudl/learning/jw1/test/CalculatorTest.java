package de.tudl.learning.jw1.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import de.tudl.learning.jw1.Calculator;

class CalculatorTest {

    private Calculator calc;

    @BeforeEach
    void setupCalculator()
    {
        calc = new Calculator();
    }

    @Test
    void testAddition()
    {
        double result = calc.add(2.45, 3.12);
        assertEquals(5.57, result, 0.0001, "2.45 + 3.12 should be 5.57");
    }

    @Test
    void testAdditionWithZero() {
        double result = calc.add(0, 5.0);
        assertEquals(5.0, result, 0.0001, "0 + 5 should be 5");
    }

    @Test
    void testSubtract()
    {
        double result = calc.subtract(1.75, 3.25);
        assertEquals(-1.5, result, 0.0001, "1.75 - 3.25 should be -1.5");
    }

    @Test
    void testMultiplication()
    {
        double result = calc.multiply(2.5, 3.5);
        assertEquals(8.75, result, 0.0001, "2.5 * 3.5 should be 8.75");
    }

    @Test
    void testMultiplicationWithNegativeNumbers() {
        double result = calc.multiply(-2.5, 3.5);
        assertEquals(-8.75, result, 0.0001, "-2.5 * 3.5 should be -8.75");
    }


    @Test
    void testDivision()
    {
        double result = calc.divide(2.0, 3.0);
        assertEquals(2.0 / 3.0, result, 0.0001, "2.0 / 3.0 should be nearly 0.6667");
    }

    @Test
    void testDivisionWithZero()
    {
        assertThrows(ArithmeticException.class, () -> calc.divide(1.0, 0.0));
    }

    @Test
    void testSquareRoot()
    {
        double result = calc.sqrt(49);
        assertEquals(7.0, result, 0.0001, "Sqaureroot of 49 should be 7");
    }

    @Test
    void testSquareRootOfNegativeNumber()
    {
        assertThrows(ArithmeticException.class,  () -> calc.sqrt(-1.0));
    }

    @Test
    void testSquared()
    {
        double result = calc.sqr(1.5);
        assertEquals(2.25, result, 0.0001, "1.5 squared should be 2.25");
    }

    @Test
    void testPower()
    {
        double result = calc.pow(1.7, 3.5);
        assertEquals(6.4057682834, result, 0.0001, "1.7 to the power of 3.5 should be 6.4057682834");
    }

    @Test
    void testPowerOfZero() {
        double result = calc.pow(5.2, 0);
        assertEquals(1.0, result, 0.0001, "Any number to the power of 0 should be 1");
    }

    @Test
    void testPowerOfOne() {
        double result = calc.pow(5.2, 1);
        assertEquals(5.2, result, 0.0001, "Any number to the power of 1 should be the number itself");
    }

    @Test
    void testPowerWithNegativeExponent() {
        double result = calc.pow(2, -1);
        assertEquals(0.5, result, 0.0001, "2 to the power of -1 should be 0.5");
    }

    @Test
    void testPowerWithBaseZeroAndNegativeExponent()
    {
        assertThrows(ArithmeticException.class, () -> calc.pow(0, -1));
    }
}
