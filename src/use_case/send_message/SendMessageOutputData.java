package use_case.send_message;

import entity.Message;

public class SendMessageOutputData {
    private final Message message;
    private boolean useCaseFailed;

    public SendMessageOutputData(Message message, boolean useCaseFailed) {
        this.message = message;
        this.useCaseFailed = useCaseFailed;
    }

    public Message getMessage() {
        return message;
    }
}
