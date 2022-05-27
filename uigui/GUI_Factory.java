package uigui;

import interfaces.PlayerIF;
import interfaces.UI_Interface;
import ui.UI_FactoryIF;

import java.util.HashMap;

/**
 * A factory object to create all the GUI user interfaces
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */

public class GUI_Factory implements UI_FactoryIF {

    /**
     * Build a UI for the Main Options that returns an
     * integer representing the option chosen.
     *
     * @return A UI_Interface that allows the user to select the main options for
     * ChessMeister. IT will notify its UIFinishedObserverIF instance with an
     * an integer to show which option the user selected.
     */
    @Override
    public UI_Interface<Integer> BuildMainOptions() {
        return new GUI_MainOptions();
    }

    /**
     * Build a player log in UI component or screen.
     *
     * @param db     A reference to the player log in Database.
     * @param prompt A prompt to display in the UI
     * @return A UI_Interface that allow a player to log in. It will notify
     * its UIFinishedObserverIF with a PlayerIF implementation for the player
     * logged in.
     */
    @Override
    public UI_Interface<PlayerIF> buildLogInScreen(HashMap<String, PlayerIF> db,
                                                   String prompt) {
        return new GUI_PlayerLogin(db);
    }

    /**
     * Build an AccountCreationUI component or screen.
     *
     * @return A UI_Interface allow a user to create an account. It will notify
     * its observer UIFinishedObserver with a PlayerIF implementation to its
     * UIFinishedObserver.
     */
    @Override
    public UI_Interface<PlayerIF> buildCreateAccount() {
        return new GUI_CreateAccount();
    }

    /**
     * Build an UI component for changing a user's ID
     *
     * @param db A reference to the player database.
     * @return A UI_Interface that will  allow a PlayerIF to change their ID
     * and it will notify its observer UIFinishedObserver with a Boolean to
     * show if a change occurred or not.
     */
    @Override
    public UI_Interface<Boolean> buildChangeUserID(HashMap<String, PlayerIF> db) {
        return null;
    }

    /**
     * Build a color options UI component or screen for defining color or
     * BlackAndWhite
     *
     * @return A UI_Interface implementation what will notify its observer with
     * a Boolean true if the user selected color and false if BlackAndWhite
     */
    @Override
    public UI_Interface<Boolean> buildColorOptions() {
        return null;
    }

    /**
     * This will build a user interface component for displaying player information.
     *
     * @param playerTurn The PlayerIF who's turn it is.
     * @param boardColor The color mode of the board.
     * @param message    The message to display in this UI.
     * @return An UI_Interface instance for displaying player information.
     */
    @Override
    public UI_Interface<Boolean> buildDisplayPlayer(PlayerIF playerTurn,
                                                    String boardColor, String message) {
        return null;
    }

    /**
     * Build a UI component for displaying messages.
     *
     * @return A UI_Interface implementation for displaying messages to the
     * user.
     */
    @Override
    public UI_Interface<Boolean> buildMessageDisplay() {
        return null;
    }

    /**
     * Build a UI control for taking input of position for during a game of
     * chess.
     * @param prompt The prompt to display the user.
     * @return Returns a UI_Interface implementation that represents control
     * that takes input from the player. It will notify its UIFinishedObserverIF
     * with a string when done such as a3, or f2 or q for quit.
     */
    @Override
    public UI_Interface<String> buildPositionInput(String prompt) {
        return null;
    }
}
