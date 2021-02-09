package com.choi.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// This class listens to changes inside the myTopic topic.
// It does so by using the KafkaListener annotation.
// Every time a new message is sent from a producer to the topic, your app receives a message inside this class.
// It adds a message to the list of messages received, making it available to other classes through the getMessages() method
@Component // register this class object to bean; my defined class using @Component, else using @Configuration, and @Bean
public class MyTopicConsumer {
    private final List<String> messages = new ArrayList<>();

    @KafkaListener(topics = "myTopic", groupId = "kafka-sandbox")
    public void listen(String message) {
        synchronized (messages) {
            messages.add(message);
        }
    }

    public List<String> getMessages() {
        return messages;
    }
}
