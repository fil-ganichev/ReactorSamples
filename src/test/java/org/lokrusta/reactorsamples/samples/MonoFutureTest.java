package org.lokrusta.reactorsamples.samples;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

public class MonoFutureTest {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Test
    void furure_not_block_reactive_pool() throws InterruptedException, ExecutionException {
        log.info("Test started");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Mono<Integer> longTask = Mono.fromSupplier(() -> {
                    log.info("longTask executing..");
                    return Mono.just(1).delayElement(Duration.ofMillis(2000));
                })
                .flatMap(Function.identity())
                .doOnNext(i -> log.info("longTask value {}", i))
                .subscribeOn(Schedulers.fromExecutor(executorService));

        Executors.newSingleThreadExecutor().submit(() -> {
            Executors.newSingleThreadExecutor().submit(() -> {
                for (int i = 0; i < 100000; i++) {
                    int finalI = i;
                    executorService.submit(() -> {
                        log.info("i={}", finalI);
                    });
                    if ((i + 1) % 100 == 0) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        });

        log.info("Before calling Future.get");
        Integer res = longTask.toFuture().get();
        log.info("Test completed with result {}", res);
    }
}
