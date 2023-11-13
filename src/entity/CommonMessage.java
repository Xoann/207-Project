package entity;

import java.time.LocalDateTime;

public class CommonMessage implements Message{
    private final String message;
    private final User sender;
    private final LocalDateTime timeSent;

    CommonMessage(String message, User sender){
        this.message = message;
        this.sender = sender;
        this.timeSent = LocalDateTime.now();
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public LocalDateTime getTimeSent() {
        return timeSent;
    }

    public User getSender() {
        return sender;
    }
}
