package interface_adapter.switch_to_reset_password;

import interface_adapter.ViewManagerModel;
import interface_adapter.reset_password.ResetPasswordViewModel;
import use_case.switch_to_login.SwitchToLoginOutputBoundary;
import use_case.switch_to_reset_password.SwitchToResetPasswordOutputBoundary;

public class SwitchToResetPasswordPresenter implements SwitchToResetPasswordOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private final ResetPasswordViewModel resetPasswordViewModel;
    public SwitchToResetPasswordPresenter(ViewManagerModel viewManagerModel, ResetPasswordViewModel resetPasswordViewModel){
        this.viewManagerModel = viewManagerModel;
        this.resetPasswordViewModel = resetPasswordViewModel;
    }
    @Override
    public void prepareSuccessView() {
        viewManagerModel.setActiveView(resetPasswordViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
