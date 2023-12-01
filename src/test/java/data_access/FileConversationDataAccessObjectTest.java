package data_access;

import entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;


import static org.junit.jupiter.api.Assertions.*;

class FileConversationDataAccessObjectTest {

    FileConversationDataAccessObject conversationDataAccessObject = new FileConversationDataAccessObject();
    @BeforeEach
    void setUp() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("conversations/100.txt"))) {
            writer.write("2023-11-28 16:40,test,hi.\n");
            writer.write("2023-11-28 16:41,test2,hi there, who are you?\n");
            writer.write("2023-11-28 16:42,test3,hey, that's test!\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
        File file = new File("conversations/test.txt");
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted successfully");
            } else {
                System.out.println("File could not be deleted");
            }
        }
    }


    @Test
    void getCorrectMessage() {
        Conversation conversation = conversationDataAccessObject.get(100);
        ArrayList<Message> messages = conversation.getMessages();
        assertEquals("hi.", messages.get(0).getMessage());
        assertEquals("hi there, who are you?", messages.get(1).getMessage());
        assertEquals("hey, that's test!", messages.get(2).getMessage());
    }

    @Test
    void getCorrectUsers() {
        Conversation conversation = conversationDataAccessObject.get(100);
        ArrayList<Message> messages = conversation.getMessages();
        assertEquals("test", messages.get(0).getSender().getUsername());
        assertEquals("test2", messages.get(1).getSender().getUsername());
        assertEquals("test3", messages.get(2).getSender().getUsername());
    }

    @Test
    void getCorrectDateTimes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Conversation conversation = conversationDataAccessObject.get(100);
        ArrayList<Message> messages = conversation.getMessages();
        assertEquals(LocalDateTime.parse("2023-11-28 16:40", formatter), messages.get(0).getTimeSent());
        assertEquals(LocalDateTime.parse("2023-11-28 16:41", formatter), messages.get(1).getTimeSent());
        assertEquals(LocalDateTime.parse("2023-11-28 16:42", formatter), messages.get(2).getTimeSent());
    }

    @Test
    void existsById() {
        assert (conversationDataAccessObject.existsById(100));
    }

    @Test
    void save() {
        User sender = new CommonUserFactory().create("test", "password", "apikey");
        Message message = new CommonMessageFactory().create("This is a test", sender);
        conversationDataAccessObject.save(100, message);
        Conversation conversation = conversationDataAccessObject.get(100);
        try {
            Message fourthMessage = conversation.getMessages().get(3);
            assertEquals("This is a test", fourthMessage.getMessage());
            assertEquals("test", fourthMessage.getSender().getUsername());
        } catch (IndexOutOfBoundsException e) {
            fail("Message did not save");
        }
    }

}