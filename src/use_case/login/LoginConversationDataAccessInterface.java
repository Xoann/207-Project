package use_case.login;

import entity.Conversation;
import entity.Message;

public interface LoginConversationDataAccessInterface {
    Conversation get(long id);
    boolean existsById(long id);
}
