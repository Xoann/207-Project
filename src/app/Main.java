package app;

import data_access.FileUserDataAccessObject;
import data_access.InMemoryConversationDataAccessObject;



import interface_adapter.login.LoginViewModel;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.reset_password.ResetPasswordViewModel;
import interface_adapter.send_message.SendMessageControllerBuilder;

import interface_adapter.recommendation.RecommendationControllerBuilder;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.switch_to_login.SwitchToLoginControllerBuilder;
import interface_adapter.switch_to_reset_password.SwitchToResetPasswordControllerBuilder;
import interface_adapter.switch_to_signup.SwitchToSignupControllerBuilder;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("AI Chat App");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        ResetPasswordViewModel resetPasswordViewModel = new ResetPasswordViewModel();

        InMemoryConversationDataAccessObject conversationDataAccessObject = new InMemoryConversationDataAccessObject();
//        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ResetPasswordView resetPasswordView = ResetPasswordUseCaseFactory.create(viewManagerModel, resetPasswordViewModel,
                loginViewModel, userDataAccessObject,
                SwitchToLoginControllerBuilder.createSwitchToLoginController(viewManagerModel, loginViewModel));
        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject,
                SwitchToLoginControllerBuilder.createSwitchToLoginController(viewManagerModel, loginViewModel));
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject,
                SwitchToResetPasswordControllerBuilder.createSwitchToResetPasswordController(viewManagerModel, resetPasswordViewModel),
                SwitchToSignupControllerBuilder.createSwitchToSignupController(viewManagerModel, signupViewModel));
        LoggedInView loggedInView = new LoggedInView(
                loggedInViewModel,
                SendMessageControllerBuilder.createSendMessageController(loggedInViewModel, userDataAccessObject, conversationDataAccessObject),
                RecommendationControllerBuilder.createRecommendationController(loggedInViewModel, userDataAccessObject, conversationDataAccessObject)
        );



        views.add(loginView, loginView.viewName);
        views.add(signupView, signupView.viewName);
        views.add(resetPasswordView, resetPasswordView.viewName);
        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
