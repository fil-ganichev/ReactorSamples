package org.lokrusta.reactorsamples.publishers;

public interface MessageCreator<T> {

    T createMessage(String rawMessage);
}
