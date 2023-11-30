package use_case.switch_to_login;

public class SwitchToLoginInteractor implements SwitchToLoginInputBoundary{
    final SwitchToLoginOutputBoundary userPresenter;
    public SwitchToLoginInteractor(SwitchToLoginOutputBoundary userPresenter){
        this.userPresenter = userPresenter;
    }


    @Override
    public void execute() {
        userPresenter.prepareSuccessView();
    }
}
