package interface_adapter.switch_to_reset_password;

import use_case.switch_to_reset_password.SwitchToResetPasswordInputBoundary;

public class SwitchToResetPasswordController {
    final SwitchToResetPasswordInputBoundary switchToResetPasswordInteractor;
    public SwitchToResetPasswordController(SwitchToResetPasswordInputBoundary switchToResetPasswordInteractor) {
        this.switchToResetPasswordInteractor = switchToResetPasswordInteractor;
    }

    public void execute(){
        switchToResetPasswordInteractor.execute();
    }
}
