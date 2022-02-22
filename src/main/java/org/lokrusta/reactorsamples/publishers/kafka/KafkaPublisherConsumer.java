package org.lokrusta.reactorsamples.publishers.kafka;

import org.lokrusta.reactorsamples.publishers.MessageCreator;

import java.util.function.Consumer;

public class KafkaPublisherConsumer<T> {

    private final MessageCreator<T> messageCreator;
    private Consumer<T> consumer;


    public KafkaPublisherConsumer(MessageCreator<T> messageCreator) {
        this.messageCreator = messageCreator;
    }

    public void consume(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    // @KafkaListener
    public void onMessage(String message) {
        T object = messageCreator.createMessage(message);
        consumer.accept(object);
    }
}
