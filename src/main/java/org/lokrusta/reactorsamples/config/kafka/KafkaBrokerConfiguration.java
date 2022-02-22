package org.lokrusta.reactorsamples.config.kafka;

import org.lokrusta.reactorsamples.utils.YamlPropertySourceFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.test.EmbeddedKafkaBroker;

import java.util.TreeMap;

@Configuration
@ConfigurationPropertiesScan
@PropertySource(value = "classpath:application.yml", factory = YamlPropertySourceFactory.class)
public class KafkaBrokerConfiguration {

    @Bean(destroyMethod = "destroy")
    public EmbeddedKafkaBroker embeddedKafkaBroker(EmbeededKafkaProperties embeededKafkaProperties) {
        return new EmbeddedKafkaBroker(1, true, 1)
                .brokerProperties(embeededKafkaProperties.embeededkafka());
    }
}
