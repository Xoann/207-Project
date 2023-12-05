package use_case.delete_user;

import data_access.FileUserDataAccessObject;
import entity.User;

public class DeleteUserInteractor implements DeleteUserInputBoundary {
    final FileUserDataAccessObject userDataAccessObject;

    final DeleteUserOutputBoundary deleteUserOutputBoundary;

    public DeleteUserInteractor(FileUserDataAccessObject userDataAccessObject, DeleteUserOutputBoundary deleteUserOutputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.deleteUserOutputBoundary = deleteUserOutputBoundary;
    }

    @Override
    public void execute(DeleteUserInputData deleteUserInputData) {
        String username = deleteUserInputData.getUsername();
        String password = deleteUserInputData.getPassword();
        if (!userDataAccessObject.existsByUsername(username)) {
            deleteUserOutputBoundary.prepareFailView(username + ": Account does not exist.");
        } else {
            String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                deleteUserOutputBoundary.prepareFailView("Incorrect password for " + username + ".");
            } else {
                User user = userDataAccessObject.get(username);
                userDataAccessObject.delete(user);

                DeleteUserOutputData deleteUserOutputData = new DeleteUserOutputData(user.getUsername(), false);
                deleteUserOutputBoundary.prepareSuccessView(deleteUserOutputData);
            }
        }
    }
}
