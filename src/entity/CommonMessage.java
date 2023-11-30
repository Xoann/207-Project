package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public CommonMessage(String message, User sender, String timeSent){
        this.message = message;
        this.sender = sender;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.timeSent = LocalDateTime.parse(timeSent, formatter);
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
