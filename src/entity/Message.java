package entity;

import java.time.LocalDateTime;

public interface Message {
    String getMessage();
    LocalDateTime getTimeSent();
}
