package interface_adapter.delete_user;

import use_case.delete_user.DeleteUserInputBoundary;
import use_case.delete_user.DeleteUserInputData;

public class DeleteUserController {

    final DeleteUserInputBoundary deleteUserUseCaseInteractor;

    public DeleteUserController(DeleteUserInputBoundary deleteUserUseCaseInteractor) {
        this.deleteUserUseCaseInteractor = deleteUserUseCaseInteractor;
    }

    public void execute(String username, String password) {
        DeleteUserInputData deleteUserInputData = new DeleteUserInputData(
                username, password);

        deleteUserUseCaseInteractor.execute(deleteUserInputData);
    }
}
