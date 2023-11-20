package use_case.recommendation;

/**
 * The interface that describes where the input data for the recommendation use case gets sent to
 */
public interface RecommendationInputBoundary {
    void execute(RecommendationInputData recommendationInputData);
}
