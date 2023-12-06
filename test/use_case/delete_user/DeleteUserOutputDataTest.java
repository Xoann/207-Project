package use_case.delete_user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteUserOutputDataTest {

    @Test
    public void testConstructorAndGetter() {
        String expectedUsername = "testUser";
        boolean expectedUseCaseFailed = true;

        DeleteUserOutputData outputData = new DeleteUserOutputData(expectedUsername, expectedUseCaseFailed);

        assertEquals(expectedUsername, outputData.getUsername(), "Username should match the input provided to the constructor.");
    }
}
