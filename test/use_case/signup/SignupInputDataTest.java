package use_case.signup;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SignupInputDataTest {
    SignupInputData signupInputData = new SignupInputData("bobby", "bobbyp", "key");

    @Test
    void getUsername(){assertEquals(signupInputData.getUsername(), "bobby"); }

    @Test
    void getPassword(){assertEquals(signupInputData.getPassword(), "bobbyp"); }

    @Test
    void getApiKey(){assertEquals(signupInputData.getApiKey(), "key"); }
}
