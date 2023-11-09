package use_case.recommendation;

import entity.Conversation;
import entity.Message;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/**
 * A class that generates recommendations
 */
public class RecommendationInteractor implements RecommendationInputBoundary{
    final RecommendationUserDataAccessInterface userDataAccessObject;
    final RecommendationConversationDataAccessInterface conversationDataAccessObject;
    final RecommendationOutputBoundary recommendationPresenter;

    /**
     * Constructs the recommendation interactor given the data access objects and the recommendation presenter
     * @param userDataAccessObject the data store for user data
     * @param conversationDataAccessObject the data store for conversation data
     * @param recommendationPresenter the presenter for recommendations
     */
    public RecommendationInteractor(RecommendationUserDataAccessInterface userDataAccessObject,
                                    RecommendationConversationDataAccessInterface conversationDataAccessObject,
                                    RecommendationOutputBoundary recommendationPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.conversationDataAccessObject = conversationDataAccessObject;
        this.recommendationPresenter = recommendationPresenter;
    }

    /**
     * Calls the OpenAI API to generate a recommendation for a reply to a conversation
     * @param recommendationInputData the current conversation's ID, current user's username, and the instruction for generating a recommendation
     */
    @Override
    public void execute(RecommendationInputData recommendationInputData) {
        Long conversationId = recommendationInputData.getConversationId();
        String recommendationInstructions = recommendationInputData.getRecommendationInstructions();
        String username = recommendationInputData.getUsername();
        String apiKey;
        String conversation;

        if (userDataAccessObject.existsByUsername(username)) {
            apiKey = userDataAccessObject.get(username).getApiKey();
        } else {
            recommendationPresenter.prepareFailView("User not found.");
            return;
        }

        if (conversationDataAccessObject.existsById(conversationId)) {
            conversation = parseConvo(conversationDataAccessObject.get(conversationId));
        } else {
            recommendationPresenter.prepareFailView("Conversation not found.");
            return;
        }

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject requestBody = new JSONObject();
        JSONArray messagesArray = new JSONArray();

        JSONObject instructions = new JSONObject();
        instructions.put("role", "system");
        instructions.put("content", recommendationInstructions);
        messagesArray.put(instructions);

        JSONObject convo = new JSONObject();
        convo.put("role", "user");
        convo.put("content", conversation + username + ": ");
        messagesArray.put(convo);

        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", messagesArray);
        requestBody.put("temperature", 0.7);
        RequestBody body = RequestBody.create(mediaType, requestBody.toString());

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .method("POST", body)
                .addHeader("content-type", "application/json")
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.code() != 200 || response.body() == null) {
                recommendationPresenter.prepareFailView("API call failed. Please try again later.");
            } else {
                JSONObject responseBody = new JSONObject(response.body().string());
                RecommendationOutputData recommendation = new RecommendationOutputData(parseResponse(responseBody));
                recommendationPresenter.prepareSuccessView(recommendation);
            }
        } catch (IOException e) {
            recommendationPresenter.prepareFailView("API call failed. Please try again later.");
        }
    }

    /**
     * Parses a conversation into a single string to be passed in to the OpenAI API in the form:
     *  username1: message1 \n
     *  username2: message2 \n
     *  ...
     *
     * @param convo the conversation to parse
     * @return the entire conversation history
     */
    private String parseConvo(Conversation convo) {
        StringBuilder conversationHistory = new StringBuilder();
        for (Message message : convo.getMessages()) {
            conversationHistory.append(message.getSender().getUsername()).append(": ");
            conversationHistory.append(message.getMessage()).append("\n");
        }
        return conversationHistory.toString();
    }

    /**
     * Parses the returned response from OpenAI and extracts the generated recommendation
     * @param responseBody OpenAI's response
     * @return the generated recommendation
     */
    private String parseResponse(JSONObject responseBody) {
        JSONArray choices = (JSONArray) responseBody.get("choices");
        JSONObject messages = (JSONObject) choices.getJSONObject(0).get("message");
        return (String) messages.get("content");
    }
}
