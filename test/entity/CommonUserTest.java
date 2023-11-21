package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommonUserTest {
    User user;
    @BeforeEach
    void setup() {
        user = new CommonUser("name", "password", "apiKey");
    }

    @Test
    void getUsername() {
        assert user.getUsername().equals("name");
    }

    @Test
    void getPassword() {
        assert user.getPassword().equals("password");
    }

    @Test
    void getFriends() {
        User friend1 = new CommonUser("bob", "passwordBob", "apiKeyBob");
        User friend2 = new CommonUser("bobby", "passwordBobby", "apiKeyBobby");
        user.addFriend(friend1);
        user.addFriend(friend2);
        assert user.getFriends().get(0).getUsername().equals("bob");
        assert user.getFriends().get(1).getUsername().equals("bobby");
    }

    @Test
    void getApiKey() {
        assert user.getApiKey().equals("apiKey");
    }

    @Test
    void setUsername() {
        assert user.getUsername().equals("name");
        user.setUsername("new");
        assert user.getUsername().equals("new");
    }

    @Test
    void setPassword() {
        assert user.getPassword().equals("password");
        user.setPassword("new");
        assert user.getPassword().equals("new");
    }

    @Test
    void addFriend() {
        User friend = new CommonUser("bob", "passwordBob", "apiKeyBob");
        user.addFriend(friend);
        assert user.getFriends().get(0).getUsername().equals("bob");
    }

    @Test
    void removeFriend() {
        User friend = new CommonUser("bob", "passwordBob", "apiKeyBob");
        user.addFriend(friend);
        user.removeFriend(friend);
        assert user.getFriends().isEmpty();
    }

    @Test
    void setApiKey() {
        assert user.getApiKey().equals("apiKey");
        user.setApiKey("newApiKey");
        assert user.getApiKey().equals("newApiKey");
    }
}