package com.offerista.task.consumer;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class PrimeNumberCalculator {

    @Async
    public CompletableFuture<Boolean> isPrime(Integer number) {
        if (number <= 1) {
            return CompletableFuture.completedFuture(false);
        }

        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return CompletableFuture.completedFuture(false);
            }
        }

        return CompletableFuture.completedFuture(true);
    }
}
