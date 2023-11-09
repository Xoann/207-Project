package interface_adapter.recommendation;

import use_case.recommendation.RecommendationOutputBoundary;
import use_case.recommendation.RecommendationOutputData;

public class RecommendationPresenter implements RecommendationOutputBoundary {
    @Override
    public void prepareSuccessView(RecommendationOutputData recommendation) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
