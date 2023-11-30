package app;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;

import interface_adapter.login.LoginViewModel;
import interface_adapter.reset_password.ResetPasswordController;
import interface_adapter.reset_password.ResetPasswordPresenter;
import interface_adapter.reset_password.ResetPasswordViewModel;
import interface_adapter.switch_to_login.SwitchToLoginController;
import use_case.reset_password.ResetPasswordInputBoundary;
import use_case.reset_password.ResetPasswordInteractor;
import use_case.reset_password.ResetPasswordOutputBoundary;
import view.ResetPasswordView;

import javax.swing.*;
import java.io.IOException;

public class ResetPasswordUseCaseFactory {
    public static ResetPasswordView create(ViewManagerModel viewManagerModel, ResetPasswordViewModel resetPasswordViewModel,
                                           LoginViewModel loginViewModel, FileUserDataAccessObject userDataAccessObject,
                                           SwitchToLoginController switchToLoginController) {
        try {
            ResetPasswordController resetPasswordController = createResetPasswordUseCase(viewManagerModel, resetPasswordViewModel, loginViewModel, userDataAccessObject);
            return new ResetPasswordView(resetPasswordViewModel, resetPasswordController, switchToLoginController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static ResetPasswordController createResetPasswordUseCase(
            ViewManagerModel viewManagerModel,
            ResetPasswordViewModel resetPasswordViewModel,
            LoginViewModel loginViewModel,
            FileUserDataAccessObject userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        ResetPasswordOutputBoundary resetPasswordOutputBoundary = new ResetPasswordPresenter(viewManagerModel, resetPasswordViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        ResetPasswordInputBoundary resetPasswordInteractor = new ResetPasswordInteractor(
                userDataAccessObject, resetPasswordOutputBoundary);

        return new ResetPasswordController(resetPasswordInteractor);
    }
}
