package uigui;

import interfaces.PlayerIF;
import interfaces.UIFinishedObserverIF;
import interfaces.UI_Interface;
import java.util.HashMap;

/**
 * A GUI for a player to log in
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class GUI_PlayerLogin implements UI_Interface<PlayerIF> {

    /**The player logged in**/
    private PlayerIF player;

    /**A reference to the data base of players**/
    private HashMap<String, PlayerIF> players;

    /**The observer for when the UI is finished**/
    private UIFinishedObserverIF<PlayerIF> uifo;

    /**
     * Create a GUI player log in
     * @param players the db of players
     */
    public GUI_PlayerLogin(HashMap<String, PlayerIF> players) {
        this.players = players;
    }


    /**
     * Show the user interface to the user
     **/
    @Override
    public void showUI() {
        PlayerIF player = null;
        String userID;
        String password = "default";

        while (player == null) {
            userID = player.getID();
            if (players.get(userID) != null) {
                if (players.get(userID).validatePassword(password)) {
                    player = players.get(userID);
                }
            }
            if (player != null) {
                System.exit(1);
            }
        }
        this.player = player;
        finished();
    }

    /**Set the observer to listen for when the player has logged in
     * @param uifo The UIFinishedObserver that listens for when the player is logged in.
     */
    @Override
    public void setFinishedObserver(UIFinishedObserverIF<PlayerIF> uifo) {
        this.uifo =uifo;
    }

    /**Tell the observer the input is finished and pass it the selected value**/
    @Override
    public void finished() {
        uifo.inputFinished(player,this);
    }
}
