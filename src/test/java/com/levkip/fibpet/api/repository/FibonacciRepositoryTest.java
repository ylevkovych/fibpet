package com.levkip.fibpet.api.repository;

import com.levkip.fibpet.api.repository.FibonacciRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciRepositoryTest {


    private FibonacciRepository fibonacciRepository;

    @BeforeEach
    private void setup() {
        fibonacciRepository = new FibonacciRepository();
        fibonacciRepository.put(1, 100L);
        fibonacciRepository.put(2, 200L);
    }

    @Test
    void get() {
        assertEquals(100L, (long) fibonacciRepository.get(1));
    }

    @Test
    void put() {
        fibonacciRepository.put(3, 300L);
        assertEquals(300L, fibonacciRepository.get(3));
    }
}