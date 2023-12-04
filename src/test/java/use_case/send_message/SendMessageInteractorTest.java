package use_case.send_message;

import data_access.InMemoryConversationDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SendMessageInteractorTest {
    InMemoryUserDataAccessObject userDataAccessObject;
    InMemoryConversationDataAccessObject conversationDataAccessObject;
    MessageFactory messageFactory;


    @BeforeEach
    void setUp() {
        messageFactory = new CommonMessageFactory();
        conversationDataAccessObject = new InMemoryConversationDataAccessObject();
        userDataAccessObject = new InMemoryUserDataAccessObject();
        User testUser = new CommonUserFactory().create("John", "password", "apikey");
        userDataAccessObject.save(testUser);
    }

    @Test
    void FailTestEmptyMessage() {
        SendMessageOutputBoundary failPresenter = new SendMessageOutputBoundary() {
            @Override
            public void prepareSuccessView(SendMessageOutputData message) {
                fail("Unexpected use case success");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "Empty message");
            }
        };

        User sender = new CommonUserFactory().create("John", "password", "apikey");
        SendMessageInputData inputData = new SendMessageInputData("", sender, 0);
        SendMessageInputBoundary interactor = new SendMessageInteractor(
                conversationDataAccessObject, userDataAccessObject, failPresenter, messageFactory
        );
        interactor.execute(inputData);
    }

    @Test
    void FailTestUsername() {
        SendMessageOutputBoundary failPresenter = new SendMessageOutputBoundary() {
            @Override
            public void prepareSuccessView(SendMessageOutputData message) {
                fail("Unexpected use case success");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "User not found");
            }
        };

        User sender = new CommonUserFactory().create("bad_username", "password", "apikey");
        SendMessageInputData inputData = new SendMessageInputData("hi", sender, 0);
        SendMessageInputBoundary interactor = new SendMessageInteractor(
                conversationDataAccessObject, userDataAccessObject, failPresenter, messageFactory
        );
        interactor.execute(inputData);
    }

    @Test
    void FailTestConversationId() {
        SendMessageOutputBoundary failPresenter = new SendMessageOutputBoundary() {
            @Override
            public void prepareSuccessView(SendMessageOutputData message) {
                fail("Unexpected use case success");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "Conversation not found");
            }
        };

        User sender = new CommonUserFactory().create("John", "password", "apikey");
        SendMessageInputData inputData = new SendMessageInputData("hi", sender, 999);
        SendMessageInputBoundary interactor = new SendMessageInteractor(
                conversationDataAccessObject, userDataAccessObject, failPresenter, messageFactory
        );
        interactor.execute(inputData);
    }
}