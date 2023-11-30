package use_case.reset_password;

import entity.User;

import java.util.Objects;

public class ResetPasswordInteractor implements ResetPasswordInputBoundary{
    final ResetPasswordDataAccessInterface userDataAccessObject;
    final ResetPasswordOutputBoundary userPresenter;

    public ResetPasswordInteractor(ResetPasswordDataAccessInterface userDataAccessObject,
                                   ResetPasswordOutputBoundary userPresenter){
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }
    @Override
    public void execute(ResetPasswordInputData resetPasswordInputData) {
        String username = resetPasswordInputData.getUsername();
        if(!this.userDataAccessObject.existsByUsername(username)) {
            userPresenter.prepareFailView("username doesn't match");
            return;
        }
        else if (!Objects.equals(resetPasswordInputData.getNewPassword(), resetPasswordInputData.getRepeatNewPassword())) {
            userPresenter.prepareFailView("Passwords don't match");
        }
        else {
            User user = this.userDataAccessObject.get(username);
            user.setPassword(resetPasswordInputData.getNewPassword());
            userDataAccessObject.save(user);
            ResetPasswordOutputData resetPasswordOutputData = new ResetPasswordOutputData(
                    user, false);
            userPresenter.prepareSuccessView(resetPasswordOutputData);
        }
    }
}
