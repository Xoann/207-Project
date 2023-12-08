package use_case.delete_user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteUserInputDataTest {
    DeleteUserInputData deleteUserInputData = new DeleteUserInputData("bobby", "bobbyp");

    @Test
    void getUsername(){assertEquals(deleteUserInputData.getUsername(), "bobby"); }

    @Test
    void getPassword(){assertEquals(deleteUserInputData.getPassword(), "bobbyp"); }
}
