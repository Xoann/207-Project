import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class APITest {
    private static final String API_TOKEN = System.getenv("OPENAI_API_KEY");
    public static void callAPI(String[] conversation) throws JSONException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        JSONObject requestBody = new JSONObject();
        JSONArray messagesArray = new JSONArray();
        JSONObject message;
        requestBody.put("model", "gpt-3.5-turbo");
        for (String reply : conversation) {
            message = new JSONObject();
            message.put("role", "user");
            message.put("content", reply);
            messagesArray.put(message);
        }

        requestBody.put("messages", messagesArray);
        requestBody.put("temperature", 0.7);
        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .method("POST", body)
                .addHeader("content-type", "application/json")
                .addHeader("Authorization", "Bearer " + API_TOKEN)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            if (response.code() != 200) {
                throw new RuntimeException(response.message());
            } else {
                System.out.println("Success!");
            }

            JSONObject responseBody = new JSONObject(response.body().string());
            System.out.println(responseBody);
        }
        catch (JSONException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        callAPI(new String[] {"What is the course CSC207 at the University of Toronto?"});
    }
}
