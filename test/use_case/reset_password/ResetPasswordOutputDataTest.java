package use_case.reset_password;

import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResetPasswordOutputDataTest {
    UserFactory userFactory = new CommonUserFactory();
    User user = userFactory.create("test", "123", "key");
    ResetPasswordOutputData data = new ResetPasswordOutputData(user, false);

    @Test
    void getUser(){
        assertEquals(data.getUser(), user);
    }
}
