package interface_adapter.selector;

import interface_adapter.ViewModel;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Arrays;
import java.util.List;

public class SelectorViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Tier List Selector";
//    public static final String INSTRUCTIONS1 = "Either view and edit existing tier lists,";
//    public static final String INSTRUCTIONS2 = "or select how you wish to generate a new one.";
//    public static final List<String> INSTRUCTIONS_LIST = Arrays.asList("Selecting <b>VIEW EXISTING</b> will allow you to choose from the tier lists you have already sorted, edit them and save these changes if you wish.", "Selecting the <b>RANDOM</b> option will allow you to enter a topic of your choosing. From this prompt a tierlist will then be automatically generated for you to organise.", "Selecting the <b>CUSTOM</b> option will allow you to enter the items you want to place in your tier list. You will be required to type in 10 different items which you can then rank.", " Finally, selecting the <b>TEMPLATE</b> option allows you to select a topic for a premade list and organise these items in a tierlist.");
    public static final String INSTRUCTIONS1 = "Selecting <b>VIEW EXISTING</b> will allow you to choose from the tier lists you have already sorted, edit them and save these changes if you wish.";
    public static final String INSTRUCTIONS2 = "Selecting the <b>RANDOM</b> option will allow you to enter a topic of your choosing. From this prompt a tierlist will then be automatically generated for you to organise.<br><br> Selecting the <b>CUSTOM</b> option will allow you to enter the items you want to place in your tier list. You will be required to type in 10 different items which you can then rank.<br><br> Finally, selecting the <b>TEMPLATE</b> option allows you to select a topic for a premade list and organise these items in a tierlist.";
    public static final List<Color> COLORS = Arrays.asList(Color.red, Color.blue, Color.green, Color.orange);
    public static final List<String> BUTTONS = Arrays.asList("VIEW EXISTING", "RANDOM", "CUSTOM", "TEMPLATE");
    public static final Font TITLE_FONT = new Font("Arial Bold", Font.PLAIN, 30);
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 15);
    public static final Font BUTTON_FONT = new Font("Arial", Font.ITALIC, 25);
    public static final String VIEW_EXISTING_BUTTON = "VIEW EXISTING";
    public static final String RANDOM_BUTTON = "RANDOM";
    public static final String CUSTOM_BUTTON = "CUSTOM";
    public static final String TEMPLATE_BUTTON = "TEMPLATE";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public SelectorState state;

    public SelectorViewModel(String name) {
        super(name);
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SelectorState getState() {
        return this.state;
    }

    public void setState(SelectorState state) {
        this.state = state;
    }
}
