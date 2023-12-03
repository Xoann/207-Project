package use_case.switch_to_reset_password;


public class SwitchToResetPasswordInteractor implements SwitchToResetPasswordInputBoundary {
    final SwitchToResetPasswordOutputBoundary userPresenter;
    public SwitchToResetPasswordInteractor(SwitchToResetPasswordOutputBoundary userPresenter){
        this.userPresenter = userPresenter;
    }


    @Override
    public void execute() {
        userPresenter.prepareSuccessView();
    }
}
