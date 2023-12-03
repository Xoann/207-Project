package interface_adapter.logged_in;

import entity.Conversation;
import java.util.ArrayList;

public class LoggedInState {
    private String username = "test";
    private long conversationId = 0L;
    private String conversation = "";
    private String message = "";
    private String recommendationError = null;
    private String sendError = null;

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

    public String getConversation() { return conversation; }

    public String getSendError() { return sendError; }

    public void setUsername(String username) { this.username = username; }

    public void setConversationId(long conversationId) { this.conversationId = conversationId; }

    public void setMessage(String message) { this.message = message; }

    public void setRecommendationError(String recommendationError) { this.recommendationError = recommendationError; }

    public void setConversation(String conversation) { this.conversation = conversation; }

    public void setSendError(String error) { this.sendError = error; }
}
