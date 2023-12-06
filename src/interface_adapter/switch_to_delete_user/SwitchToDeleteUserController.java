package interface_adapter.switch_to_delete_user;

import use_case.switch_to_delete_user.SwitchToDeleteUserInputBoundary;
public class SwitchToDeleteUserController {

    final SwitchToDeleteUserInputBoundary switchToLoginInteractor;

    public SwitchToDeleteUserController(SwitchToDeleteUserInputBoundary switchToLoginInteractor) {
        this.switchToLoginInteractor = switchToLoginInteractor;
    }

    public void execute(){
        switchToLoginInteractor.execute();
    }

}
