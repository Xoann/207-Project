package use_case.recommendation;

import entity.User;

/**
 * The interface that describes the data store for users
 */
public interface RecommendationUserDataAccessInterface {
    User get(String username);

    void save(User user);

    boolean existsByUsername(String username);
}
