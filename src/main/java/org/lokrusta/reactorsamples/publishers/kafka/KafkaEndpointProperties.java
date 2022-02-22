package org.lokrusta.reactorsamples.publishers.kafka;

import lombok.Data;

@Data
public class KafkaEndpointProperties {

    private String id;
    private String groupId;
    private String topic;
}
