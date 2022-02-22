package org.lokrusta.reactorsamples.generator;

public class DefaultKafkaMessageGenerator implements KafkaMessageGenerator<String> {

    private static final String MESSAGE_TEXT_PATTERN = "Next message. Item number is %d";

    @Override
    public String nextMessage(int next) {
        return String.format(MESSAGE_TEXT_PATTERN, next);
    }
}
