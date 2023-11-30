package use_case.send_message;

import entity.User;

public interface SendMessageUserDataAccessInterface {
    User get(String username);
    boolean existsByUsername(String username);
}
