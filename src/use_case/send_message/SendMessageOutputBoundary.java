package use_case.send_message;

public interface SendMessageOutputBoundary {
    void prepareSuccessView(SendMessageOutputData message);

    void prepareFailView(String error);
}
