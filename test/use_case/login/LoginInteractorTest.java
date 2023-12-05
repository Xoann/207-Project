package use_case.login;

import data_access.FileConversationDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import org.junit.jupiter.api.*;

import java.io.IOException;

public class LoginInteractorTest {
    LoginUserDataAccessInterface userDataAccessInterface;
    UserFactory userFactory = new CommonUserFactory();
    FileConversationDataAccessObject fileConversationDataAccessObject;

    @BeforeEach
    void setup() throws IOException {
        userDataAccessInterface = new FileUserDataAccessObject("users.csv", userFactory);
        fileConversationDataAccessObject = new FileConversationDataAccessObject(0);
    }

    @Test
    void fail(){
        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {}

            @Override
            public void prepareFailView(String error) {}
        };
        LoginInputData loginInputData = new LoginInputData("billy", "billyp");
        LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccessInterface, loginPresenter,
                fileConversationDataAccessObject);
        loginInteractor.execute(loginInputData);
    }

    @Test
    void success(){
        LoginOutputBoundary loginPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {}

            @Override
            public void prepareFailView(String error) {}
        };
        LoginInputData loginInputData = new LoginInputData("billy", "billyp");
        LoginInputBoundary loginInteractor = new LoginInteractor(userDataAccessInterface, loginPresenter,
                fileConversationDataAccessObject);
        loginInteractor.execute(loginInputData);
    }

}
