package interface_adapter.switch_to_signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.switch_to_signup.SwitchToSignupController;
import interface_adapter.switch_to_signup.SwitchToSignupPresenter;
import use_case.switch_to_signup.SwitchToSignupInputBoundary;
import use_case.switch_to_signup.SwitchToSignupInteractor;
import use_case.switch_to_signup.SwitchToSignupOutputBoundary;

public class SwitchToSignupControllerBuilder {
    private SwitchToSignupControllerBuilder(){}
    public static SwitchToSignupController createSwitchToSignupController(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel){
        SwitchToSignupOutputBoundary switchToSignupPresenter = new SwitchToSignupPresenter(viewManagerModel, signupViewModel);
        SwitchToSignupInputBoundary switchToSignupInteractor = new SwitchToSignupInteractor(switchToSignupPresenter);
        return new SwitchToSignupController(switchToSignupInteractor);
    }
}
