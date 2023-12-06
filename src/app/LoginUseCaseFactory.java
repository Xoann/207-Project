package app;

import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.switch_to_delete_user.SwitchToDeleteUserController;
import use_case.login.*;
import interface_adapter.switch_to_reset_password.SwitchToResetPasswordController;
import interface_adapter.switch_to_signup.SwitchToSignupController;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            SwitchToResetPasswordController switchToResetPasswordController,
            LoginConversationDataAccessInterface convoDataAccessObject,
            SwitchToSignupController switchToSignupController, SwitchToDeleteUserController switchToDeleteUserController) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject, convoDataAccessObject);
            return new LoginView(loginViewModel, loginController, switchToResetPasswordController, switchToSignupController, switchToDeleteUserController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            LoginUserDataAccessInterface userDataAccessObject,
            LoginConversationDataAccessInterface convoDataAccessObject
            ) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary, convoDataAccessObject);

        return new LoginController(loginInteractor);
    }
}
