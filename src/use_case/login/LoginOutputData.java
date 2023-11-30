package use_case.login;

import entity.Conversation;

public class LoginOutputData {

    private final String username;
    private final Conversation conversation;
    private boolean useCaseFailed;

    public LoginOutputData(String username, Conversation conversation, boolean useCaseFailed) {
        this.username = username;
        this.conversation = conversation;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public Conversation getConversation() { return conversation; }

}
