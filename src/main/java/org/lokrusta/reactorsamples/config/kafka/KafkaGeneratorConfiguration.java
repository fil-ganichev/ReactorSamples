package org.lokrusta.reactorsamples.config.kafka;

import org.lokrusta.reactorsamples.generator.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaGeneratorConfiguration {

    @Bean
    KafkaGeneratorService<String> kafkaGeneratorService(KafkaMessageGenerator<String> kafkaMessageGenerator,
                                                        KafkaTemplate<String, String> kafkaTemplate) {
        return new DefaultKafkaGeneratorService(kafkaMessageGenerator,
                kafkaTemplate);
    }

    @Bean
    KafkaMessageGenerator<String> kafkaMessageGenerator() {
        return new DefaultKafkaMessageGenerator();
    }

    @Bean
    KafkaMessageSender<String> kafkaMessageSender(KafkaGeneratorService<String> kafkaGeneratorService) {
        return new KafkaMessageSender<>(kafkaGeneratorService);
    }
}
