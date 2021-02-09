package com.choi.kafka.controller;

import com.choi.kafka.constant.KafkaConstants;
import com.choi.kafka.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

// this endpoint injects the KafkaTemplate configured earlier and sends a message to myTopic when a GET request is made to /kafka/produce
@RestController
public class KafkaController {
    @Autowired
    private KafkaTemplate<String, Message> template;

    @PostMapping(value = "/api/send", consumes = "application/json", produces = "application/json")
    public void sendMessage(@RequestBody Message message) {
        message.setTimestamp(LocalDateTime.now().toString());
        try {
            template.send(KafkaConstants.KAFKA_TOPIC, message).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
