package use_case.reset_password;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResetPasswordInputDataTest {
    ResetPasswordInputData data = new ResetPasswordInputData("test", "123", "123");
    ResetPasswordInputData data2 = new ResetPasswordInputData("test", "123", "321");

    @Test
    void getUsername() {
        assertEquals(data.getUsername(), "test");
    }

    @Test
    void getNewPassword() {
        assertEquals(data.getNewPassword(), "123");
    }

    @Test
    void getRepeatNewPassword() {
        assertEquals(data.getRepeatNewPassword(), "123");
    }

    @Test
    void getDifferentRepeatNewPassword() {
        assertEquals(data2.getRepeatNewPassword(), "321");
    }

    @Test
    void getDifferentNewPassword() {
        assertEquals(data2.getNewPassword(), "123");
    }
}
