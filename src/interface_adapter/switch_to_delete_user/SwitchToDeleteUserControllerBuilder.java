package interface_adapter.switch_to_delete_user;

import interface_adapter.ViewManagerModel;
import interface_adapter.delete_user.DeleteUserViewModel;
import use_case.switch_to_delete_user.SwitchToDeleteUserInputBoundary;
import use_case.switch_to_delete_user.SwitchToDeleteUserInteractor;
import use_case.switch_to_delete_user.SwitchToDeleteUserOutputBoundary;

public class SwitchToDeleteUserControllerBuilder {

    private SwitchToDeleteUserControllerBuilder(){}

    public static SwitchToDeleteUserController createSwitchToDeleteUserController(ViewManagerModel viewManagerModel, DeleteUserViewModel deleteUserViewModel){
        SwitchToDeleteUserOutputBoundary switchToDeleteUserPresenter = new SwitchToDeleteUserPresenter(viewManagerModel, deleteUserViewModel);
        SwitchToDeleteUserInputBoundary switchToDeleteUserInteractor = new SwitchToDeleteUserInteractor(switchToDeleteUserPresenter);
        return new SwitchToDeleteUserController(switchToDeleteUserInteractor);
    }
}
