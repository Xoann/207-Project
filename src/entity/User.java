package entity;

import java.util.List;

/**
 * User is the interface that describes the entities that can partake in a conversation
 */
public interface User {

    String getUsername();

    String getPassword();

    List<User> getFriends();

    void setUsername(String username);

    void setPassword(String password);

    void addFriend(User user);

    void removeFriend(User user);
}
