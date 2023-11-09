package entity;

import java.util.ArrayList;

public class Conversation {
    private final ArrayList<Message> messages;

    private final long id;

    Conversation(long id) {
        messages = new ArrayList<>();
        this.id = id;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public long getId() { return id; }

    public void sendMessage(Message message) {
        messages.add(message);
    }
}
