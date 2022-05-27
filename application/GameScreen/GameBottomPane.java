package application.GameScreen;

import application.MessageHandlerIF;
import application.ScreenChangeHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * A class modeling the bottom section of the game screen where the status of
 * the currently selected piece goes
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class GameBottomPane implements GamePaneIF, MessageHandlerIF {

    ScreenChangeHandler sch;

    /** This field is the root pane of the bottom */
    Pane root;

    /** This field is the text stored in the root pane */
    Label text;

    /**
     * Creates a bottom pane for the game screen and populates it with some
     * test text for now
     */
    public GameBottomPane(){
        this.root = new HBox();
        this.text = new Label("Player 1's Turn");
        this.text.getStyleClass().add("Status");
        this.root.getChildren().add(this.text);
    }

    /**
     * Sets the text found in the label
     * @param newText the text to place inside
     */
    public void setText(String newText){
        this.text.setText(newText);
    }

    /**
     * Returns the root pane of this part of the screen
     * @return the root pane of this part of the screen
     */
    public Pane getRoot(){
        return this.root;
    }

    /**
     * Sets the screen changer to be used with this screen
     * @param sch the screen changer to use
     */
    public void setScreenChangeHandler(ScreenChangeHandler sch) {
        this.sch = sch;
    }

    /**
     * Sends a message to the message handler
     * @param message the message to send to the messenger
     */
    public void sendMessage(String message) {
        text.setText(message);
    }
}