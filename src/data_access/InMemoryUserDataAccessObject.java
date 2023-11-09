package data_access;

import entity.User;
import use_case.recommendation.RecommendationUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements RecommendationUserDataAccessInterface {
    private final Map<String, User> users = new HashMap<>();

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
}
