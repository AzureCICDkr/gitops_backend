package com.example.demo.config;



import com.google.gson.Gson;
import com.example.demo.dto.ChatMessage;

import io.nats.client.Connection;
import io.nats.client.JetStream;

import io.nats.client.api.PublishAck;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.UUID;

@Component
public class JetStreamPublisher {

    Logger log = LoggerFactory.getLogger(JetStreamPublisher.class);

    private final JetStream jetStream;
    private final Gson gson = new Gson();
    private final String subject;

    public JetStreamPublisher(Connection connection, @Value("${nats.subject}") String subject) throws Exception {
        this.jetStream = connection.jetStream();
        this.subject = subject;
    }

    public void publish(ChatMessage req) {
        try {
            req.setTimestamp(Instant.now().toString());
            req.setMessageId(UUID.randomUUID().toString());

            byte[] payload = gson
                    .toJson(req)
                    .getBytes(StandardCharsets.UTF_8);

            PublishAck ack = jetStream.publish(
                    subject,
                    payload
            );

            log.info(
                    "JetStream publish OK stream={}, seq={}, msgId={}, message={}",
                    ack.getStream(),
                    ack.getSeqno(),
                    req.getMessageId(),
                    req.getMessage()
            );

            log.info("ACK stream={}, seq={}",ack.getStream(), ack.getSeqno());

        } catch (Exception e) {
            log.error("JetStream publish failed subject={}", subject, e);
            throw new RuntimeException(e);
        }
    }
}
