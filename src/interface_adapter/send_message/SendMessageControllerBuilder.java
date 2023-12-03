package interface_adapter.send_message;

import entity.CommonMessageFactory;
import entity.MessageFactory;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.send_message.*;

public class SendMessageControllerBuilder {
    private void SendMesssageControllerBuilder() {}

    public static SendMessageController createSendMessageController(
            LoggedInViewModel loggedInViewModel,
            SendMessageUserDataAccessInterface sendMessageUserDataAccessObject,
            SendMessageConversationDataAccessInterface sendMessageConversationDataAccessObject) {
        SendMessageOutputBoundary sendMessagePresenter = new SendMessagePresenter(loggedInViewModel);
        MessageFactory messageFactory = new CommonMessageFactory();
        SendMessageInputBoundary sendMessageInputInteractor = new SendMessageInteractor(
                sendMessageConversationDataAccessObject,
                sendMessageUserDataAccessObject, sendMessagePresenter,
                messageFactory
        );

        return new SendMessageController(sendMessageInputInteractor, sendMessageUserDataAccessObject);
    }
}
