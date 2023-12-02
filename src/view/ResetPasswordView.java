package view;

import interface_adapter.reset_password.ResetPasswordController;
import interface_adapter.reset_password.ResetPasswordState;
import interface_adapter.reset_password.ResetPasswordViewModel;
import interface_adapter.switch_to_login.SwitchToLoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ResetPasswordView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "reset password";
    private final ResetPasswordViewModel resetPasswordViewModel;

    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    final JPasswordField passwordInputField = new JPasswordField(15);
    final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    final JButton resetPassword;
    final JButton cancel;
    private final ResetPasswordController resetPasswordController;

    public ResetPasswordView(ResetPasswordViewModel resetPasswordViewModel, ResetPasswordController controller, SwitchToLoginController switchToLoginController) {

        this.resetPasswordController = controller;
        this.resetPasswordViewModel = resetPasswordViewModel;
        this.resetPasswordViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Reset Password Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        /*LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);*/
        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel("Repeat Password"), repeatPasswordInputField);

        JPanel buttons = new JPanel();
        resetPassword = new JButton(ResetPasswordViewModel.RESET_PASSWORD_BUTTON_LABEL);
        buttons.add(resetPassword);
        cancel = new JButton(ResetPasswordViewModel.CANCEL_BUTTON_LABEL);
        buttons.add(cancel);

        resetPassword.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(resetPassword)) {
                            ResetPasswordState currentState = resetPasswordViewModel.getState();

                            resetPasswordController.execute(
                                    currentState.getUsername(),
                                    currentState.getNewPassword(),
                                    currentState.getRepeatNewPassword()
                            );
                        }
                    }
                }
        );

        //TODO: Implement cancel to switch to Login View
        cancel.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(e.getSource().equals(cancel)) {
                            switchToLoginController.execute();
                        }
                    }
                }
        );

        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                ResetPasswordState currentState = resetPasswordViewModel.getState();
                currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                resetPasswordViewModel.setState(currentState);
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
                        ResetPasswordState currentState = resetPasswordViewModel.getState();
                        currentState.setNewPassword(passwordInputField.getText() + e.getKeyChar());
                        resetPasswordViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        repeatPasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        ResetPasswordState currentState = resetPasswordViewModel.getState();
                        currentState.setRepeatNewPassword(repeatPasswordInputField.getText() + e.getKeyChar());
                        resetPasswordViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ResetPasswordState state = (ResetPasswordState) evt.getNewValue();
        if(!(state.getPasswordError() == null)) {
            passwordErrorField.setText(state.getPasswordError());
        } else {
            usernameErrorField.setText(state.getUsernameError());
        }
    }
}
