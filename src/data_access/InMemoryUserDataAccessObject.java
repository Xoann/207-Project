package data_access;

import entity.CommonUser;
import entity.User;
import use_case.login.LoginUserDataAccessInterface;
import use_case.send_message.SendMessageUserDataAccessInterface;
import use_case.recommendation.RecommendationUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements SendMessageUserDataAccessInterface, RecommendationUserDataAccessInterface, LoginUserDataAccessInterface, SignupUserDataAccessInterface {
    private final Map<String, User> users = new HashMap<>();

    /**
     * Construct a user DAO with dummy data
     */
    public InMemoryUserDataAccessObject() {
        // test is the default user (default username in LoggedInState class)
        User test = new CommonUser("test", "test", "put_key_here");
        User test2 = new CommonUser("test2", "test2", "put_key_here");
        User test3 = new CommonUser("test3", "test3", "put_key_here");
        users.put(test.getUsername(),test);
        users.put(test2.getUsername(), test2);
        users.put(test3.getUsername(), test3);
    }

    /**
     * @param username the user's username
     * @return the user
     */
    @Override
    public User get(String username) {
        return users.get(username);
    }

    /**
     * @param username the user's username
     * @return if the user exists
     */
    @Override
    public boolean existsByUsername(String username) {
        return users.containsKey(username);
    }

    @Override
    public void save(User user) {
        users.put(user.getUsername(), user);
    }


}
