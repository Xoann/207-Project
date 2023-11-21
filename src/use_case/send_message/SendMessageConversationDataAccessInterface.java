package use_case.send_message;

import entity.Message;

public interface SendMessageConversationDataAccessInterface {
    void save(long id, Message message);
}
