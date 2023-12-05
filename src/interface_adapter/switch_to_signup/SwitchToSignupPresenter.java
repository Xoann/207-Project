package interface_adapter.switch_to_signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.signup.SignupViewModel;
import use_case.switch_to_signup.SwitchToSignupOutputBoundary;

public class SwitchToSignupPresenter implements SwitchToSignupOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private final SignupViewModel loginViewModel;
    public SwitchToSignupPresenter(ViewManagerModel viewManagerModel, SignupViewModel loginViewModel){
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }
    @Override
    public void prepareSuccessView() {
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
