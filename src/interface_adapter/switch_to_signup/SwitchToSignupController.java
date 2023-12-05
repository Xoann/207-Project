package interface_adapter.switch_to_signup;

import use_case.switch_to_signup.SwitchToSignupInputBoundary;

public class SwitchToSignupController {
    final SwitchToSignupInputBoundary switchToSignupInteractor;
    public SwitchToSignupController(SwitchToSignupInputBoundary switchToSignupInteractor) {
        this.switchToSignupInteractor = switchToSignupInteractor;
    }

    public void execute(){
        switchToSignupInteractor.execute();
    }
}
