package com.offerista.task.consumer;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

class PrimeNumberCalculatorTest {

    private PrimeNumberCalculator calculator = new PrimeNumberCalculator();

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 13})
    void isPrime_primeNumber_true(int number) {
        CompletableFuture<Boolean> result = calculator.isPrime(number);

        result.thenAccept(isPrime -> assertTrue(isPrime));
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 8, 15, 21, 33})
    void isPrime_nonPrimeNumber_false(int number) {
        CompletableFuture<Boolean> result = calculator.isPrime(number);

        result.thenAccept(isPrime -> assertFalse(isPrime));
    }
}