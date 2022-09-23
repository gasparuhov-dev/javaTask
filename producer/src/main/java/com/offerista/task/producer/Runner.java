package com.offerista.task.producer;

import java.util.UUID;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    private  final TaskExecutor taskExecutor;
    private final RandomNumbersGenerator randomNumbersGenerator;
    private final MessageSender messageSender;

    @Override
    public void run(String... args) throws Exception {
        taskExecutor.execute(messageSender);
        taskExecutor.execute(randomNumbersGenerator);
    }

}
