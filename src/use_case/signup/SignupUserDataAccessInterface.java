package use_case.signup;

import entity.User;

public interface SignupUserDataAccessInterface {
    boolean existsByUsername(String identifier);
    void save(User user);
}
