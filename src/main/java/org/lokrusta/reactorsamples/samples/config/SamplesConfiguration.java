package org.lokrusta.reactorsamples.samples.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lokrusta.reactorsamples.publishers.DefaultMessageCreator;
import org.lokrusta.reactorsamples.publishers.MessageCreator;
import org.lokrusta.reactorsamples.publishers.kafka.KafkaConsumerRunner;
import org.lokrusta.reactorsamples.publishers.kafka.KafkaEndpointProperties;
import org.lokrusta.reactorsamples.publishers.kafka.KafkaPublisherConsumer;
import org.lokrusta.reactorsamples.publishers.kafka.KafkaPublisherFactory;
import org.lokrusta.reactorsamples.samples.dto.Event;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;

@Configuration
public class SamplesConfiguration {

    @Bean
    public MessageCreator<Event> eventMessageCreator(ObjectMapper objectMapper) {
        return new DefaultMessageCreator<>(objectMapper, Event.class);
    }

    @Bean
    public KafkaEndpointProperties kafkaEndpointProperties() {
        KafkaEndpointProperties kafkaEndpointProperties = new KafkaEndpointProperties();
        kafkaEndpointProperties.setId("test");
        kafkaEndpointProperties.setGroupId("test");
        kafkaEndpointProperties.setTopic("testTopic");
        return kafkaEndpointProperties;
    }

    @Bean
    public KafkaConsumerRunner kafkaConsumerRunner(ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory,
                                                   KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry,
                                                   KafkaPublisherConsumer<Event> kafkaPublisherConsumer,
                                                   KafkaEndpointProperties kafkaEndpointProperties) {
        return new KafkaConsumerRunner(kafkaListenerContainerFactory,
                kafkaListenerEndpointRegistry,
                kafkaPublisherConsumer,
                kafkaEndpointProperties);
    }

    @Bean
    public KafkaPublisherConsumer<Event> kafkaPublisherConsumer(MessageCreator<Event> eventMessageCreator) {
        return new KafkaPublisherConsumer<>(eventMessageCreator);
    }

    @Bean
    public KafkaPublisherFactory<Event> kafkaPublisherFactory(KafkaPublisherConsumer<Event> kafkaPublisherConsumer) {
        return new KafkaPublisherFactory<>(kafkaPublisherConsumer);
    }

    @Bean
    public KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry() {
        return new KafkaListenerEndpointRegistry();
    }


}
