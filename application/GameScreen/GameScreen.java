package application.GameScreen;

import application.MessageHandlerIF;
import javafx.scene.Node;
import javafx.scene.layout.*;

/**
 * The Game Screen is the screen where the actual gameplay will take place and
 * supplementary information will be
 * displayed.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class GameScreen extends Pane implements NameChangeHandler,
        MessageHandlerIF, PieceCapturedHandlerIF {

    /** The panes that make up the borderpane **/
    GamePaneIF top, bottom;

    /**A reference to the center screen**/
    CenterPaneIF center;

    /**Holds a reference to the left and right screens**/
    PlayerSidesIF left, right;

    /**the main pane**/
    BorderPane root;

    /**The names of the players**/
    String p1name, p2Name;

    /**The messenger to send messages**/
    MessageHandlerIF messenger;

    /**
     * Creates the whole game screen with all of its individual panes
     * @param username1 the name of the first player
     * @param username2 the name of the second player
     */
    public GameScreen(String username1, String username2){
        defineLayout(username1, username2);
        this.setMessageHandler((MessageHandlerIF) bottom);
    }

    /**
     * Set the layout for each pane of the BorderPane
     * @param username1 the name of the first player
     * @param username2 the name of the second player
     */
    public void defineLayout(String username1, String username2){
        this.top = new GameTopPane();
        this.top.getRoot().getStyleClass().add("GameTopBottomPane");

        this.bottom = new GameBottomPane();
        this.bottom.getRoot().getStyleClass().add("GameTopBottomPane");

        this.left = new GameLeftPane(username1);
        this.left.getRoot().getStyleClass().add("GameLeftRightPane");

        this.right = new GameRightPane(username2);
        this.right.getRoot().getStyleClass().add("GameLeftRightPane");

        this.center = new GameCenterPane();
        this.center.getRoot().getStyleClass().add("GameCenterPane");
        this.center.setPieceCaptureHandler(this);

        this.root = new BorderPane(center.getRoot(), top.getRoot(),
                right.getRoot(), bottom.getRoot(), left.getRoot());
        this.root.setId("Background");
    }

    /**
     * Gets the root of this screen
     * @return root, the root of this screen
     */
    public BorderPane getRoot(){
        return this.root;
    }

    /**
     * Gets the styleable node of this screen
     * @return the styleable node of this screen
     */
    @Override
    public Node getStyleableNode() {
        return super.getStyleableNode();
    }

    /**
     * Gets the right pane root of this screen
     * @return the right pane root of this screen
     */
    public PlayerSidesIF getRight() {
        return right;
    }

    /**
     * Gets the top pane root of this screen
     * @return the top pane root of this screen
     */
    public GamePaneIF getTop() {
        return top;
    }

    /**
     * Gets the center pane root of this screen
     * @return the center pane root of this screen
     */
    public GamePaneIF getCenter() {
        return center;
    }

    /**
     * For setting the first player's name
     * @param name the new name to set to
     */
    @Override
    public void setPlayer1Name(String name) {
        this.p1name = name;
        this.left.setPlayerName(name);
    }

    /**
     * For setting the second player's name
     * @param name the new name to set to
     */
    @Override
    public void setPlayer2Name(String name) {
        this.p2Name = name;
        this.right.setPlayerName(name);
    }

    /**
     * Initializes the messenger to set this class to
     * @param messenger the messenger to send messages to
     */
    public void setMessageHandler(MessageHandlerIF messenger) {
        center.setMessageHandler(messenger);
    }

    /**
     * Sends a message to the message handler
     * @param message the message to send to the messenger
     */
    @Override
    public void sendMessage(String message) {
        messenger.sendMessage(message);
    }

    /**
     * Sends the tile colors to the center screen
     * @param whiteColor the new color to set the white tile colors to
     * @param blackColor the new color to set the black tiles to
     */
    public void setTileColor(String whiteColor, String blackColor) {
        center.setTileColor(whiteColor, blackColor);
    }

    /**
     * For displaying the captured piece to the screen
     *
     * @param whiteCaptured if white captured this piece
     * @param piece         the piece that was captured
     * @param color         the color of the piece captured
     */
    @Override
    public void setCaptured(boolean whiteCaptured, String piece,
                            String color) {

        //If white captured a piece
        if(whiteCaptured) {

            left.setCaptured(piece, color);

        //If black captured a piece
        } else {
            right.setCaptured(piece, color);
        }
    }
}
