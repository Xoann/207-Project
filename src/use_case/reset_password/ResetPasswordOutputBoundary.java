package use_case.reset_password;

public interface ResetPasswordOutputBoundary {
    void prepareSuccessView(ResetPasswordOutputData user);
    void prepareFailView(String error);
}
