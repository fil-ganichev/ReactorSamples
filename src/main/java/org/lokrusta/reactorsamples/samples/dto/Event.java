package org.lokrusta.reactorsamples.samples.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class Event {

    Instant evetDate;
    String id;
    String data;
}
