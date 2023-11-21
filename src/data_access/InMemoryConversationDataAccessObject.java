package data_access;

import entity.CommonMessage;
import entity.CommonUser;
import entity.Conversation;
import entity.User;
import use_case.recommendation.RecommendationConversationDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryConversationDataAccessObject implements RecommendationConversationDataAccessInterface {
    private final Map<Long, Conversation> conversations = new HashMap<>();

    /**
     * Construct a conversation DAO with dummy data
     */
    public InMemoryConversationDataAccessObject() {
        // convo is the default conversation (default conversationId in LoggedInState class)
        Conversation convo = new Conversation(0);
        User test2 = new CommonUser("test2", "test2", "put_key_here");
        User test3 = new CommonUser("test3", "test3", "put_key_here");
        convo.sendMessage(new CommonMessage("Hey guys, how are you doing?", test2));
        convo.sendMessage(new CommonMessage("I'm good!", test3));
        conversations.put(convo.getId(), convo);
    }

    /**
     * @param id the conversation's id
     * @return the conversation
     */
    @Override
    public Conversation get(long id) {
        return conversations.get(id);
    }

    /**
     * @param id the conversation's id
     * @return if the conversation exists
     */
    @Override
    public boolean existsById(long id) {
        return conversations.containsKey(id);
    }
}
