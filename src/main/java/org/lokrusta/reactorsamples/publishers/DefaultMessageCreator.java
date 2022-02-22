package org.lokrusta.reactorsamples.publishers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lokrusta.reactorsamples.publishers.exception.MessageCreationException;

public class DefaultMessageCreator<T> implements MessageCreator<T> {

    private final ObjectMapper objectMapper;
    private final Class<T> messageClass;

    public DefaultMessageCreator(ObjectMapper objectMapper,
                                 Class<T> messageClass) {
        this.objectMapper = objectMapper;
        this.messageClass = messageClass;
    }

    @Override
    public T createMessage(String rawMessage) {
        try {
            return objectMapper.readValue(rawMessage, messageClass);
        } catch (JsonProcessingException e) {
            throw new MessageCreationException(e);
        }
    }
}
