package use_case.send_message;

import entity.Message;

public class SendMessageInputData {

    final private Message message;

    public SendMessageInputData(Message message) {
        this.message = message;
    }

    Message getMessage() { return message; }

}
