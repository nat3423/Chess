package application.Settings;

import application.GameScreen.TileColorChangeHandler;
import application.LastScreenIF;
import application.ScreenChangeHandler;
import application.colorScreen.ColorChangerIF;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.*;

/**
 * The Settings Screen that displays options for the user to
 * manipulate the visuals and settings of the game.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class Settings implements ColorChangerIF, LastScreenIF {
    /**The root of the Settings Screen**/
    Pane root;

    /**Labels for the Settings Page*/
    private Label titleL, colorsL, undoL;

    /**Buttons for the Settings Page*/
    private Button saveB, exitB, blackSquare, whiteSquare;

    /**Allows this screen to change screens**/
    ScreenChangeHandler sch;

    /**If the color to change is for the whiteSquares**/
    public boolean changeWhite;

    /**Sends a signal to this handler when the user wants to change
     * the color of the tiles
     */
    TileColorChangeHandler tileHandler;

    /**Holds a reference to the white and black tile colors**/
    String blackTileColor, whiteTileColor;

    /**A reference to the last screen swapped to.*/
    int lastScreen;

    /**
     * Constructs the Settings Screen
     */
    public Settings() {
        super();
        defineLayout();
    }

    public void setLastScene(int s){
        lastScreen = s;
    }

    /**
     * Gets the root of this screen
     * @return root, the root of this screen
     */
    public Pane getRoot() {
        return root;
    }

    /**
     * Sets the layout for each pane of the HBox
     */
    public void defineLayout(){
        //Creating the initial, outer Pane
        root = new VBox();
        root.setId("Main");
        root.setId("Background");

        //Adding the title label and an anchor pane.
        titleL = new Label("Settings");
        titleL.setId("Title");
        titleL.setMaxWidth(Double.MAX_VALUE);
        titleL.setAlignment(Pos.CENTER);
        Pane anchor1 = new AnchorPane();
        root.getChildren().addAll(titleL, anchor1);

        //Creating the VBox (color and undo options) and it's Panes, anchored left
        Pane vBox1 = new VBox();
        colorsL = new Label("COLORS");
        colorsL.setId("LabelL");
        blackSquare = new Button();
        whiteSquare = new Button();
        Pane hBox2 = labelWithButton("Black Squares:\t", blackSquare, "LabelM",
                "-fx-background-color: #273746", "buttonSizeSquareS");
        Pane hBox3 = labelWithButton("White Squares:\t", whiteSquare,"LabelM",
                "-fx-background-color: #f5deb3", "buttonSizeSquareS");
        undoL = new Label("UNDO");
        undoL.setId("LabelL");
        Pane hBox4 = checkBoxWithLabel("Enabled", "LabelM");
        Pane hBox5 = checkBoxWithLabel("Unlimited Undos", "LabelM");
        Pane hBox6 = textFieldWithLabel("Max Undos", "LabelM");
        vBox1.getChildren().addAll(colorsL, hBox2, hBox3, undoL, hBox4, hBox5, hBox6);
        AnchorPane.setLeftAnchor(vBox1, 10.0);

        //Creating an HBox (Show Moves checkbox) and anchoring it top right
        Pane hBox7 = checkBoxWithLabel("ShowMoves", "LabelM");
        AnchorPane.setTopAnchor(hBox7, 20.0);
        AnchorPane.setRightAnchor(hBox7, 40.0);

        //Creating a VBox (Save and Exit buttons) and anchoring it bottom right
        Pane vBox2 = twoVerticalButtons("Save", "Exit", "buttonSizeS");
        AnchorPane.setBottomAnchor(vBox2, 20.0);
        AnchorPane.setRightAnchor(vBox2, 20.0);

        anchor1.getChildren().addAll(vBox1, hBox7, vBox2);
    }

    /**
     * Create a label with a button right next to it.
     * @param lab the content for the label
     * @param labId the id for the label
     * @param style the style for the button
     * @param size the size for the button
     * @return an HBox containing the label and the button.
     */
    public HBox labelWithButton(String lab, Button btn, String labId,
                                String style, String size){
        HBox hBox = new HBox();
        Label label = new Label(lab);
        label.setId(labId);
        btn.setStyle(style);
        btn.getStyleClass().add(size);
        btn.setOnAction(buttonHandler);
        hBox.getChildren().addAll(label, btn);
        hBox.setSpacing(30);
        hBox.setPadding(new Insets(15,15,15,15));
        return hBox;
    }

    /**
     * This method initializes a checkbox with a label beside it
     * @param lab the content the label will contain
     * @param labId the id for the label
     * @return an HBox containing the checkbox and the label
     */
    public HBox checkBoxWithLabel(String lab, String labId){
        HBox hBox = new HBox();
        CheckBox checkBox = new CheckBox();
        Label label = new Label(lab);
        label.setId(labId);
        hBox.getChildren().addAll(checkBox, label);
        hBox.setSpacing(20);
        hBox.setPadding(new Insets(15,15,15,15));
        return hBox;
    }

    /**
     * Creates the label and textField asking for Max Undos
     * @param lab the content the label will contain
     * @param labId the id for the label
     * @return an HBox containing the created Label and textField
     */
    public HBox textFieldWithLabel(String lab, String labId){
        HBox hBox = new HBox();
        TextField textField = new TextField();
        textField.setPrefWidth(90);
        Label label = new Label(lab);
        label.setId(labId);
        hBox.getChildren().addAll(textField, label);
        hBox.setSpacing(30);
        hBox.setPadding(new Insets(15,15,15,15));
        return hBox;
    }

    /**
     * Creates the two vertical buttons in the bottom right corner of this
     *      screen.
     * @param label1 the label that the first button will contain
     * @param label2 the label that the second button will contain
     * @param size the size that these buttons will be
     * @return the VBox containing these buttons.
     */
    public VBox twoVerticalButtons(String label1, String label2, String size){
        VBox vBox = new VBox();
        saveB = new Button(label1);
        saveB.getStyleClass().add("buttonStyle1");
        saveB.getStyleClass().add(size);
        saveB.setOnAction(buttonHandler);

        exitB = new Button(label2);
        exitB.getStyleClass().add("buttonStyle1");
        exitB.getStyleClass().add(size);
        exitB.setOnAction(buttonHandler);

        vBox.getChildren().addAll(saveB, exitB);
        vBox.setSpacing(30);
        vBox.setPadding(new Insets(15,15,15,15));
        return vBox;
    }

    /**
     * Sets the screen changer to be used with this screen
     * @param sch the screen changer to use
     */
    public void setScreenChangeHandler(ScreenChangeHandler sch) {
        this.sch = sch;
    }

    /**
     * An anonymous event handler to determine what buttons should do
     */
    EventHandler<ActionEvent> buttonHandler = new EventHandler<>() {

        @Override
        public void handle(ActionEvent actionEvent) {
            Object o = actionEvent.getSource();
            if (o == exitB) {
                if (lastScreen == 0) {
                    System.out.println("A");
                    sch.switchScreen(1);
                } else {
                    System.out.println("B");
                    sch.switchScreen(lastScreen);
                }
            } else if (o == blackSquare) {
                changeWhite = false;
                sch.switchScreen(5);
            } else if (o == whiteSquare){
                changeWhite = true;
                sch.switchScreen(5);
            } else if (o == saveB) {
                tileHandler.setTileColor(whiteTileColor, blackTileColor);
                if (lastScreen == 0) {
                    System.out.println("C");
                    sch.switchScreen(1);
                } else {
                    System.out.println("D");
                    sch.switchScreen(lastScreen);
                }
            }
        }
    };

    /**
     * Sets the color of the white Square to a new value
     * @param color the new color to set the white squares
     */
    @Override
    public void setColorWhite(String color) {
        whiteSquare.setStyle("-fx-background-color: #"+color);
        this.whiteTileColor = color;
    }

    /**
     * Sets the color of the black Square to a new value
     * @param color the new color to set the black squares
     */
    @Override
    public void setColorBlack(String color) {
        blackSquare.setStyle("-fx-background-color: #"+color);
        this.blackTileColor = color;
    }

    /**
     * Gets a value finding if the color to change is for white
     * @return true if the color to change is for white.
     */
    @Override
    public boolean getChangeWhite() {
        return this.changeWhite;
    }

    /**
     * Initializes the tile color handler for to send signals to
     * @param newTileHandler the new handler to change to
     */
    public void setTileChangeHandler(TileColorChangeHandler newTileHandler) {
        this.tileHandler = newTileHandler;
    }

    /**
     * A reference to the last screen that the user swapped to.
     * @param screenNum the last screen the application swapped to.
     */
    @Override
    public void lastScreenSelected(int screenNum) {
        setLastScene(screenNum);
    }
}
