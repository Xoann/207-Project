package interface_adapter.switch_to_delete_user;

import interface_adapter.ViewManagerModel;
import interface_adapter.delete_user.DeleteUserViewModel;
import use_case.switch_to_delete_user.SwitchToDeleteUserOutputBoundary;

public class SwitchToDeleteUserPresenter implements SwitchToDeleteUserOutputBoundary{

    private ViewManagerModel viewManagerModel;

    private final DeleteUserViewModel deleteUserViewModel;

    public SwitchToDeleteUserPresenter(ViewManagerModel viewManagerModel, DeleteUserViewModel deleteUserViewModel){
        this.viewManagerModel = viewManagerModel;
        this.deleteUserViewModel = deleteUserViewModel;
    }

    @Override
    public void prepareSuccessView() {
        viewManagerModel.setActiveView(deleteUserViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


}
