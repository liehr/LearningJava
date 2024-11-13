package de.tudl.learning.jw1.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import de.tudl.learning.jw1.Calculator;

class CalculatorTest {

    @Test
    void testAdd()
    {
        Calculator calc = new Calculator();
        int result = calc.add(2, 3);
        assertEquals(5, result, "2 + 3 should be 5");
    }

    @Test
    void testSubtract()
    {
        Calculator calc = new Calculator();
        int result = calc.subtract(2, 3);
        assertEquals(-1, result, "2 - 3 should be -1");
    }

    @Test
    void testMultiplication()
    {
        Calculator calc = new Calculator();
        int result = calc.multiply(2, 3);
        assertEquals(6, result, "2 * 3 should be 6");
    }

    @Test
    void testDivision()
    {
        Calculator calc = new Calculator();
        int result = calc.divide(6, 3);
        assertEquals(2, result, "6 / 3 should be 2");
    }

    @Test
    void testDivisionWithZero()
    {
        Calculator calc = new Calculator();
        assertThrows(ArithmeticException.class, () -> calc.divide(1, 0))
    }

}
