package entity;

import java.util.ArrayList;

public class Conversation {
    ArrayList<Message> messages;

    ArrayList<Message> getMessages() {
        return messages;
    }

    void sendMessage(Message message) {
        messages.add(message);
    }
}
