package use_case.delete_user;

public interface DeleteUserOutputBoundary {

    void prepareSuccessView(DeleteUserOutputData user);

    void prepareFailView(String error);
}
