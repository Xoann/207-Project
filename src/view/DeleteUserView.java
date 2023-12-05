package view;

import interface_adapter.delete_user.DeleteUserController;
import interface_adapter.delete_user.DeleteUserState;
import interface_adapter.delete_user.DeleteUserViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DeleteUserView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "delete user";

    private final DeleteUserViewModel deleteUserViewModel;
    private final JTextField usernameInputField = new JTextField(15);

    private final JLabel usernameErrorField = new JLabel();
    private final JPasswordField passwordInputField = new JPasswordField(15);

    private final JLabel passwordErrorField = new JLabel();
    private final DeleteUserController deleteUserController;

    private final JButton deleteUser;
    private final JButton cancel;

    public DeleteUserView(DeleteUserViewModel deleteUserViewModel, DeleteUserController deleteUserController) {

        this.deleteUserController = deleteUserController;
        this.deleteUserViewModel = deleteUserViewModel;
        deleteUserViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(DeleteUserViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(DeleteUserViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(DeleteUserViewModel.PASSWORD_LABEL), passwordInputField);

        JPanel buttons = new JPanel();
        deleteUser = new JButton(DeleteUserViewModel.DELETE_BUTTON_LABEL);
        buttons.add(deleteUser);
        cancel = new JButton(DeleteUserViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        deleteUser.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(deleteUser)) {
                            DeleteUserState currentState = deleteUserViewModel.getState();

                            deleteUserController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );

        cancel.addActionListener(this);

        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e){
                DeleteUserState currentState = deleteUserViewModel.getState();
                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                deleteUserViewModel.setState(currentState);
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        DeleteUserState currentState = deleteUserViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        deleteUserViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                }
        );

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        DeleteUserState state = (DeleteUserState) evt.getNewValue();
        setFields(state);
    }

    private void setFields(DeleteUserState state) {
        usernameInputField.setText(state.getUsername());
    }
}
