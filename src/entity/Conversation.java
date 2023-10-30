package entity;

import java.util.ArrayList;

public class Conversation {
    private final ArrayList<Message> messages;

    Conversation() {
        messages = new ArrayList<>();
    }

     public ArrayList<Message> getMessages() {
        return messages;
    }

    public void sendMessage(Message message) {
        messages.add(message);
    }
}
