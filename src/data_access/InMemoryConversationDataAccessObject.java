package data_access;

import entity.*;
import use_case.send_message.SendMessageDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryConversationDataAccessObject implements SendMessageDataAccessInterface {

    private final Map<Long, Conversation> conversations = new HashMap<>();

    public InMemoryConversationDataAccessObject() {
        Conversation convo = new Conversation(0);
        User test2 = new CommonUser("test2", "test2", "put_key_here");
        User test3 = new CommonUser("test3", "test3", "put_key_here");
        convo.sendMessage(new CommonMessage("Hey guys, how are you doing?", test2));
        convo.sendMessage(new CommonMessage("I'm good!", test3));
        conversations.put(convo.getId(), convo);
    }
    @Override
    public void save(long id, Message message) {
        conversations.get(id).sendMessage(message);
    }
}
