package org.lokrusta.reactorsamples.samples.tests;

import org.lokrusta.reactorsamples.publishers.kafka.KafkaConsumerRunner;
import org.lokrusta.reactorsamples.publishers.kafka.KafkaPublisherFactory;
import org.lokrusta.reactorsamples.generator.KafkaMessageSender;
import org.lokrusta.reactorsamples.samples.dto.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class BackpressureConsumer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final KafkaPublisherFactory<Event> kafkaPublisherFactory;
    private final KafkaConsumerRunner kafkaConsumerRunner;
    private final KafkaMessageSender<String> kafkaMessageSender;

    public BackpressureConsumer(KafkaPublisherFactory<Event> kafkaPublisherFactory,
                                KafkaConsumerRunner kafkaConsumerRunner,
                                KafkaMessageSender<String> kafkaMessageSender) {
        this.kafkaPublisherFactory = kafkaPublisherFactory;
        this.kafkaConsumerRunner = kafkaConsumerRunner;
        this.kafkaMessageSender = kafkaMessageSender;
    }

    public void consume() {
        Flux<Event> eventFlux = kafkaPublisherFactory.createPublisher();
        // subscribe eventFlux
        eventFlux.subscribe(message -> {
            logger.info("Next message {}", message);
        });

        // Start reading Kafka
        kafkaConsumerRunner.start();

        // Start send Kafka messages
        kafkaMessageSender.start(10, "testTopic");
    }
}
