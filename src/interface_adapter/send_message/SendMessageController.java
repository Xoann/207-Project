package interface_adapter.send_message;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInputData;
import use_case.send_message.SendMessageUserDataAccessInterface;

public class SendMessageController {
    final SendMessageInputBoundary sendMessageInteractor;
    final SendMessageUserDataAccessInterface userDataAccessObject;

    public SendMessageController(SendMessageInputBoundary sendMessageInteractor, SendMessageUserDataAccessInterface userDataAccessObject) {
        this. sendMessageInteractor = sendMessageInteractor;
        this.userDataAccessObject = userDataAccessObject;
    }

    public void execute(String message, String username, long conversationId) {
        User sender = userDataAccessObject.get(username);
        SendMessageInputData sendMessageInputData = new SendMessageInputData(
                message, sender, conversationId);

        sendMessageInteractor.execute(sendMessageInputData);
    }
}
