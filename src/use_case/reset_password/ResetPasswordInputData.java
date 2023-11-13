package use_case.reset_password;

import entity.User;

public class ResetPasswordInputData {

    final private User user;

    public ResetPasswordInputData(User user){
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }
}
