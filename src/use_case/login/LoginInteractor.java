package use_case.login;

import entity.Conversation;
import entity.User;

public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginConversationDataAccessInterface conversationDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary,
                           LoginConversationDataAccessInterface conversationDataAccessObject) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
        this.conversationDataAccessObject = conversationDataAccessObject;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        } else {
            String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else {

                User user = userDataAccessObject.get(loginInputData.getUsername());
//                TODO change convo id
                Conversation conversation;
                if (conversationDataAccessObject.existsById(0)) {
                    conversation = conversationDataAccessObject.get(0);
                } else {
                    conversation = new Conversation(0);
                }

                LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(), conversation, false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}