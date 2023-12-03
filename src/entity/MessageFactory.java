package entity;

import java.time.LocalDateTime;

public interface MessageFactory {
    Message create(String message, User sender);
    Message create(String message, User sender, String timeSent);
}
