package application;

/**
 * An observer pattern for integers for when an option is selected.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public interface OptionWasSelected {

    /**
     * Sends observers an integer value.
     * @param value the integer value that an observer needs
     */
    void optionWasSelected(int value);
}
