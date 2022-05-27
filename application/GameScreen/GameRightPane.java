package application.GameScreen;

import application.ScreenChangeHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * A class modeling the right side of the game board where player 2's
 * information is kept. 
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class GameRightPane implements GamePaneIF, PlayerSidesIF {

    /**The root pane for this part of the screen**/
    VBox root;

    /**The Top section of this part of the screen**/
    PlayerSidesIF top;

    /**The middle section of this part of the screen**/
    GamePaneIF middle;

    /**The button found at the bottom of this part of the screen**/
    Button bottom;

    /**A screen changer to allow for switching screens**/
    private ScreenChangeHandler sch;

    /**
     * Creates a new pane for the right part of the game screen
     * @param username the name of the player
     */
    public GameRightPane(String username) {
        this.top = new PlayerPane(2, username);
        this.middle = new PiecesPane();
        this.bottom = setupButton();
        this.root = new VBox();
        this.root.getChildren().addAll(top.getRoot(), middle.getRoot(),
                bottom);
        this.root.setId("GameLeftRightPane");
    }

    /**
     * Helper function that sets up the "exit" button
     * @return the button created
     */
    private Button setupButton(){
        Button b1 = new Button("Exit");
        b1.setOnAction(buttonHandler);
        b1.getStyleClass().add("buttonStyle1");
        b1.getStyleClass().add("buttonSizeS");
        return b1;
    }

    /**
     * An anonymous event handler to determine what buttons should do
     */
    EventHandler<ActionEvent> buttonHandler = new EventHandler<>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Object o = actionEvent.getSource();
            if (o == bottom) {
                sch.switchScreen(1);
            }
        }
    };

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
     * For displaying the captured piece to the screen
     * @param piece the piece that was captured
     * @param color the color of the piece captured
     */
    @Override
    public void setCaptured(String piece, String color) {
        top.setCaptured(piece, color);
    }

    /**
     * The name to set the player's name to
     * @param name the name to set the name with
     */
    @Override
    public void setPlayerName(String name) {
        this.top.setPlayerName(name);
    }
}