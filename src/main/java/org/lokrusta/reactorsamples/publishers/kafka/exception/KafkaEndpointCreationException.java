package org.lokrusta.reactorsamples.publishers.kafka.exception;

public class KafkaEndpointCreationException extends RuntimeException {

    public KafkaEndpointCreationException(Exception e) {
        super(e);
    }
}
