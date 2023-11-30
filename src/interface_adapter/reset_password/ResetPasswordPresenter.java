package interface_adapter.reset_password;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.reset_password.ResetPasswordOutputBoundary;
import use_case.reset_password.ResetPasswordOutputData;


public class ResetPasswordPresenter implements ResetPasswordOutputBoundary {
    private ViewManagerModel viewManagerModel;
    //TODO: Change all LoginViewModel to Login
    private final LoginViewModel loginViewModel;
    //private final SignupViewModel signupViewModel;
    private final ResetPasswordViewModel resetPasswordViewModel;

    public ResetPasswordPresenter(ViewManagerModel viewManagerModel, ResetPasswordViewModel resetPasswordViewModel, LoginViewModel loginViewModel){
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
        //this.signupViewModel = signupViewModel;
        this.resetPasswordViewModel = resetPasswordViewModel;
    }
    @Override
    public void prepareSuccessView(ResetPasswordOutputData user) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(user.getUser().getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        /*SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();*/
        ResetPasswordState resetPasswordState = resetPasswordViewModel.getState();
        if(error.contains("username")){
            resetPasswordState.setUsernameError(error);
        } else {
            resetPasswordState.setPasswordError(error);
        }
        resetPasswordViewModel.firePropertyChanged();
    }
}
