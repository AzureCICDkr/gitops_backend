package com.example.demo.controller;

import com.example.demo.service.SseEmitterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/sse")
public class SseController {

    private final SseEmitterService sseEmitterService;

    @Autowired
    public SseController(SseEmitterService service) {
        this.sseEmitterService = service;
    }

    @GetMapping(value = "/subscribe/{sessionId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@PathVariable String sessionId) {
        return sseEmitterService.subscribe(sessionId);
    }





}
