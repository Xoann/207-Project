package interface_adapter.delete_user;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DeleteUserViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Delete User";

    public static final String USERNAME_LABEL = "Enter Username";

    public static String PASSWORD_LABEL = "Enter Password";

    public static final String DELETE_BUTTON_LABEL = "Delete";

    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private DeleteUserState state = new DeleteUserState();

    public DeleteUserViewModel() {
        super("delete user");
    }

    public void setState(DeleteUserState state) {
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

    public DeleteUserState getState() {
        return state;
    }
}
