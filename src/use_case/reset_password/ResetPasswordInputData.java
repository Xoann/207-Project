package use_case.reset_password;

import entity.User;

public class ResetPasswordInputData {
    // Look into using username instead of user since they'll be logged out
    final private User user;
    final private String newPassword;
    final private String repeatNewPassword;

    public ResetPasswordInputData(User user, String newPassword, String repeatNewPassword){
        this.user = user;
        this.newPassword = newPassword;
        this.repeatNewPassword = repeatNewPassword;
    }

    public User getUser(){
        return this.user;
    }
    public String getNewPassword() {
        return newPassword;
    }
    public String getRepeatNewPassword(){
        return repeatNewPassword;
    }
}
