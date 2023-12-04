package use_case.login;

import entity.Conversation;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class LoginOutputDataTest {
    long l = 20;
    Conversation conversation = new Conversation(l);
    LoginOutputData loginOutputData = new LoginOutputData("bobby", conversation, false);
    @Test
    void getUsername() {
        assertEquals(loginOutputData.getUsername(), "bobby");
    }

    @Test
    void getConversation() {assertEquals(loginOutputData.getConversation(), conversation); }
}