package use_case.recommendation;

/**
 * The interface that describes where the output data for the recommendation use case gets sent to
 */
public interface RecommendationOutputBoundary {
    void prepareSuccessView(RecommendationOutputData recommendation);

    void prepareFailView(String error);
}
