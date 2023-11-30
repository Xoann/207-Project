package interface_adapter.reset_password;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ResetPasswordViewModel extends ViewModel {
    public final String TITLE_LABEL = "Reset Password View";
    public final String USERNAME_LABEL = "Enter username";
    public final String PASSWORD_LABEL = "Enter new password";
    public final String REPEAT_PASSWORD_LABEL = "Re-enter new password";

    public static final String RESET_PASSWORD_BUTTON_LABEL = "Log in";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private ResetPasswordState state = new ResetPasswordState();

    public ResetPasswordViewModel() {
        super("reset password");
    }

    public void setState(ResetPasswordState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ResetPasswordState getState() {
        return state;
    }
}
