package use_case.send_message;

import entity.CommonUserFactory;
import entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SendMessageInputDataTest {

    User sender = new CommonUserFactory().create("John", "password", "apikey");

    SendMessageInputData data = new SendMessageInputData("hi", sender, 0);
    @Test
    void getMessage() {
        assertEquals(data.getMessage(), "hi");
    }

    @Test
    void getSender() {
        assertEquals(data.getSender(), sender);
    }

    @Test
    void getId() {
        assertEquals(data.getId(), 0);
    }
}