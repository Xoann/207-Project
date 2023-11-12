package use_case.send_message;

import entity.User;

public class SendMessageInputData {

    final private String message;

    final private User sender;

    final private long id;

    public SendMessageInputData(String message, User sender, long id) {
        this.message = message;
        this.sender = sender;
        this.id = id;
    }

    String getMessage() { return message; }

    User getSender() { return sender; }

    public long getId() { return id; }
}
