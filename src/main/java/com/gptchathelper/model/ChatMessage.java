package com.gptchathelper.model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class ChatMessage {


    private String content;
    private String sender;
    private MessageType type;

    private String id;

    private String time;

    public enum MessageType {
        CHAT, LEAVE, JOIN, GENERATE_RESPONSE
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String generateId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
    public String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd/HH/mm/ss");
        return now.format(formatter);
    }

}
