package com.example.demo.config;

import io.nats.client.Connection;
import io.nats.client.JetStream;
import io.nats.client.JetStreamManagement;
import io.nats.client.api.StreamConfiguration;
import io.nats.client.api.StorageType;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class JetStreamInitializer {

    Logger log = LoggerFactory.getLogger(JetStreamInitializer.class);

    private final Connection connection;
    private final String streamName;
    private final String subject;

    public JetStreamInitializer(
            Connection connection,
            @Value("${nats.stream}") String streamName,
            @Value("${nats.subject}") String subject
    ) {
        this.connection = connection;
        this.streamName = streamName;
        this.subject = subject;
    }

    @Bean
    public JetStream jetStream() throws IOException {
        // ❗ 외부 호출 없음
        return connection.jetStream();
    }

    @PostConstruct
    public void initStream() {
        try {
            JetStreamManagement jsm = connection.jetStreamManagement();

            if (!jsm.getStreamNames().contains(streamName)) {
                StreamConfiguration streamConfig = StreamConfiguration.builder()
                        .name(streamName)
                        .subjects(subject)
                        .storageType(StorageType.File)
                        .build();

                jsm.addStream(streamConfig);
            }


        } catch (Exception e) {

            log.error("JetStream init failed", e);
        }
    }
}
