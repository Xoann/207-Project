package app;

import data_access.FileUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.delete_user.DeleteUserController;
import interface_adapter.delete_user.DeleteUserPresenter;
import interface_adapter.delete_user.DeleteUserViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.switch_to_login.SwitchToLoginController;
import use_case.delete_user.DeleteUserInputBoundary;
import use_case.delete_user.DeleteUserInteractor;
import use_case.delete_user.DeleteUserOutputBoundary;
import view.DeleteUserView;

import javax.swing.*;
import java.io.IOException;

public class DeleteUserUseCaseFactory {

    private DeleteUserUseCaseFactory() {
    }

    public static DeleteUserView create(
            ViewManagerModel viewManagerModel,
            DeleteUserViewModel deleteUserViewModel,
            LoginViewModel loginViewModel,
            FileUserDataAccessObject userDataAccessObject,
            SwitchToLoginController switchToLoginController) {

     try {
         DeleteUserController deleteUserController = createDeleteUserUseCase(viewManagerModel, deleteUserViewModel, loginViewModel, userDataAccessObject);
         return new DeleteUserView(deleteUserViewModel, deleteUserController, switchToLoginController);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Could not open user data file.");
    }

    return null;
}

private static DeleteUserController createDeleteUserUseCase(
        ViewManagerModel viewManagerModel,
        DeleteUserViewModel DeleteUserViewModel,
        LoginViewModel loginViewModel,
        FileUserDataAccessObject userDataAccessObject) throws IOException {

    // Notice how we pass this method's parameters to the Presenter.
    DeleteUserOutputBoundary deleteUserOutputBoundary = new DeleteUserPresenter(viewManagerModel, DeleteUserViewModel, loginViewModel);

    DeleteUserInputBoundary deleteUserInteractor = new DeleteUserInteractor(
            userDataAccessObject, deleteUserOutputBoundary);

    return new DeleteUserController(deleteUserInteractor);
}
}
