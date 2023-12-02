package interface_adapter.delete_user;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.delete_user.DeleteUserOutputBoundary;
import use_case.delete_user.DeleteUserOutputData;

public class DeleteUserPresenter implements DeleteUserOutputBoundary {

    private final DeleteUserViewModel DeleteUserViewModel;

    private final LoginViewModel loginViewModel;

    private final ViewManagerModel viewManagerModel;

    public DeleteUserPresenter(ViewManagerModel viewManagerModel,
                               DeleteUserViewModel DeleteUserViewModel,
                               LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.DeleteUserViewModel = DeleteUserViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(DeleteUserOutputData response) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        DeleteUserState DeleteUserState = DeleteUserViewModel.getState();
        DeleteUserState.setUsernameError(error);
        DeleteUserViewModel.firePropertyChanged();
    }
}
