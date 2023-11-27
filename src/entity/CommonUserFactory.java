package entity;

import java.time.LocalDateTime;

public class CommonUserFactory implements UserFactory {
    @Override
    public User create(String name, String password, String apiKey) {
        return new CommonUser(name, password, apiKey);
    }
}
