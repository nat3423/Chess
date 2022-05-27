package application.GameScreen;

import application.ScreenChangeHandler;
import javafx.scene.layout.Pane;

/**
 * An interface for the side panels in the GUI to implement where captured pieces are shown
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public interface PlayerSidesIF {

    /**
     * The name to set the player's name to
     * @param name the name to set the name with
     */
    void setPlayerName(String name);

    /**
     * Returns the root pane for this part of the screen
     * @return the root pane for this part of the screen
     */
    Pane getRoot();

    /**
     * Sets the screen changer to be used with this screen
     * @param sch the screen changer to use
     */
    void setScreenChangeHandler(ScreenChangeHandler sch);

    /**
     * For displaying the captured piece to the screen
     * @param piece the piece that was captured
     * @param color the color of the piece captured
     */
    void setCaptured(String piece, String color);
}
