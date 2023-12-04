package interface_adapter.follow;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.Icon;

public class FollowViewModel extends ViewModel {

    public final String FOLLOW_BUTTON_LABEL = "Follow";
    public final String FOLLOWING_BUTTON_LABEL = "Following";

    public final String TITLE_LABEL = "User View";

    private FollowState state = new FollowState();

    public FollowViewModel() {
        super("view user");
    }

    public void setState(FollowState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public FollowState getState() {
        return state;
    }
}