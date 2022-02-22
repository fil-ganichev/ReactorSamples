package org.lokrusta.reactorsamples.publishers.kafka;

import org.lokrusta.reactorsamples.publishers.PublisherFactory;
import reactor.core.publisher.Flux;

public class KafkaPublisherFactory<T> implements PublisherFactory<T> {

    private final KafkaPublisherConsumer<T> kafkaPublisherConsumer;

    public KafkaPublisherFactory(KafkaPublisherConsumer<T> kafkaPublisherConsumer) {
        this.kafkaPublisherConsumer = kafkaPublisherConsumer;
    }

    @Override
    public Flux<T> createPublisher() {
        return Flux.create(emitter -> kafkaPublisherConsumer.consume(emitter::next));
    }
}
