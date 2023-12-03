package interface_adapter.send_message;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.send_message.SendMessageOutputBoundary;
import use_case.send_message.SendMessageOutputData;

public class SendMessagePresenter implements SendMessageOutputBoundary {
    private final LoggedInViewModel loggedInViewModel;


    public SendMessagePresenter(LoggedInViewModel sendMessageViewModel) {
        this.loggedInViewModel = sendMessageViewModel;
    }

    @Override
    public void prepareSuccessView(SendMessageOutputData message) {
        LoggedInState loggedInState = loggedInViewModel.getState();
        String conversation = loggedInState.getConversation() + loggedInState.getUsername() + " : " + message.getMessage() + "\n";
        loggedInState.setConversation(conversation);
        loggedInViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setSendError(error);
        loggedInViewModel.firePropertyChanged();
    }
}
