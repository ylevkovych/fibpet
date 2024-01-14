package com.levkip.fibpet.api.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.levkip.fibpet.api.exception.ValueErrorException;
import com.levkip.fibpet.api.model.Fib;
import com.levkip.fibpet.api.repository.FibonacciCacheRepository;
import com.levkip.fibpet.api.repository.FibonacciRepository;

@ExtendWith(MockitoExtension.class)
class FibonacciServiceTest {

    @InjectMocks
    private FibonacciService fibonacciService;

    @Mock
    private FibonacciRepository fibonacciRepository;
    
    @Mock
    private FibonacciCacheRepository fibonacciCacheRepository;

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

        Mockito.when(fibonacciCacheRepository.get(7)).thenReturn(13L);
        Mockito.when(fibonacciCacheRepository.get(11)).thenReturn(89L);
        Mockito.when(fibonacciCacheRepository.get(27)).thenReturn(196418L);
        Mockito.when(fibonacciCacheRepository.get(41)).thenReturn(165580141L);

        assertEquals(fibonacciService.countFibonacci(7), 13);
        assertEquals(fibonacciService.countFibonacci(11), 89);
        assertEquals(fibonacciService.countFibonacci(27), 196418);
        assertEquals(fibonacciService.countFibonacci(41), 165580141);
    }
    
    @Test
    public void getSavedFibonacciValues( ) {
    	
    	Fib fib1 = new Fib(7,13L);
    	Fib fib2 = new Fib(11,89L);
    	
    	List<Fib> fibs = Arrays.asList(fib1, fib2);
    	
    	Mockito.when(fibonacciRepository.get()).thenReturn(fibs);
    	
    	List<Fib> result = fibonacciService.getSavedFibonacciValues();
    	
    	assertEquals(result.size(), fibs.size());
    }
    
    @Test
    public void deleteSavedFibonacciValue( ) {
    	final Integer index = 7;
    	Fib fib1 = new Fib(index,13L);
    	
    	Mockito.when(fibonacciRepository.getOne(index)).thenReturn(fib1);
    	Mockito.when(fibonacciRepository.delete(index)).thenReturn(true);

        assertTrue(fibonacciService.deleteSavedFibonacciValue(index));
    }
    
    @Test
    public void deleteSavedFibonacciValue_NotFound( ) {
    	final Integer index = 7;
    	
    	Mockito.when(fibonacciRepository.getOne(index)).thenReturn(null);
    	
        ValueErrorException e = assertThrows(ValueErrorException.class, () -> {
        	fibonacciService.deleteSavedFibonacciValue(index);
        });

        assertTrue(e.getLocalizedMessage().startsWith("Fibonacci value by index ["+index+"] not found."));
    	
    }

}