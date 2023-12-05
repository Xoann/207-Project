package interface_adapter.switch_to_login;

import use_case.switch_to_login.SwitchToLoginInputBoundary;

public class SwitchToLoginController {
    final SwitchToLoginInputBoundary switchToLoginInteractor;
    public SwitchToLoginController(SwitchToLoginInputBoundary switchToLoginInteractor) {
        this.switchToLoginInteractor = switchToLoginInteractor;
    }

    public void execute(){
        switchToLoginInteractor.execute();
    }
}
