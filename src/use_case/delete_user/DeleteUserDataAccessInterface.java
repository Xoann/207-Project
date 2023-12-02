package use_case.delete_user;

public interface DeleteUserDataAccessInterface {
    boolean existsByName(String identifier);

    void delete(String username);
}
