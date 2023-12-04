package use_case.login;

import org.junit.jupiter.api.Test;
import use_case.login.LoginInputData;

import static org.junit.jupiter.api.Assertions.*;

class LoginInputDataTest {
    LoginInputData loginInputData = new LoginInputData("bobby", "bobbyp");

    @Test
    void getUsername(){assertEquals(loginInputData.getUsername(), "bobby"); }

    @Test
    void getPassword(){assertEquals(loginInputData.getPassword(), "bobbyp"); }
}