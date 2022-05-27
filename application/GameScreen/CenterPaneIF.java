package application.GameScreen;

import application.MessageHandlerIF;
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
public interface CenterPaneIF extends GamePaneIF {
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
     * Initializes the messenger to set this class to
     * @param messenger the messenger to send messages to
     */
    void setMessageHandler(MessageHandlerIF messenger);

    /**
     * Sets the color of the tiles to a new value
     * @param whiteColor the color of the white tiles to set
     * @param blackColor the color of the black tiles to set to
     */
    void setTileColor(String whiteColor, String blackColor);

    /**
     * Initializes the captured piece handler for sending the captured pieces
     * to their respected players
     * @param pieceHandler the handler to set this class' piece handler to
     */
    void setPieceCaptureHandler(PieceCapturedHandlerIF pieceHandler);
}