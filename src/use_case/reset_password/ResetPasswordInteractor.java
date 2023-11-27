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
        if(!this.userDataAccessObject.existsByUsername(username)){
            userPresenter.prepareFailView("username doesn't match");
            return;
        }
        User user = this.userDataAccessObject.get(username);
        if (!Objects.equals(resetPasswordInputData.getNewPassword(), resetPasswordInputData.getRepeatNewPassword())){
            userPresenter.prepareFailView("Passwords don't match");
        } else if (Objects.equals(resetPasswordInputData.getNewPassword(),
                user.getPassword())){ // Might not need this
            userPresenter.prepareFailView("New password must be different from old password");
        }
        else {
            user.setPassword(resetPasswordInputData.getNewPassword());
            ResetPasswordOutputData resetPasswordOutputData = new ResetPasswordOutputData(
                    user, false);
            userPresenter.prepareSuccessView(resetPasswordOutputData);
        }
    }
}
