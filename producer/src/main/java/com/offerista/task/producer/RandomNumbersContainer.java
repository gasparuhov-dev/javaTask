package com.offerista.task.producer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RandomNumbersContainer {
    private List<Integer> randomNumbers = new ArrayList<>();

    public synchronized void add(Integer randomNumber) {
        randomNumbers.add(randomNumber);
    }

    public synchronized List<Integer> getRandomNumbers(int amount) {
        while (randomNumbers.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException("Thread: " + Thread.currentThread().getId() + " was interrupted!");
            }
        }

        return getIntegers(amount);
    }

    public void notifyWaiting() {
        synchronized (this) {
            notify();
        }
    }

    private List<Integer> getIntegers(int amount) {
        List<Integer> result;
        if (randomNumbers.size() < amount) {
            result = new ArrayList<>(randomNumbers);
            randomNumbers.clear();
        } else {
            result = new ArrayList<>(randomNumbers.subList(0, amount));
            randomNumbers.subList(0, amount).clear();
        }
        return result;
    }
}
