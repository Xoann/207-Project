package use_case.recommendation;

/**
 * A class that represents input data to make a recommendation
 */
public class RecommendationInputData {
    final private long conversationId;
    final private String recommendationInstructions;
    final private String username;

    /**
     * Constructs the input data for a recommendation given a conversation and an api key
     * @param conversationId the id for the conversation
     * @param recommendationInstructions the instruction to the large language model for generating a recommendation
     * @param username the user's username
     */
    public RecommendationInputData(long conversationId, String recommendationInstructions, String username) {
        this.conversationId = conversationId;
        this.recommendationInstructions = recommendationInstructions;
        this.username = username;
    }

    public long getConversationId() { return this.conversationId; }

    public String getRecommendationInstructions() { return this.recommendationInstructions; }

    public String getUsername() { return this.username; }
}
