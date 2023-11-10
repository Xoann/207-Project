package interface_adapter.recommendation;

import use_case.recommendation.RecommendationInputBoundary;
import use_case.recommendation.RecommendationInputData;

public class RecommendationController {
    final RecommendationInputBoundary recommendationUseCaseInteractor;

    public RecommendationController(RecommendationInputBoundary recommendationUseCaseInteractor) {
        this.recommendationUseCaseInteractor = recommendationUseCaseInteractor;
    }

    public void execute(long conversationId, String recommendationInstructions, String username) {
        RecommendationInputData recommendationInputData = new RecommendationInputData(
                conversationId, recommendationInstructions, username);
        recommendationUseCaseInteractor.execute(recommendationInputData);
    }
}
