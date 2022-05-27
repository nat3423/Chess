package application.GameScreen;

import application.ScreenChangeHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * A class modeling the section on the top of the game screen where various
 * buttons are found
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class GameTopPane implements GamePaneIF{

    /**A screen changer to allow for switching screens**/
    ScreenChangeHandler sch;

    /**The main HBox**/
    Pane root;

    /**The different buttons on the screen**/
    Button load, save, undo, redo, settings;

    /**A popup box to show if a feature is not implemented**/
    Alert alert;

    /**
     * Constructor to create the top pane
     */
    public GameTopPane(){
        this.root = new HBox();
        this.root.getStyleClass().add("GameButtonList");
        setupPane();
    }

    /**
     * Sets up the layout for the top pane
     */
    private void setupPane(){
        this.load = new Button("Load");
        this.save = new Button("Save");
        this.undo = new Button("Undo");
        this.redo = new Button("Redo");
        this.settings = new Button("Settings");
        Button[] b = {this.load, this.save, this.undo, this.redo, this.settings};
        for (Button but: b) {
            but.getStyleClass().add("buttonStyle1");
            but.getStyleClass().add("buttonSizeS");
            but.setOnAction(buttonHandler);
            root.getChildren().add(but);
        }
    }

    /**
     * An anonymous event handler to determine what buttons should do
     */
    EventHandler<ActionEvent> buttonHandler = new EventHandler<>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Object o = actionEvent.getSource();
            if (o == load) {
                makeAlert();
            } else if (o == save) {
                makeAlert();
            } else if (o == undo) {
                makeAlert();
            } else if (o == redo) {
                makeAlert();
            } else if (o == settings) {
                sch.switchScreen(4);
            }
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
     * Gets the root of this screen
     * @return root, the root of this screen
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
}
