package interface_adapter.switch_to_reset_password;

import interface_adapter.ViewManagerModel;
import interface_adapter.reset_password.ResetPasswordViewModel;
import use_case.switch_to_reset_password.SwitchToResetPasswordInputBoundary;
import use_case.switch_to_reset_password.SwitchToResetPasswordInteractor;
import use_case.switch_to_reset_password.SwitchToResetPasswordOutputBoundary;

public class SwitchToResetPasswordControllerBuilder {
    private SwitchToResetPasswordControllerBuilder(){}
    public static SwitchToResetPasswordController createSwitchToResetPasswordController(ViewManagerModel viewManagerModel, ResetPasswordViewModel resetPasswordViewModel){
        SwitchToResetPasswordOutputBoundary switchToResetPasswordPresenter = new SwitchToResetPasswordPresenter(viewManagerModel, resetPasswordViewModel);
        SwitchToResetPasswordInputBoundary switchToResetPasswordInteractor = new SwitchToResetPasswordInteractor(switchToResetPasswordPresenter);
        return new SwitchToResetPasswordController(switchToResetPasswordInteractor);
    }
}
