package application.GameScreen;

import application.ScreenChangeHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * A class modeling the section on the sides of the game screen where the
 * player number. username, and 'captured:'
 * labels are.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class PlayerPane implements PlayerSidesIF {

    /**The main PlayerPane**/
    Pane root;

    /**A screen changer to allow for switching screens**/
    ScreenChangeHandler sch;

    /**The label to hold the player**/
    Label playerLabel, playerNameLabel, capturedLabel;

    /**This player's number **/
    int playerNum;

    /**The username of the player**/
    String username;

    /**Holds the captured pieces this player has for the current row**/
    HBox currCaptures;

    /**Holds the current row size**/
    int currRowSize = 0;

    /**
     * Constructor to create the PlayerPane
     * @param playerNum the number that this player is (1 or 2)
     * @param username the name of the player
     */
    public PlayerPane(int playerNum, String username){
        currCaptures = new HBox();
        this.root = new VBox();
        this.playerNum = playerNum;
        this.username = username;
        playerLabel = new Label("Player " + this.playerNum);
        playerNameLabel = new Label("  "+this.username);
        capturedLabel = new Label("Captured: ");
        playerLabel.getStyleClass().add("BoardText");
        playerNameLabel.getStyleClass().add("BoardText");
        capturedLabel.getStyleClass().add("BoardText");
        this.root.getStyleClass().add("GamePlayerPane");
        this.root.getChildren().addAll(playerLabel, playerNameLabel,
                capturedLabel, currCaptures);
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

    /**
     * For displaying the captured piece to the screen
     * @param piece the piece that was captured
     * @param color the color of the piece captured
     */
    @Override
    public void setCaptured(String piece, String color) {
        updateCaptures(piece, color);
    }

    /**
     * Updates the display to show the current captured pieces
     * @param piece the piece that was captured
     * @param color the color of the piece captured
     */
    private void updateCaptures(String piece, String color) {

        //Adds a new row to the captured pieces
        if(currRowSize == 3) {
            this.currCaptures = new HBox();
            this.root.getChildren().add(currCaptures);
            currRowSize = 0;
        }

        try {
            Image img = new Image(new FileInputStream("src/application/ChessPieces/"+color+piece+".png"));
            ImageView imv = new ImageView(img);
            imv.setFitHeight(32);
            imv.setPreserveRatio(true);
            Label label = new Label();
            label.setGraphic(imv);
            currCaptures.getChildren().add(label);
            currRowSize++;
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: Image not found");
            System.exit(1);
        }
    }

    /**
     * Resets the player's name
     * @param name the name to set the name with
     */
    public void setPlayerName(String name) {
        playerNameLabel.setText("  "+name);
        this.username = name;
    }
}
