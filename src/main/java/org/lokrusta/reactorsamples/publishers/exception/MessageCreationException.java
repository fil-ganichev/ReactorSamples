package org.lokrusta.reactorsamples.publishers.exception;

public class MessageCreationException extends RuntimeException {

    public MessageCreationException(Exception cause) {
        super(cause);
    }
}
