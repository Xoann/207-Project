package view;

import app.Main;
import data_access.InMemoryUserDataAccessObject;
import entity.CommonUser;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RecommendationTest {
    static String message = "";
    static boolean popUpDiscovered = false;
    JButton button;

    @BeforeEach
    public void setUp() {
        Main.main(null);
        button = getRecommendationButton();
    }

    public JButton getRecommendationButton() {
        JFrame app = null;
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            if (window instanceof JFrame) {
                app = (JFrame) window;
            }
        }

        assertNotNull(app); // found the window?

        Component root = app.getComponent(0);

        Component cp = ((JRootPane) root).getContentPane();

        JPanel jp = (JPanel) cp;

        JPanel jp2 = (JPanel) jp.getComponent(0);

        LoggedInView lv = (LoggedInView) jp2.getComponent(0);

        JPanel messagePanel = (JPanel) lv.getComponent(2);

        JPanel buttons = (JPanel) messagePanel.getComponent(1);

        return (JButton) buttons.getComponent(0); // this should be the recommendation button
    }

    @Test
    public void recommendationButtonPresent() {
        assert(button.getText().equals("Generate a Response"));
    }

    @Test
    public void errorPopupShown() {
        popUpDiscovered = false;

        createCloseTimer().start();
        button.doClick();

        assert(popUpDiscovered);
    }

    private Timer createCloseTimer() {
        ActionListener close = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                Window[] windows = Window.getWindows();
                for (Window window : windows) {

                    if (window instanceof JDialog) {

                        JDialog dialog = (JDialog)window;

                        // this ignores old dialogs
                        if (dialog.isVisible()) {
                            String s = ((JOptionPane) ((BorderLayout) dialog.getRootPane()
                                    .getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER)).getMessage().toString();
                            System.out.println("message = " + s);

                            // store the information we got from the JDialog
                            RecommendationTest.message = s;
                            RecommendationTest.popUpDiscovered = true;

                            System.out.println("disposing of..." + window.getClass());
                            window.dispose();
                        }
                    }
                }
            }

        };

        Timer t = new Timer(5000, close);
        t.setRepeats(false);
        return t;
    }
}
