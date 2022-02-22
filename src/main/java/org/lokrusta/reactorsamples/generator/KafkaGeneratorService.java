package org.lokrusta.reactorsamples.generator;

import java.util.function.Supplier;

public interface KafkaGeneratorService<T> {

    Supplier<Runnable> get(int permits, String topicName);
}
