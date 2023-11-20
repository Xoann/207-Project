package entity;

import java.time.LocalDateTime;

public class CommonMessage implements Message{
    private final String message;
    private final User sender;
    private final LocalDateTime timeSent;

    // TODO can remove public later on. This was added to add dummy data in the DAOs
    public CommonMessage(String message, User sender){
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
