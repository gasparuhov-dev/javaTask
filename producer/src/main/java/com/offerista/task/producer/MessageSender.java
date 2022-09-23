package com.offerista.task.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MessageSender extends Thread {
    private final RabbitTemplate rabbitTemplate;
    private final RandomNumbersContainer container;
    private final FileLogger logger;

    @Override
    public void run() {
        while(true) {
            var randomNumbers = container.getRandomNumbers(5);
            var message = randomNumbers.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(","));
            rabbitTemplate.convertAndSend(
                    ProducerApplication.topicExchangeName,
                    ProducerApplication.routingKey + UUID.randomUUID(),
                    message);

            logger.log(message);
        }
    }
}
