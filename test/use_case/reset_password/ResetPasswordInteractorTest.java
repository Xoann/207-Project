package use_case.reset_password;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ResetPasswordInteractorTest {
    FileUserDataAccessObject userRepo;
    UserFactory userFactory = new CommonUserFactory();

    @BeforeEach
    void setUp() throws IOException {
        userRepo = new FileUserDataAccessObject("testUsers", userFactory);
        User test1 = userFactory.create("testUser1", "Test", "Key");
        // test2 = userFactory.create("testUser2", "testPass2", "Key2");
        userRepo.save(test1);
    }

    @Test
    void failTestUsername(){
        ResetPasswordOutputBoundary failPresenter = new ResetPasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ResetPasswordOutputData user) {
                fail("Unexpected use case Success");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals(error, "username doesn't match");
            }
        };

        ResetPasswordInputData inputData = new ResetPasswordInputData("notinrepo", "123", "123");
        ResetPasswordInputBoundary interactor = new ResetPasswordInteractor(userRepo, failPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failTestPassword(){
        ResetPasswordOutputBoundary failPresenter = new ResetPasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ResetPasswordOutputData user) {
                fail("Unexpected use case Success");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match", error);
            }
        };

        ResetPasswordInputData inputData = new ResetPasswordInputData("testUser1", "123", "124");
        ResetPasswordInputBoundary interactor = new ResetPasswordInteractor(userRepo, failPresenter);
        interactor.execute(inputData);
    }


    @Test
    void resetPassword(){
        ResetPasswordOutputBoundary failPresenter = new ResetPasswordOutputBoundary() {
            @Override
            public void prepareSuccessView(ResetPasswordOutputData user) {}
            @Override
            public void prepareFailView(String error) {}
        };

        ResetPasswordInputData inputData = new ResetPasswordInputData("testUser1", "NewPassword", "NewPassword");
        ResetPasswordInputBoundary interactor = new ResetPasswordInteractor(userRepo, failPresenter);
        interactor.execute(inputData);
        assertEquals("NewPassword", userRepo.get("testUser1").getPassword());
    }
}
