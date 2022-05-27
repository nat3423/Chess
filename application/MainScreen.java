package application;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The Main Screen that shows different options to get to different screens
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class MainScreen {
    /**The different panes for each section of the BorderPane**/
    Pane top, bottom, left, right, center;

    /**The main BorderPane**/
    BorderPane root;

    /**THe different buttons on the page**/
    Button b1, b2, b3, b4, b5, b6, b7;

    /**A popup box to show if a feature is not implemented**/
    Alert alert;

    /**A screen changer to allow for switching screens**/
    private ScreenChangeHandler sch;

    /**Reference to observers of the main screen*/
    private OptionWasSelected oWS;

    /**Singelton instance to this screen.*/
    private static MainScreen instance;

    /**
     * Constructor to create the main screen
     */
    MainScreen() {
        super();
        defineLayout();
    }

    /**
     * Sets the layout for each pane of the BorderPane
     */
    public void defineLayout() {
        //Init panes
        root = new BorderPane();
        root.setId("Background");
        top = new StackPane();
        bottom = new HBox();
        bottom.setId("Bottom");
        left = new Pane();
        right = new VBox();
        right.setId("Right");
        center = new VBox();
        center.setId("Center");

        //Add panes
        root.setTop(top);
        root.setBottom(bottom);
        root.setLeft(left);
        root.setRight(right);
        root.setCenter(center);

        //Define each pane's contents
        defineTop(top);
        defineCenter(center);
        defineBottom(bottom);
        defineRight(right);
    }

    /**
     * Sets the layout for the top pane, which contains the title
     * @param root the pane for the top pane
     */
    public void defineTop(Pane root) {
        Label lbl = new Label("Chess Meister");
        lbl.setId("Title");
        root.getChildren().add(lbl);
    }

    /**
     * Sets the layout for the center pane, which contains 5 buttons
     * @param root the pane for the center pane
     */
    public void defineCenter(Pane root) {
        b1 = new Button("Player vs Player");
        b2 = new Button("Player vs CPU");
        b3 = new Button("Online Play");
        b4 = new Button("Rules of Chess");
        b5 = new Button("Tutorial");
        Button[] b = {b1, b2, b3, b4, b5};
        for (Button but: b) {
            but.getStyleClass().add("buttonStyle1");
            but.getStyleClass().add("buttonSizeL");
            but.setOnAction(buttonHandler);
            root.getChildren().add(but);
        }
    }

    /**
     * Sets the layout for the bottom pane, which contains 2 buttons
     * @param root the pane for the bottom pane
     */
    public void defineBottom(Pane root) {
        b6 = new Button("Settings");
        b7 = new Button("Exit");

        b6.getStyleClass().add("buttonStyle1");
        b6.getStyleClass().add("buttonSizeS");
        b6.setOnAction(buttonHandler);
        root.getChildren().add(b6);

        b7.getStyleClass().add("buttonStyle1");
        b7.getStyleClass().add("buttonSizeS");
        b7.setOnAction(buttonHandler);
        root.getChildren().add(b7);
    }

    /**
     * Sets the layout for the right pane, which contains an image
     * @param root the pane for the right pane
     */
    public void defineRight(Pane root) {
        try {
            Image img = new Image(new FileInputStream(
                    "src/application/ChessPieces/WK.png"));
            ImageView imv = new ImageView(img);
            imv.setImage(img);
            root.getChildren().add(imv);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: Image not found");
            System.exit(1);
        }
    }

    /**
     * Gets the root of this screen
     * @return root, the root of this screen
     */
    public Pane getRoot() {
        return root;
    }

    /**
     * Creates an alert box to inform the user if a feature is not
     * implemented
     */
    public void makeAlert() {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("This feature is currently not implemented!");
        alert.setHeaderText("");
        alert.show();
    }

    /**
     * An anonymous event handler to determine what buttons should do
     */
    EventHandler<ActionEvent> buttonHandler = new EventHandler<>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Object o = actionEvent.getSource();
            if (o == b1) {
                //PlayerScreen
                notify_obs(2);
                sch.switchScreen(2);
            } else if (o == b2) {
                makeAlert();
            } else if (o == b3) {
                makeAlert();
            } else if (o == b4) {
                makeAlert();
            } else if (o == b5) {
                makeAlert();
            } else if (o == b6) {
                //SettingScreen
                sch.switchScreen(4);
            } else if (o == b7) {
                System.exit(0);
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
     * Setter for the observers of screen changes
     * @param observer the observer of a screen change
     */
    public void setOptionWasSelected(OptionWasSelected observer) {
        oWS = observer;
    }

    /**
     * Method that notifies the observers
     * @param value the value for the screen that was chosen.
     */
    public void notify_obs(int value) {
        if (oWS != null) {
            oWS.optionWasSelected(value);
        }
    }

    /**
     * Returns the single instance of this screen.
     * @return the isntance of this screen
     */
    public static MainScreen getInstance() {
        if (instance == null) instance = new MainScreen();
        return instance;
    }
}
