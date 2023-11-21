package interface_adapter.recommendation;

import use_case.recommendation.RecommendationInputBoundary;
import use_case.recommendation.RecommendationInputData;

/**
 * The controller for executing the recommendation use case
 */
public class RecommendationController {
    final RecommendationInputBoundary recommendationUseCaseInteractor;

    /**
     * Constructs a controller for the recommendation use case that controls the recommendation use case interactor
     * @param recommendationUseCaseInteractor the use case interactor that calls the OpenAI API to generate a
     *                                        recommendation
     */
    public RecommendationController(RecommendationInputBoundary recommendationUseCaseInteractor) {
        this.recommendationUseCaseInteractor = recommendationUseCaseInteractor;
    }

    /**
     * Executes the recommendation use case
     * @param conversationId the ID of the current conversation
     * @param recommendationInstructions the type of response to generate (i.e. sad, serious, funny, etc.)
     * @param username the current user's username
     */
    public void execute(long conversationId, String recommendationInstructions, String username) {
        RecommendationInputData recommendationInputData = new RecommendationInputData(
                conversationId, recommendationInstructions, username);
        recommendationUseCaseInteractor.execute(recommendationInputData);
    }
}
