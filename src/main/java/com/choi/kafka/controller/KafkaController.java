package com.choi.kafka.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// this endpoint injects the KafkaTemplate configured earlier and sends a message to myTopic when a GET request is made to /kafka/produce
@RestController
public class KafkaController {
    private KafkaTemplate<String, String> template;

    public KafkaController(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    @GetMapping("/kafka/produce")
    public void produce(@RequestParam String message) {
        template.send("myTopic", message);
    }
}
