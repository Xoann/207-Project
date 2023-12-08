package use_case.switch_to_delete_user;

public class SwitchToDeleteUserInteractor implements SwitchToDeleteUserInputBoundary{

        final SwitchToDeleteUserOutputBoundary switchToDeleteUserPresenter;

        public SwitchToDeleteUserInteractor(SwitchToDeleteUserOutputBoundary switchToDeleteUserPresenter) {
            this.switchToDeleteUserPresenter = switchToDeleteUserPresenter;
        }

        @Override
        public void execute() {
            switchToDeleteUserPresenter.prepareSuccessView();
        }
}
