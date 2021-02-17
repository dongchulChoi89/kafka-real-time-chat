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

/*
    // STOMP over Websocket
    // the returned value is turned into a Spring message and sent to a default response destination(/topic/[@MessageMapping end point])
    // or to a custom destination specified with an @SendTo
    @MessageMapping("/sendMessage") // client send SEND STOMP message
    @SendTo("/topic/group")
    public Message broadcastGroupMessage(@Payload Message message) {
        return message; // send this message to all the subscribers
    }
*/
}
