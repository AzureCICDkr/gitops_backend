package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.config.JetStreamPublisher;
import com.example.demo.dto.ChatMessage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;




@RestController
@RequestMapping(value = "/chat")
public class ChatController {


    Logger log = LoggerFactory.getLogger(ChatController.class);

    @Value("${spring.ai.openai.api-key:NULL}")
    private String apiKey;

//    @PostConstruct
//    void check() {
//        System.out.println("OPENAI KEY = " + apiKey);
//    }


    private final JetStreamPublisher publisher;

    @Autowired
    public ChatController(JetStreamPublisher publisher) {
        this.publisher = publisher;
    }

    @Operation(
        summary = "채팅 응답 전송",
        description = "서버가 응답 채팅을 전송합니다.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ChatMessage.class)
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "성공",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ChatMessage.class)
                        )
                )
        }
    )
    @RequestMapping(value="/publish", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> chatResponse(ChatMessage chatMessage, HttpServletRequest req, HttpServletResponse res){

        Map<String, Object> map = new HashMap<>();

        try {
              HttpSession session = req.getSession(true);
              chatMessage.setSessionId(session.getId());
              publisher.publish(chatMessage);
              map.put("message","success");
              map.put("data",chatMessage);
              log.debug("=============== chatMessage={}",chatMessage.toString());
              log.debug("Session={}",session);

        } catch (Exception e) {


              map.put("message","failed");
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);

        }


       return new ResponseEntity<>(map, HttpStatus.OK);

    }





}

