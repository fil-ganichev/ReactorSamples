package org.lokrusta.reactorsamples.samples;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lokrusta.reactorsamples.samples.tests.BackpressureConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BackpressureConsumerTest {

    @Autowired
    private BackpressureConsumer backpressureConsumer;

    @Test
    void consumeTest() {
        System.out.println("**********");
        backpressureConsumer.consume();
    }
}
