package uigui;

import interfaces.PlayerIF;
import interfaces.UIFinishedObserverIF;
import interfaces.UI_Interface;
import model.Player;

/**
 * A GUI for player account creation
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class GUI_CreateAccount implements UI_Interface<PlayerIF> {

    /**The playerIF instance created**/
    private PlayerIF player;

    /**The observer for when the UI is finished**/
    private UIFinishedObserverIF<PlayerIF> uifo;

    /**
     * Show the user interface to the user
     **/
    @Override
    public void showUI() {
        player = null;

    }

    /**
     * Creates a player with default values other than their name/userid
     * @param name the name of the player
     */
    public void setPlayer(String name) {
        player = new Player(name, "default", name, "default");
    }

    /**
     * Set the class to be notified when the UI is finished.
     * @param uifo The UIFinishedObserverIF implementation.
     */
    public void setFinishedObserver(UIFinishedObserverIF<PlayerIF> uifo) {
        this.uifo =  uifo;
    }

    /**When the UI is finished notify the observer**/
    public void finished() {
        if(uifo != null)
            uifo.inputFinished(player,this);

    }
}
