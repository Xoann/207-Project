package interface_adapter.recommendation;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.recommendation.RecommendationOutputBoundary;
import use_case.recommendation.RecommendationOutputData;

public class RecommendationPresenter implements RecommendationOutputBoundary {
    private final LoggedInViewModel loggedInViewModel;

    public RecommendationPresenter(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void prepareSuccessView(RecommendationOutputData recommendation) {
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setMessage(recommendation.getRecommendation());
        loggedInViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setRecommendationError(error);
        loggedInViewModel.firePropertyChanged();
    }
}
