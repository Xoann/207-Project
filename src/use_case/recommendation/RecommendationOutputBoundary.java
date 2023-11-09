package use_case.recommendation;

public interface RecommendationOutputBoundary {
    void prepareSuccessView(RecommendationOutputData recommendation);

    void prepareFailView(String error);
}
