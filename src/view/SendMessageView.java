package view;

import interface_adapter.logged_in.LoggedInState;
import interface_adapter.send_message.SendMessageController;
import interface_adapter.logged_in.LoggedInViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SendMessageView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "send message";
    private final LoggedInViewModel sendMessageViewModel;
    final JTextArea conversationTextArea = new JTextArea();
    private final JTextField messageInputField = new JTextField(15);
    private final JButton sendMessage;
    private final SendMessageController sendMessageController;

    public SendMessageView(LoggedInViewModel sendMessageViewModel, SendMessageController controller) {
        this.sendMessageController = controller;
        this.sendMessageViewModel = sendMessageViewModel;
        this.sendMessageViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Send Message Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

//        infos
        JPanel buttons = new JPanel();

        sendMessage = new JButton(sendMessageViewModel.SEND_MESSAGE_BUTTON_LABEL);
        buttons.add(sendMessage);

        sendMessage.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        if (event.getSource().equals(sendMessage)) {
                            LoggedInState currentState = sendMessageViewModel.getState();

//                            Get user obj and pass it into execute
//                            User sender =

//                            sendMessageController.execute(currentState.getMessage(),
//                                    sender,
//                                    currentState.getConversationId());
                        }
                    }
                }
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
