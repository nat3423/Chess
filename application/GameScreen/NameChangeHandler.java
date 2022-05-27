package application.GameScreen;

/**
 * Sets new values for the player names when they are changed
 *  in the PlayerNames class
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public interface NameChangeHandler {

    /**
     * For setting the first player's name
     * @param name the new name to set to
     */
    void setPlayer1Name(String name);

    /**
     * For setting the second player's name
     * @param name the new name to set to
     */
    void setPlayer2Name(String name);

}
