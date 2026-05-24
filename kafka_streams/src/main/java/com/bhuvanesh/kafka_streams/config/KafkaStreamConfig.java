package com.bhuvanesh.kafka_streams.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaStreamConfig {
    @Bean
    public NewTopic createTransactionTopic(){
        return TopicBuilder.name("transaction")
                        .partitions(3)
                        .replicas(1)
                        .build();
    }

    @Bean
    public NewTopic createFraudAlertTopic() {
        return TopicBuilder.name("fraud-alert")
                        .partitions(3)
                        .replicas(2)
                        .build();
    }
}
