package application;

import application.GameScreen.NameChangeHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.*;

/**
 * The player names screen which allows users to input their names
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class PlayerNames {
    /**The main VBox**/
    VBox root;
    /**The different buttons on the page**/
    Button play, exit;
    /**A screen changer to allow for switching screens**/
    private ScreenChangeHandler sch;

    /**Holding references to the textFields**/
    TextField tfp1, tfp2;

    /**For holding a reference to the nameHandler**/
    private NameChangeHandler nameHandler;

    /**
     * Constructor to create the screen where players enter their name
     */
    public PlayerNames() {
        root = new VBox();
        defineLayout();
    }
    /**
     * Sets the layout for this screen
     */
    public void defineLayout() {
        VBox vb1 = root;
        vb1.setId("Background");
        vb1.setAlignment(Pos.TOP_CENTER);
        vb1.setSpacing(40);

        VBox vb2 = new VBox();
        vb2.setSpacing(40);
        vb2.setAlignment(Pos.CENTER);

        Label title = new Label("Enter Player Names");
        title.getStyleClass().add("bigLabel");
        vb2.getChildren().add(title);

        Label p1 = new Label("Player 1 name");
        p1.getStyleClass().add("littleLabel");
        vb2.getChildren().add(p1);
        tfp1 = new TextField();
        tfp1.getStyleClass().add("textField1");
        vb2.getChildren().add(tfp1);

        Label p2 = new Label("Player 2 name");
        p2.getStyleClass().add("littleLabel");
        vb2.getChildren().add(p2);
        tfp2 = new TextField();
        tfp2.getStyleClass().add("textField1");
        vb2.getChildren().add(tfp2);

        HBox hb2 = new HBox();
        hb2.setAlignment(Pos.BOTTOM_RIGHT);
        hb2.setSpacing(250);

        play = new Button("Play");
        play.getStyleClass().add("buttonStyle1");
        play.getStyleClass().add("buttonSizeS");
        play.setOnAction(buttonHandler);
        hb2.getChildren().add(play);

        exit = new Button("Exit");
        exit.getStyleClass().add("buttonStyle1");
        exit.getStyleClass().add("buttonSizeS");
        exit.setOnAction(buttonHandler);
        hb2.getChildren().add(exit);

        vb1.getChildren().add(vb2);
        vb1.getChildren().add(hb2);
    }

    /**
     * Gets the root of this screen
     * @return root, the root of this screen
     */
    public Pane getRoot() {
        return root;
    }

    /**
     * An anonymous event handler to determine what buttons should do
     */
    EventHandler<ActionEvent> buttonHandler = new EventHandler<>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Object o = actionEvent.getSource();
            if (o == play) {
                String p1name = tfp1.getText();
                String p2name = tfp2.getText();
                nameHandler.setPlayer1Name(p1name);
                nameHandler.setPlayer2Name(p2name);
                sch.switchScreen(3);
            } else if (o == exit) {
                sch.switchScreen(1);
            }
        }
    };

    /**
     * Sets the screen changer to be used with this screen
     * @param sch the screen changer to use
     */
    public void setScreenChangeHandler(ScreenChangeHandler sch) {
        this.sch = sch;
    }

    /**
     * Sets the name change handler for this class
     * @param nameHandler the new handler to set it with
     */
    public void setNameChangeHandler(NameChangeHandler nameHandler) {
        this.nameHandler = nameHandler;
    }
}
