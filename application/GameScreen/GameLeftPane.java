package application.GameScreen;

import application.ScreenChangeHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * A class modeling the left side of the game screen where the player number.
 * username, and 'captured:' labels are.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */

public class GameLeftPane implements GamePaneIF, PlayerSidesIF {

    /** The root pane for this part of the screen */
    Pane root;

    /** The Top section of this part of the screen */
    PlayerSidesIF top;

    /** The Middle section of this part of the screen */
    GamePaneIF middle;

    /** The button found at the bottom of this part of the screen*/
    Button bottom;

    /* Used to display a feature not implemented alert */
    /** We may be able to get rid of this once functionality is added to the
     * button */
    Alert alert;

    /**A screen changer to allow for switching screens**/
    ScreenChangeHandler sch;

    /**
     * Creates a new pane for the left part of the game screen
     * @param username the username of player 1
     */
    public GameLeftPane(String username){
        this.top = new PlayerPane(1, username);
        this.middle = new PiecesPane();
        this.bottom = setupButton();
        this.root = new VBox();
        this.root.setId("GameLeftRightPane");
        this.root.getChildren().addAll(top.getRoot(), middle.getRoot(),
                bottom);
    }

    /**
     * A helper function that sets up the "show" button
     * @return the button created
     */
    private Button setupButton(){
        Button b1 = new Button("Show");
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
            makeAlert();
        }
    };

    /**
     * Creates an alert box to inform the user if a feature is not implemented
     */
    public void makeAlert() {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("This feature is currently not implemented!");
        alert.setHeaderText("");
        alert.show();
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
