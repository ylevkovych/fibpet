package com.levkip.fibpet.api.service;

import com.levkip.fibpet.api.exception.ValueErrorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciServiceTest {

    @Mock
    private FibonacciService fibonacciService;

    @BeforeEach
    public void setup() {
        fibonacciService = new FibonacciService();
    }

    @Test
    public void countFibonacci_IndexIsNotSet() {

        ValueErrorException e = assertThrows(ValueErrorException.class, () -> {
            fibonacciService.countFibonacci(null);
        });

        assertTrue("Index is not set.".equals(e.getLocalizedMessage()));
    }

    @Test
    public void countFibonacci_IndexIsTooHigh() {

        ValueErrorException e = assertThrows(ValueErrorException.class, () -> {
           fibonacciService.countFibonacci(5001);
        });

        assertTrue(e.getLocalizedMessage().startsWith("Index is to high. Should be less or equal "));

    }

    @Test
    public void countFibonacci() {

        assertEquals(fibonacciService.countFibonacci(0), 0);
        assertEquals(fibonacciService.countFibonacci(7), 13);
        assertEquals(fibonacciService.countFibonacci(11), 89);
        assertEquals(fibonacciService.countFibonacci(27), 196418);
        assertEquals(fibonacciService.countFibonacci(41), 165580141);
        assertEquals(fibonacciService.countFibonacci(45), 1134903170);

    }

}