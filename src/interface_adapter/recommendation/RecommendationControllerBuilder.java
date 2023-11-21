package interface_adapter.recommendation;

import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.recommendation.RecommendationController;
import interface_adapter.recommendation.RecommendationPresenter;
import use_case.recommendation.*;

/**
 * A builder for recommendation controller
 */
public class RecommendationControllerBuilder {
    private RecommendationControllerBuilder() {}

    /**
     * Creates a controller for recommendation use case
     * @param loggedInViewModel the current view model storing data on current user and conversation
     * @param recommendationUserDataAccessObject DAO for user data
     * @param recommendationConversationDataAccessObject DAO for conversation data
     * @return recommendation controller
     */
    public static RecommendationController createRecommendationController (
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
