package com.offerista.task.consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReceiverTest {
    @Mock
    FileLogger fileLogger;
    @Mock
    PrimeNumberCalculator primeNumberCalculator;
    @InjectMocks
    @Spy
    private Receiver receiver;

    @BeforeEach
    public void setUp() {
        doNothing().when(receiver).isPrime(any());
    }

    @Test
    public void receiveMessage_multipleNumbers_isPrimeCalledWithAllNumbers() {
        String numbers = "1,4,8";

        receiver.receiveMessage(numbers);

        verify(receiver, times(1)).isPrime(1);
        verify(receiver, times(1)).isPrime(4);
        verify(receiver, times(1)).isPrime(8);
    }

    @Test
    public void receiveMessage_singleNumber_isPrimeCalledWithThatNumber() {
        String numbers = "3";

        receiver.receiveMessage(numbers);

        verify(receiver, times(1)).isPrime(3);
    }

}