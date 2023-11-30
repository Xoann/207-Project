package entity;

import javax.xml.stream.events.Comment;
import java.time.LocalDateTime;

public class CommonMessageFactory implements MessageFactory {
    @Override
    public Message create(String message, User sender) {
        return new CommonMessage(message, sender);
    }

    @Override
    public Message create(String message, User sender, String timeSent) {
        return new CommonMessage(message, sender, timeSent);
    }
}
