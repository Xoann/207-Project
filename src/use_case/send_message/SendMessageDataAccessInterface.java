package use_case.send_message;

import entity.Conversation;
import entity.Message;

public interface SendMessageDataAccessInterface {
    void save(long id, Message message);
}
