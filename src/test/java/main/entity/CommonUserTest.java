package main.entity;

import org.junit.jupiter.api.Test;

class CommonUserTest {

    @Test
    void getUsername() {
        User user = new CommonUser("name", "password");
        assert user.getUsername().equals("name");
    }

    @Test
    void getPassword() {
        User user = new CommonUser("name", "password");
        assert user.getPassword().equals("password");
    }

    @Test
    void getFriends() {
        User user = new CommonUser("name", "password");
        User friend1 = new CommonUser("bob", "password");
        User friend2 = new CommonUser("bobby", "password");
        user.addFriend(friend1);
        user.addFriend(friend2);
        assert user.getFriends().get(0).getUsername().equals("bob");
        assert user.getFriends().get(1).getUsername().equals("bobby");
    }

    @Test
    void setUsername() {
        User user = new CommonUser("name", "password");
        assert user.getUsername().equals("name");
        user.setUsername("new");
        assert user.getUsername().equals("new");
    }

    @Test
    void setPassword() {
        User user = new CommonUser("name", "password");
        assert user.getPassword().equals("password");
        user.setUsername("new");
        assert user.getPassword().equals("new");
    }

    @Test
    void addFriend() {
        User user = new CommonUser("name", "password");
        User friend = new CommonUser("bob", "password");
        user.addFriend(friend);
        assert user.getFriends().get(0).getUsername().equals("bob");
    }

    @Test
    void removeFriend() {
        User user = new CommonUser("name", "password");
        User friend = new CommonUser("bob", "password");
        user.addFriend(friend);
        user.removeFriend(friend);
        assert user.getFriends().isEmpty();
    }
}