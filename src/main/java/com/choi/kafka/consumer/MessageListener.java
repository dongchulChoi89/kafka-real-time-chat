package com.choi.kafka.consumer;

import com.choi.kafka.constant.KafkaConstants;
import com.choi.kafka.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

// This listner class listens to changes inside the 'kafka-chat' topic.
// It does so by using the KafkaListener annotation.
// Using SimpMessagingTemplate, you can send messages directly from any component of application to brokerChannel
@Component // register this class object to bean; my defined class using @Component, else using @Configuration, and @Bean
public class MessageListener {
    @Autowired
    SimpMessagingTemplate template; // websocket API

    @KafkaListener(topics = KafkaConstants.KAFKA_TOPIC, groupId = KafkaConstants.GROUP_ID)
    public void listen(Message message) {
        System.out.println("sending via kafka listener..");
        template.convertAndSend("/topic/group", message);
    }
}
