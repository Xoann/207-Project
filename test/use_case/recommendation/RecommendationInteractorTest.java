package use_case.recommendation;

import data_access.InMemoryConversationDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecommendationInteractorTest {
    InMemoryUserDataAccessObject userRepo;
    InMemoryConversationDataAccessObject convoRepo;
    UserFactory userFactory = new CommonUserFactory();
    MessageFactory messageFactory = new CommonMessageFactory();

    @BeforeEach
    void setUp() {
        userRepo = new InMemoryUserDataAccessObject();
        convoRepo = new InMemoryConversationDataAccessObject();
        User test1 = userFactory.create("testUser1", "testPass1", "fakeAPIKey1");
        User test2 = userFactory.create("testUser2", "testPass2", "fakeAPIKey2");
        Message msg1 = messageFactory.create("Hi, how are you?", test1);
        Message msg2 = messageFactory.create("I'm fine, and you?", test2);
        userRepo.save(test1);
        userRepo.save(test2);
        convoRepo.createConversation(10);
        convoRepo.save(10, msg1);
        convoRepo.save(10, msg2);
    }

    @Test
    void failTestConvoId() {
        RecommendationOutputBoundary failPresenter = new RecommendationOutputBoundary() {
            @Override
            public void prepareSuccessView(RecommendationOutputData recommendation) {
                fail("Unexpected use case success");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "Conversation not found.");
            }
        };

        RecommendationInputData inputData = new RecommendationInputData(1, "", "testUser1");
        RecommendationInputBoundary interactor = new RecommendationInteractor(
                userRepo, convoRepo, failPresenter
        );
        interactor.execute(inputData);
    }

    @Test
    void failTestUsername() {
        RecommendationOutputBoundary failPresenter = new RecommendationOutputBoundary() {
            @Override
            public void prepareSuccessView(RecommendationOutputData recommendation) {
                fail("Unexpected use case success");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "User not found.");
            }
        };

        RecommendationInputData inputData = new RecommendationInputData(10, "", "bad_username");
        RecommendationInputBoundary interactor = new RecommendationInteractor(
                userRepo, convoRepo, failPresenter
        );
        interactor.execute(inputData);
    }

    @Test
    void failAPICall() {
        RecommendationOutputBoundary failPresenter = new RecommendationOutputBoundary() {
            @Override
            public void prepareSuccessView(RecommendationOutputData recommendation) {
                fail("Unexpected use case success");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "API call failed. Please try again later.");
            }
        };

        RecommendationInputData inputData = new RecommendationInputData(10, "", "testUser1");
        RecommendationInputBoundary interactor = new RecommendationInteractor(
                userRepo, convoRepo, failPresenter
        );
        interactor.execute(inputData);
    }
}