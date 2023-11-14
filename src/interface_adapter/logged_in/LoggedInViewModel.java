package interface_adapter.logged_in;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoggedInViewModel extends ViewModel {
    public final String RECOMMENDATION_BUTTON_LABEL = "Generate a Response";
    private LoggedInState state = new LoggedInState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public LoggedInViewModel() {
        super("logged in");
    }

    @Override
    public void firePropertyChanged() { support.firePropertyChange("state", null, this.state); }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public LoggedInState getState() { return state; }

    public void setState(LoggedInState state) { this.state = state; }
}