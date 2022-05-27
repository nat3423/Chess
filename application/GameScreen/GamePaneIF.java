package application.GameScreen;

import application.ScreenChangeHandler;
import javafx.scene.layout.Pane;

/**
 * An interface for Child nodes of GameScreen to implement
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public interface GamePaneIF {
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

}
