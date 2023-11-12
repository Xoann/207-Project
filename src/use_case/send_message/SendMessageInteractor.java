package use_case.send_message;

import entity.Message;
import entity.MessageFactory;

import java.time.LocalDateTime;

public class SendMessageInteractor implements SendMessageInputBoundary {

    final SendMessageDataAccessInterface conversationDataAccessObject;

    final SendMessageOutputBoundary sendMessagePresenter;

    final MessageFactory messageFactory;

    public SendMessageInteractor(SendMessageDataAccessInterface conversationDataAccessObject,
                                 SendMessageOutputBoundary sendMessagePresenter,
                                 MessageFactory messageFactory) {
        this.conversationDataAccessObject = conversationDataAccessObject;
        this.sendMessagePresenter = sendMessagePresenter;
        this.messageFactory = messageFactory;
    }
    @Override
    public void execute(SendMessageInputData sendMessageInputData) {
        Message message = messageFactory.create(sendMessageInputData.getMessage(), sendMessageInputData.getSender());
        conversationDataAccessObject.save(sendMessageInputData.getId(), message);

        SendMessageOutputData sendMessageOutputData = new SendMessageOutputData(message, false);
        sendMessagePresenter.prepareSuccessView(sendMessageOutputData);
    }
}
