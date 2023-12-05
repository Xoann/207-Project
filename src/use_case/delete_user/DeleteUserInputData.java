package use_case.delete_user;

public class DeleteUserInputData {

    final private String username;

    final private String password;

    public DeleteUserInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }
}
