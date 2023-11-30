package use_case.login;

<<<<<<< HEAD
import entity.User;

public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
=======
import data_access.FileUserDataAccessObject;
import entity.User;

public class LoginInteractor implements LoginInputBoundary {
    final FileUserDataAccessObject userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(FileUserDataAccessObject userDataAccessInterface,
>>>>>>> dev
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
<<<<<<< HEAD
        if (!userDataAccessObject.existsByName(username)) {
=======
        if (!userDataAccessObject.existsByUsername(username)) {
>>>>>>> dev
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        } else {
            String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else {

                User user = userDataAccessObject.get(loginInputData.getUsername());

                LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(), false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}