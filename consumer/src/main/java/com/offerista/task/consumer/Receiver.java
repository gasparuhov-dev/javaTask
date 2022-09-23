package com.offerista.task.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Receiver {

    private final PrimeNumberCalculator calculator;
    private final FileLogger logger;

    public void receiveMessage(String message) {
        var numbers = Arrays.stream(message.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        numbers.stream().forEach(number -> isPrime(number));
    }

    void isPrime(Integer number) {
        CompletableFuture<Boolean> prime = calculator.isPrime(number);
        prime.thenAccept(isPrime -> log(isPrime, number));
    }

    private void log(Boolean isPrime, Integer number) {
        if(isPrime) {
            logger.log(number);
        }
    }

}
