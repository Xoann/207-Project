package view;

import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.recommendation.RecommendationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "logged in";
    private final LoggedInViewModel loggedInViewModel;
    final JTextField messageField = new JTextField(100);
    final JButton recommendation;
    final JButton send;
    private final RecommendationController recommendationController;

    public LoggedInView(LoggedInViewModel loggedInViewModel, RecommendationController recommendController) {
        this.recommendationController = recommendController;
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("conversation");
        JPanel buttons = new JPanel();
        recommendation = new JButton(loggedInViewModel.RECOMMENDATION_BUTTON_LABEL);
        send = new JButton("Send");
        buttons.add(recommendation);
        buttons.add(send);

        recommendation.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(recommendation)) {
                            LoggedInState currState = loggedInViewModel.getState();

                            recommendationController.execute(
                                    currState.getConversationId(),
                                    currState.getMessage(),
                                    currState.getUsername());
                        }
                    }
                }
        );

        messageField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent evt) {
                        LoggedInState currState = loggedInViewModel.getState();
                        currState.setMessage(messageField.getText() + evt.getKeyChar());
                        loggedInViewModel.setState(currState);
                    }

                    @Override
                    public void keyPressed(KeyEvent evt) {}

                    @Override
                    public void keyReleased(KeyEvent evt) {}
                }
        );

        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new FlowLayout());
        messagePanel.setPreferredSize(new Dimension(1200, 50));
        messageField.setPreferredSize(new Dimension(150,20));

        messagePanel.add(messageField);
        messagePanel.add(buttons);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(messagePanel);
    }
    @Override
    public void actionPerformed(ActionEvent evt) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState loggedInState = loggedInViewModel.getState();

        if (loggedInState.getRecommendationError() != null) {
            JOptionPane.showMessageDialog(this, loggedInState.getRecommendationError());
        }

        messageField.setText(loggedInState.getMessage());
    }
}
