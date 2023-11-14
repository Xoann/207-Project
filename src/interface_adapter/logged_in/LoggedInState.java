package interface_adapter.logged_in;

public class LoggedInState {
    private String username = "test";
    private long conversationId = 0L;
    private String message = "";
    private String recommendationError = null;

    public LoggedInState(LoggedInState copy) {
        this.username = copy.username;
        this.conversationId = copy.conversationId;
        this.message = copy.message;
        this.recommendationError = copy.recommendationError;
    }

    public LoggedInState() {}

    public String getUsername() { return username; }

    public long getConversationId() { return conversationId; }

    public String getMessage() { return message; }

    public String getRecommendationError() { return recommendationError; }

    public void setUsername(String username) { this.username = username; }

    public void setConversationId(long conversationId) { this.conversationId = conversationId; }

    public void setMessage(String message) { this.message = message; }

    public void setRecommendationError(String recommendationError) { this.recommendationError = recommendationError; }
}