package use_case.delete_user;

import data_access.FileUserDataAccessObject;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

public class DeleteUserInteractorTest {
    @Mock
    private FileUserDataAccessObject mockUserDataAccessObject;
    @Mock
    private DeleteUserOutputBoundary mockDeleteUserOutputBoundary;

    private User mockUser;
    private DeleteUserInteractor deleteUserInteractor;
    private DeleteUserInputData inputData;

    @BeforeEach
    void setup() {
        mockUserDataAccessObject = mock(FileUserDataAccessObject.class);
        mockDeleteUserOutputBoundary = mock(DeleteUserOutputBoundary.class);
        mockUser = mock(User.class);

        when(mockUser.getPassword()).thenReturn("correctPassword");

        deleteUserInteractor = new DeleteUserInteractor(mockUserDataAccessObject, mockDeleteUserOutputBoundary);
    }

    @Test
    void failBecauseUserDoesNotExist() {
        inputData = new DeleteUserInputData("nonExistentUser", "password");
        when(mockUserDataAccessObject.existsByUsername("nonExistentUser")).thenReturn(false);

        deleteUserInteractor.execute(inputData);

        verify(mockDeleteUserOutputBoundary).prepareFailView("nonExistentUser: Account does not exist.");
    }

    @Test
    void failBecauseOfIncorrectPassword() {
        inputData = new DeleteUserInputData("existingUser", "wrongPassword");
        when(mockUserDataAccessObject.existsByUsername("existingUser")).thenReturn(true);
        when(mockUserDataAccessObject.get("existingUser")).thenReturn(mockUser);

        deleteUserInteractor.execute(inputData);

        verify(mockDeleteUserOutputBoundary).prepareFailView("Incorrect password for existingUser.");
    }

    @Test
    void successInDeletion() {
        inputData = new DeleteUserInputData("existingUser", "correctPassword");
        when(mockUserDataAccessObject.existsByUsername("existingUser")).thenReturn(true);
        when(mockUserDataAccessObject.get("existingUser")).thenReturn(mockUser);

        deleteUserInteractor.execute(inputData);

        verify(mockUserDataAccessObject).delete(any(User.class));
        verify(mockDeleteUserOutputBoundary).prepareSuccessView(any(DeleteUserOutputData.class));
    }

}
