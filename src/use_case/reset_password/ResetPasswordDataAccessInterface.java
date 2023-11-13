package use_case.reset_password;

import entity.User;

public interface ResetPasswordDataAccessInterface {
    void save(User user, String newPassword);
}
