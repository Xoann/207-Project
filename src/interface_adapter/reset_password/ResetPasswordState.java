package interface_adapter.reset_password;

public class ResetPasswordState {
    private String username = "";
    private String usernameError = null;
    private String newPassword = "";
    private String repeatNewPassword = "";
    private String passwordError = null;
    public ResetPasswordState(ResetPasswordState copy){
        this.username = copy.username;
        this.newPassword = copy.newPassword;
        this.repeatNewPassword = copy.repeatNewPassword;
    }
    public ResetPasswordState() {}
    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getNewPassword() {
        return newPassword;
    }
    public String getRepeatNewPassword() {return repeatNewPassword; }

    public String getPasswordError() {
        return passwordError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setNewPassword(String password) {
        this.newPassword = password;
    }
    public void setRepeatNewPassword(String repeatNewPassword){this.repeatNewPassword = repeatNewPassword; }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }
}
