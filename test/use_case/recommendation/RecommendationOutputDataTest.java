package use_case.recommendation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecommendationOutputDataTest {
    RecommendationOutputData data = new RecommendationOutputData("recommendation");

    @Test
    void getRecommendation() {
        assertEquals(data.getRecommendation(), "recommendation");
    }
}