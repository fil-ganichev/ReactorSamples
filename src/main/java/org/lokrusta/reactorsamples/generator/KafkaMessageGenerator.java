package org.lokrusta.reactorsamples.generator;

public interface KafkaMessageGenerator<T> {

    T nextMessage(int next);
}
