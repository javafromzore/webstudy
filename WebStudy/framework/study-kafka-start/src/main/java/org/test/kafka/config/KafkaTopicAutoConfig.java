package org.test.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicAutoConfig {
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("topicA")
                .partitions(1)
                .replicas(1)
                .build();
    }
}