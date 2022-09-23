package com.offerista.task.producer;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomNumbersGenerator extends Thread{

    private final RandomNumbersContainer container;

    public RandomNumbersGenerator(RandomNumbersContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        while(true) {
            for(int i = 0; i < 5; i++) {
                int randomNumber = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE);
                container.add(randomNumber);
            }
            container.notifyWaiting();
            takeRest();
        }
    }

    private void takeRest() {
        try {
            sleep(10 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
