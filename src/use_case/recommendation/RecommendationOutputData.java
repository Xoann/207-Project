package use_case.recommendation;

/**
 * A class that represents the output data from a recommendation
 */
public class RecommendationOutputData {
    private final String recommendation;

    /**
     * Constructs the output data given a recommended response
     * @param recommendation
     */
    public RecommendationOutputData(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getRecommendation() {
        return this.recommendation;
    }

}
