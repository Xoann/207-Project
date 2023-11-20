package use_case.signup;

public class SignupInputData {

    final private String username;
    final private String password;
    final private String apiKey;

    public SignupInputData(String username, String password, String apiKey) {
        this.username = username;
        this.password = password;
        this.apiKey = apiKey;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public String getApiKey() {
        return apiKey;
    }
}
