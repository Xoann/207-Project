package interface_adapter.reset_password;

import use_case.reset_password.ResetPasswordInputBoundary;
import use_case.reset_password.ResetPasswordInputData;

public class ResetPasswordController {
    final ResetPasswordInputBoundary resetPasswordUseCaseInteractor;
    public ResetPasswordController(ResetPasswordInputBoundary resetPasswordUseCaseInteractor){
        this.resetPasswordUseCaseInteractor = resetPasswordUseCaseInteractor;
    }

    public void execute(String username, String newPassword, String repeatNewPassword){
        ResetPasswordInputData resetPasswordInputData = new ResetPasswordInputData(username, newPassword,
                repeatNewPassword);
        resetPasswordUseCaseInteractor.execute(resetPasswordInputData);
    }
}
