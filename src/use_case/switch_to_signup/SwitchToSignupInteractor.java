package use_case.switch_to_signup;

public class SwitchToSignupInteractor implements SwitchToSignupInputBoundary{
    final SwitchToSignupOutputBoundary userPresenter;
    public SwitchToSignupInteractor(SwitchToSignupOutputBoundary userPresenter){
        this.userPresenter = userPresenter;
    }


    @Override
    public void execute() {
        userPresenter.prepareSuccessView();
    }
}
