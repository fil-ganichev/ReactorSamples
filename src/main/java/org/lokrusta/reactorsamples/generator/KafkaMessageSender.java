package org.lokrusta.reactorsamples.generator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KafkaMessageSender<T> {

    public final KafkaGeneratorService<T> kafkaGeneratorService;

    private ExecutorService senderExecutor;

    public KafkaMessageSender(KafkaGeneratorService<T> kafkaGeneratorService) {
        this.kafkaGeneratorService = kafkaGeneratorService;
    }

    public void start(int permits, String topicName) {
        if (senderExecutor == null) {
            senderExecutor = Executors.newSingleThreadExecutor();
            senderExecutor.submit(kafkaGeneratorService.get(permits, topicName).get());
        }
    }

    public void stop() {
        if (senderExecutor != null) {
            senderExecutor.shutdown();
            senderExecutor = null;
        }
    }
}
