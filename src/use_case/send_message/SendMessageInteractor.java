package use_case.send_message;

import entity.Message;
import entity.MessageFactory;

import java.util.Objects;

public class SendMessageInteractor implements SendMessageInputBoundary {

    final SendMessageConversationDataAccessInterface conversationDataAccessObject;

    final SendMessageOutputBoundary sendMessagePresenter;

    final MessageFactory messageFactory;

    public SendMessageInteractor(SendMessageConversationDataAccessInterface conversationDataAccessObject,
                                 SendMessageOutputBoundary sendMessagePresenter,
                                 MessageFactory messageFactory) {
        this.conversationDataAccessObject = conversationDataAccessObject;
        this.sendMessagePresenter = sendMessagePresenter;
        this.messageFactory = messageFactory;
    }
    @Override
    public void execute(SendMessageInputData sendMessageInputData) {
        String messageText = sendMessageInputData.getMessage();
        if (Objects.equals(messageText, "")) {
            sendMessagePresenter.prepareFailView("Empty message");
        } else {
            Message message = messageFactory.create(messageText, sendMessageInputData.getSender());
            conversationDataAccessObject.save(sendMessageInputData.getId(), message);

            SendMessageOutputData sendMessageOutputData = new SendMessageOutputData(message, false);
            sendMessagePresenter.prepareSuccessView(sendMessageOutputData);
        }

    }
}
