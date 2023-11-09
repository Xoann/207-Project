package data_access;

import entity.Conversation;
import use_case.recommendation.RecommendationConversationDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryConversationDataAccessObject implements RecommendationConversationDataAccessInterface {
    private final Map<Long, Conversation> conversations = new HashMap<>();

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
