package com.example.demo.service;

import com.example.demo.config.JetStreamPublisher;
import com.example.demo.dto.ChatMessage;
import com.google.gson.Gson;

import io.nats.client.Connection;
import io.nats.client.JetStream;
import io.nats.client.JetStreamSubscription;
import io.nats.client.Message;
import io.nats.client.PullSubscribeOptions;

import jakarta.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;



@Component
public class JetStreamConsumer {

    Logger log = LoggerFactory.getLogger(JetStreamConsumer.class);

    private final JetStream jetStream;
    private final Gson gson = new Gson();
    private final SseEmitterService emitterService;
    private final ChatClient chatClient;

    @Value("${nats.subject}")
    private String subject;

    public JetStreamConsumer(
            Connection connection,
            SseEmitterService emitterService,
            ChatClient chatClient
    ) throws Exception {
        this.jetStream = connection.jetStream();
        this.emitterService = emitterService;
        this.chatClient = chatClient;
    }

    @PostConstruct
    public void start() {
        new Thread(this::consumeLoop).start();
    }

    private void consumeLoop() {
        try {
            PullSubscribeOptions options = PullSubscribeOptions.builder()
                    .durable("chat-worker")
                    .build();

            JetStreamSubscription sub =
                    jetStream.subscribe(subject, options);

            while (true) {
                for (Message msg : sub.fetch(1, Duration.ofSeconds(5))) {
                    handle(msg);
                }
            }

        } catch (Exception e) {
            // 로그만 찍고 루프 유지
        }
    }

    private void handle(Message msg) {
        try {
            ChatMessage req = gson.fromJson(
                    new String(msg.getData()),
                    ChatMessage.class
            );

            // 🔥 OpenAI 호출
            String result = chatClient.prompt(req.getMessage())
                    .call()
                    .content();

            log.debug("OpenAI result={}",result);
            // 🔥 SSE push
            emitterService.send(req.getSessionId(), result);

            msg.ack(); // ✅ JetStream ACK

        } catch (Exception e) {
            msg.nak(); // ❌ 재시도
        }
    }
}
