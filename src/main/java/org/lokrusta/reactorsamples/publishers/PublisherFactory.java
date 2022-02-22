package org.lokrusta.reactorsamples.publishers;

import reactor.core.publisher.Flux;

public interface PublisherFactory<T> {

    Flux<T> createPublisher();
}
