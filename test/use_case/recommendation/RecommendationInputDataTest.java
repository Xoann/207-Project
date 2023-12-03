package use_case.recommendation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecommendationInputDataTest {
    RecommendationInputData data = new RecommendationInputData(0, "happy", "test");

    @Test
    void getConversationId() {
        assertEquals(data.getConversationId(), 0);
    }

    @Test
    void getRecommendationInstructions() {
        assertEquals(data.getRecommendationInstructions(), "happy");
    }

    @Test
    void getUsername() {
        assertEquals(data.getUsername(), "test");
    }
}