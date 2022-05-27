package application;

/**
 * Allows main to inform screens the last screen changed to (excluding
 * screens that just return to the screen that accessed them.)
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public interface LastScreenIF {
    /**
     * A reference to the last screen that the user swapped to.
     * @param screenNum the last screen the application swapped to.
     */
    void lastScreenSelected(int screenNum);
}
