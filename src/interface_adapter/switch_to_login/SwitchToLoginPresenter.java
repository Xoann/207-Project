package interface_adapter.switch_to_login;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.switch_to_login.SwitchToLoginOutputBoundary;

public class SwitchToLoginPresenter implements SwitchToLoginOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;
    public SwitchToLoginPresenter(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel){
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }
    @Override
    public void prepareSuccessView() {
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
