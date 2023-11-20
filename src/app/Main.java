package app;

import data_access.InMemoryConversationDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.recommendation.RecommendationControllerBuilder;
import view.LoggedInView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

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

        InMemoryConversationDataAccessObject conversationDataAccessObject = new InMemoryConversationDataAccessObject();
        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();

        LoggedInView loggedInView = new LoggedInView(
                loggedInViewModel,
                RecommendationControllerBuilder.createRecommendationController(loggedInViewModel, userDataAccessObject, conversationDataAccessObject)
        );

        views.add(loggedInView, loggedInView.viewName);

        // TODO change this to sign up or sign in view later. This is just to skip the sign in step of the app.
        viewManagerModel.setActiveView(loggedInView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
