package interface_adapter.recommendation;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.recommendation.RecommendationOutputBoundary;
import use_case.recommendation.RecommendationOutputData;

/**
 * A class for updating the app's state
 */
public class RecommendationPresenter implements RecommendationOutputBoundary {
    private final LoggedInViewModel loggedInViewModel;

    /**
     * Constructs a recommendation presenter to update the logged in view model
     * @param loggedInViewModel the state and data in the logged in view
     */
    public RecommendationPresenter(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
    }

    /**
     * Updates the logged in state when a recommendation is successfully generated
     * @param recommendation the generated recommendation
     */
    @Override
    public void prepareSuccessView(RecommendationOutputData recommendation) {
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setMessage(recommendation.getRecommendation());
        loggedInViewModel.firePropertyChanged();
    }

    /**
     * Updates the state when a recommendation fails to generate
     * @param error the error message to show to the user
     */
    @Override
    public void prepareFailView(String error) {
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setRecommendationError(error);
        loggedInViewModel.firePropertyChanged();
    }
}
