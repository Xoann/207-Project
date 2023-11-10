package app;

import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.recommendation.RecommendationController;
import interface_adapter.recommendation.RecommendationPresenter;
import use_case.recommendation.*;

public class RecommendationUseCaseFactory {
    private RecommendationUseCaseFactory() {}

    public static RecommendationController createRecommendationUseCase (
            LoggedInViewModel loggedInViewModel,
            RecommendationUserDataAccessInterface recommendationUserDataAccessObject,
            RecommendationConversationDataAccessInterface recommendationConversationDataAccessObject) {
        RecommendationOutputBoundary recommendationPresenter = new RecommendationPresenter(loggedInViewModel);
        RecommendationInputBoundary recommendationInputInteractor = new RecommendationInteractor(
                recommendationUserDataAccessObject, recommendationConversationDataAccessObject, recommendationPresenter
        );

        return new RecommendationController(recommendationInputInteractor);
    }
}
