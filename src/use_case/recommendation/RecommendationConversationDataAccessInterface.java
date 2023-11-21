package use_case.recommendation;

import entity.Conversation;

/**
 * The interface that describes the data store for conversations
 */
public interface RecommendationConversationDataAccessInterface {
    Conversation get(long id);

    boolean existsById(long id);
}
