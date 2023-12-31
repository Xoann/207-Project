package use_case.send_message;

import entity.CommonMessageFactory;
import entity.CommonUserFactory;
import entity.Message;
import entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SendMessageOutputDataTest {
    User sender = new CommonUserFactory().create("John", "password", "apiKey");
    Message message = new CommonMessageFactory().create("hi", sender);
    SendMessageOutputData data = new SendMessageOutputData(message, false);

    @Test
    void getMessage() {
        assertEquals(data.getMessage(), message);
        assertEquals(data.getMessage().getMessage(), "hi");
    }
}