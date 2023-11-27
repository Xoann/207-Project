package use_case.send_message;

import entity.User;

public class SendMessageInputData {

    final private String message;

    final private User sender;

    final private long conversationId;

    public SendMessageInputData(String message, User sender, long conversationId) {
        this.message = message;
        this.sender = sender;
        this.conversationId = conversationId;
    }

    String getMessage() { return message; }

    User getSender() { return sender; }

    public long getId() { return conversationId; }
}
