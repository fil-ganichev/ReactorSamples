package org.lokrusta.reactorsamples.config.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.util.Map;

@Getter
@Accessors(fluent = true)
@ConfigurationProperties
@AllArgsConstructor
@ConstructorBinding
public class KafkaProperties {

    private Map<String, Object> kafka;
}
