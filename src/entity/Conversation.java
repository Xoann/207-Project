package entity;

import java.util.ArrayList;

public class Conversation {
    private final ArrayList<Message> messages;

    private final long id;

    public Conversation(long id) {
        messages = new ArrayList<>();
        this.id = id;
    }

     public ArrayList<Message> getMessages() {
        return messages;
    }

    public void sendMessage(Message message) {
        messages.add(message);
    }

    public long getId() { return id; }
}
