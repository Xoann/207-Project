package use_case.signup;

public class SignupOutputData {

    private final String username;
    private String creationTime;

    private boolean useCaseFailed;

    public SignupOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername(){return username;}
    public boolean getCreated() {
        return useCaseFailed;
    }

}
