package entity;

public class CommonUserFactory implements UserFactory {
    /**
     * Requires: password is valid.
     */
    @Override
    public User create(String name, String password, String key) {
        return new CommonUser(name, password, key);
    }

}
