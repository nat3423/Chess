package application;

/**
 * The interface to allow for the application to switch screens
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public interface ScreenChangeHandler {
    /**
     * Allows the user to switch screens by pressing different buttons
     * @param screen the number of the screen to switch to
     */
    void switchScreen(int screen);
}
