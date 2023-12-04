package use_case.signup;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SignupOutputDataTest {
    SignupOutputData signupOutputData = new SignupOutputData("bobby", false);

    @Test
    void getUsername(){assertEquals(signupOutputData.getUsername(), "bobby"); }

    @Test
    void getCreated(){assert(!signupOutputData.getCreated()); }
}