package org.lokrusta.reactorsamples.generator;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.function.Supplier;

public class DefaultKafkaGeneratorService implements KafkaGeneratorService<String> {

    private final KafkaMessageGenerator<String> kafkaMessageGenerator;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public DefaultKafkaGeneratorService(KafkaMessageGenerator<String> kafkaMessageGenerator,
                                        KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaMessageGenerator = kafkaMessageGenerator;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Supplier<Runnable> get(int permits, String topicName) {
        return () -> new GeneratorThread(kafkaMessageGenerator,
                kafkaTemplate,
                RateLimiter.create(permits),
                topicName);
    }

    static class GeneratorThread implements Runnable {

        private final KafkaTemplate<String, String> kafkaTemplate;
        private final KafkaMessageGenerator<String> kafkaMessageGenerator;
        private final RateLimiter rateLimiter;
        private final String topicName;

        public GeneratorThread(KafkaMessageGenerator<String> kafkaMessageGenerator,
                               KafkaTemplate<String, String> kafkaTemplate,
                               RateLimiter rateLimiter,
                               String topicName) {
            this.kafkaMessageGenerator = kafkaMessageGenerator;
            this.kafkaTemplate = kafkaTemplate;
            this.rateLimiter = rateLimiter;
            this.topicName = topicName;
        }

        @Override
        public void run() {
            int i = 1;
            while (!Thread.currentThread().isInterrupted()) {
                rateLimiter.acquire();
                kafkaTemplate.send(topicName, kafkaMessageGenerator.nextMessage(i++));
            }
        }
    }
}
