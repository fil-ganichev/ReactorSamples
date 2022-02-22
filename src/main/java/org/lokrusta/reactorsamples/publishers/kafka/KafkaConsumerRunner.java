package org.lokrusta.reactorsamples.publishers.kafka;

import org.lokrusta.reactorsamples.publishers.kafka.exception.KafkaEndpointCreationException;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.config.MethodKafkaListenerEndpoint;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

public class KafkaConsumerRunner {

    private final ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory;
    private final KafkaEndpointProperties kafkaEndpointProperties;
    private final KafkaPublisherConsumer<?> kafkaPublisherConsumer;
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    public KafkaConsumerRunner(ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory,
                               KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry,
                               KafkaPublisherConsumer<?> kafkaPublisherConsumer,
                               KafkaEndpointProperties kafkaEndpointProperties) {
        this.kafkaListenerContainerFactory = kafkaListenerContainerFactory;
        this.kafkaListenerEndpointRegistry = kafkaListenerEndpointRegistry;
        this.kafkaPublisherConsumer = kafkaPublisherConsumer;
        this.kafkaEndpointProperties = kafkaEndpointProperties;
    }

    public void start() {
        // Программно регистрируем и запускаем KafkaListener
        MethodKafkaListenerEndpoint<String, String> kafkaListenerEndpoint = createEndPoint(kafkaEndpointProperties);
        kafkaListenerEndpointRegistry.registerListenerContainer(kafkaListenerEndpoint, kafkaListenerContainerFactory, true);
    }


    public void stop() {
        // Останавливаем KafkaListener, получая его по Id из kafkaListenerEndpointRegistry
        kafkaListenerEndpointRegistry.getListenerContainer(kafkaEndpointProperties.getId()).stop();
    }


    private MethodKafkaListenerEndpoint<String, String> createEndPoint(KafkaEndpointProperties kafkaEndpointProperties) {
        try {
            MethodKafkaListenerEndpoint<String, String> kafkaListenerEndpoint =
                    new MethodKafkaListenerEndpoint<>();
            kafkaListenerEndpoint.setId(kafkaEndpointProperties.getId());
            kafkaListenerEndpoint.setGroupId(kafkaEndpointProperties.getGroupId());
            kafkaListenerEndpoint.setAutoStartup(true);
            kafkaListenerEndpoint.setTopics(kafkaEndpointProperties.getTopic());
            kafkaListenerEndpoint.setMessageHandlerMethodFactory(new DefaultMessageHandlerMethodFactory());
            kafkaListenerEndpoint.setBean(kafkaPublisherConsumer);
            kafkaListenerEndpoint.setMethod(kafkaPublisherConsumer.getClass().getMethod("onMessage", String.class));
            return kafkaListenerEndpoint;
        } catch (NoSuchMethodException e) {
            throw new KafkaEndpointCreationException(e);
        }
    }
}
