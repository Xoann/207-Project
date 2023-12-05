package use_case.delete_user;

public class DeleteUserOutputData {

    private final String username;

    private boolean useCaseFailed;

    public DeleteUserOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }
}
