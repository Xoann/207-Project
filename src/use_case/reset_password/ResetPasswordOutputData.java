package use_case.reset_password;

import entity.User;

public class ResetPasswordOutputData {
    private boolean useCaseFailed;
    private User user;
    public ResetPasswordOutputData(User user, boolean useCaseFailed){
        this.user = user;
        this.useCaseFailed = useCaseFailed;
    }
}
