package entity;

import java.util.ArrayList;

public class Conversation {
    private final ArrayList<Message> messages;

    private final long id;

    // TODO can remove public later on. This was added to add dummy data in the DAOs
    public Conversation(long id) {
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
