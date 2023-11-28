package com.levkip.fibpet.api.service;

import com.levkip.fibpet.api.exception.ValueErrorException;
import com.levkip.fibpet.api.repository.FibonacciRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FibonacciServiceTest {

    @InjectMocks
    private FibonacciService fibonacciService;

    @Mock
    private FibonacciRepository fibonacciRepository;

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

        Mockito.when(fibonacciRepository.get(7)).thenReturn(13L);
        Mockito.when(fibonacciRepository.get(11)).thenReturn(89L);
        Mockito.when(fibonacciRepository.get(27)).thenReturn(196418L);
        Mockito.when(fibonacciRepository.get(41)).thenReturn(165580141L);

        assertEquals(fibonacciService.countFibonacci(7), 13);
        assertEquals(fibonacciService.countFibonacci(11), 89);
        assertEquals(fibonacciService.countFibonacci(27), 196418);
        assertEquals(fibonacciService.countFibonacci(41), 165580141);

    }

}