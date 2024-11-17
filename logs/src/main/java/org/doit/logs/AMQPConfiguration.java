package org.doit.logs;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfiguration {

    @Bean
    public TopicExchange logsExchange(@Value("${amqp.exchange.logs}") String exchangeName) {
        return ExchangeBuilder.topicExchange(exchangeName)
                .durable(true)
                .build();
    }
    @Bean
    public Queue logsQueue(@Value("${amqp.queue.logs}") String queueName) {
        return QueueBuilder.durable(queueName).build();
    }
    @Bean
    public Binding logsBinding(final Queue logsQueue,
                               final TopicExchange logsExchange) {
        return BindingBuilder.bind(logsQueue)
                .to(logsExchange).with("#");
    }
}
