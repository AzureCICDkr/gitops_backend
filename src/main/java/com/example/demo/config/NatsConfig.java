package com.example.demo.config;

import io.nats.client.Connection;
import io.nats.client.Nats;
import io.nats.client.Options;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NatsConfig {

    Logger log = LoggerFactory.getLogger(NatsConfig.class);

    @Bean
    public Connection natsConnection(@Value("${nats.servers}") String natsServer) throws Exception {

        Options options = new Options.Builder()
                .server(natsServer)
                .maxReconnects(-1)
                .connectionName("spring-boot-jetstream")
                .build();
        log.atDebug().log(options.getConnectionName());
        return Nats.connect(options);
    }
}
