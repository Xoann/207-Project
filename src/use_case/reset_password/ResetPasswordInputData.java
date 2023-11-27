package use_case.reset_password;

import entity.User;

public class ResetPasswordInputData {

    final private String username;
    final private String newPassword;
    final private String repeatNewPassword;

    public ResetPasswordInputData(String username, String newPassword, String repeatNewPassword){
        this.username = username;
        this.newPassword = newPassword;
        this.repeatNewPassword = repeatNewPassword;
    }

    public String getUsername(){
        return this.username;
    }
    public String getNewPassword() {
        return newPassword;
    }
    public String getRepeatNewPassword(){
        return repeatNewPassword;
    }
}
