package com.choi.kafka.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String sender;
    private String content;
    private String timestamp;

    @Override
    public String toString() {
        return "Message{" +
                "sender='" + sender + '\'' +
                ", content='" + content + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
