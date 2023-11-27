package use_case.reset_password;

import entity.User;

public interface ResetPasswordDataAccessInterface {
    User get(String username);
    boolean existsByUsername(String username);
}
