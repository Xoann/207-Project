package use_case.send_message;

import entity.Message;
import entity.MessageFactory;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.recommendation.RecommendationOutputData;

import java.io.IOException;
import java.util.Objects;

public class SendMessageInteractor implements SendMessageInputBoundary {

    final SendMessageConversationDataAccessInterface conversationDataAccessObject;
    final SendMessageUserDataAccessInterface userDataAccessObject;

    final SendMessageOutputBoundary sendMessagePresenter;

    final MessageFactory messageFactory;

    public SendMessageInteractor(SendMessageConversationDataAccessInterface conversationDataAccessObject,
                                 SendMessageUserDataAccessInterface userDataAccessObject, SendMessageOutputBoundary sendMessagePresenter,
                                 MessageFactory messageFactory) {
        this.conversationDataAccessObject = conversationDataAccessObject;
        this.userDataAccessObject = userDataAccessObject;
        this.sendMessagePresenter = sendMessagePresenter;
        this.messageFactory = messageFactory;
    }
    @Override
    public void execute(SendMessageInputData sendMessageInputData) {
        String messageText = sendMessageInputData.getMessage();
        String apiKey = sendMessageInputData.getSender().getApiKey();
        if (Objects.equals(messageText, "")) {
            sendMessagePresenter.prepareFailView("Empty message");
        } else if (!conversationDataAccessObject.existsById(sendMessageInputData.getId())) {
            sendMessagePresenter.prepareFailView("Conversation not found");
        } else if (!userDataAccessObject.existsByUsername(sendMessageInputData.getSender().getUsername())) {
            sendMessagePresenter.prepareFailView("User not found");
        } else {
            String isSafe = safeContent(messageText, apiKey);
            if (isSafe.equals("pass")) {
                Message message = messageFactory.create(messageText, sendMessageInputData.getSender());
                conversationDataAccessObject.save(sendMessageInputData.getId(), message);

                SendMessageOutputData sendMessageOutputData = new SendMessageOutputData(message, false);
                sendMessagePresenter.prepareSuccessView(sendMessageOutputData);
            } else {
                sendMessagePresenter.prepareFailView(isSafe);
            }
        }

    }

    private String safeContent(String message, String apiKey) {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject requestBody = new JSONObject();

        requestBody.put("input", message);

        RequestBody body = RequestBody.create(mediaType, requestBody.toString());

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/moderations")
                .method("POST", body)
                .addHeader("content-type", "application/json")
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.code() != 200 || response.body() == null) {
                return "Moderation API failed. Check API key or try again later.";
            } else {
                JSONObject responseBody = new JSONObject(response.body().string());
                JSONArray results = (JSONArray) responseBody.get("results");
                boolean result = (boolean) results.getJSONObject(0).get("flagged");
                if (!result) {
                    return "pass";
                }
                return "Inappropriate message detected!";
            }
        } catch (IOException e) {
            return "Moderation API failed. Check API key or try again later.";
        }
    }
}
