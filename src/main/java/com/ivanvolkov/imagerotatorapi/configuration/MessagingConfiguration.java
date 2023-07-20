package com.ivanvolkov.imagerotatorapi.configuration;

import com.ivanvolkov.imagerotatorapi.domain.TaskMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import java.util.function.Supplier;

@Configuration
@Slf4j
public class MessagingConfiguration {

    @Bean
    public Sinks.Many<Message<TaskMessage>> many() {
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<Message<TaskMessage>>> supply(Sinks.Many<Message<TaskMessage>> many) {
        return () -> many.asFlux()
                .doOnNext(m -> log.info("Sending message {}", m))
                .doOnError(t -> log.error("Error occurred ", t));
    }
}
