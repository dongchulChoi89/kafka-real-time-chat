package com.choi.kafka.controller;

import com.choi.kafka.constant.KafkaConstants;
import com.choi.kafka.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

// this endpoint injects the KafkaTemplate configured earlier and sends a message to myTopic when a GET request is made to /kafka/produce
@RestController
public class KafkaController {
    @Autowired
    private KafkaTemplate<String, Message> template;

    // Kafka
    @PostMapping(value = "/api/send", consumes = "application/json", produces = "application/json")
    public void sendMessage(@RequestBody Message message) {
        message.setTimestamp(LocalDateTime.now().toString());
        try {
            template.send(KafkaConstants.KAFKA_TOPIC, message).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Websocket
    // Annotation for mapping a Message onto a message-handling method by matching the declared patterns to a destination extracted from the message.
    // STOMP over WebSocket -- the value is turned into a message and sent to a default response destination or to a custom destination specified with an @SendTo
    @MessageMapping("/sendMessage")
    @SendTo("/topic/group")
    public Message broadcastGroupMessage(@Payload Message message) {
        return message; // send this message to all the subscribers
    }

    @MessageMapping("/newUser")
    @SendTo("/topic/group")
    public Message addUser(@Payload Message message,
                           SimpMessageHeaderAccessor headerAccessor) {
        // Add user in web socket session
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        return message;
    }

}
