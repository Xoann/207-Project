package entity;

public interface UserFactory {
    User create(String name, String password, String apikey);
}
