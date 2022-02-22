package org.lokrusta.reactorsamples;

import org.lokrusta.reactorsamples.config.kafka.KafkaBrokerConfiguration;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootConfiguration
@ConfigurationPropertiesScan
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
        classes = KafkaBrokerConfiguration.class))
public class StartConfiguration {
}
