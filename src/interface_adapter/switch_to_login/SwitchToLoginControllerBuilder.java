package interface_adapter.switch_to_login;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import use_case.switch_to_login.SwitchToLoginInputBoundary;
import use_case.switch_to_login.SwitchToLoginInteractor;
import use_case.switch_to_login.SwitchToLoginOutputBoundary;

public class SwitchToLoginControllerBuilder {
    private SwitchToLoginControllerBuilder(){}
    public static SwitchToLoginController createSwitchToLoginController(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel){
        SwitchToLoginOutputBoundary switchToLoginPresenter = new SwitchToLoginPresenter(viewManagerModel, loginViewModel);
        SwitchToLoginInputBoundary switchToLoginInteractor = new SwitchToLoginInteractor(switchToLoginPresenter);
        return new SwitchToLoginController(switchToLoginInteractor);
    }

}
