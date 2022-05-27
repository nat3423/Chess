package application.GameScreen;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * A class that models a single chess tile button
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class ChessTile implements ChessTileIF{
    /**The button that this ChessTile is represented by**/
    Button button;

    /**Sends a signal to this class when a button is clicked**/
    TileClickedHandler tileHandler;

    /**The row and column of this Tile**/
    String row, col;

    /**The color that this tile is originally set to**/
    String originalColor;

    /**True if this tile is for the white section**/
    boolean isWhite;

    /**
     * Default constructor to initialize the button
     */
    public ChessTile(String row, String col) {
        this.row = row;
        this.col = col;
        button = new Button();
        button.setOnAction(buttonHandler);
    }

    /**
     * Gets the button for this chess tile
     * @return the button for this chess tile
     */
    public Button getB() {
        return button;
    }

    /**
     * Sets the style of the button for this chess tile
     * @param style the style of this chess tile
     */
    public void setBStyle(String style) {
        this.button.getStyleClass().add(style);
        if(style.equals("WhiteChessTile")) {
            originalColor = "f5deb3";
        }
        else {
            originalColor = "273746";
        }
    }

    /**
     * Sets the color of this chess tile's button
     * @param color the number representing the hex code for the color
     */
    public void setColor(String color) {
        this.originalColor = color;
        button.setStyle("-fx-background-color: #" + color);
    }

    /**
     * Adds an image to this tile
     * @param path the path of the image to find
     */
    @Override
    public void setImage(String path) {
        button.setGraphic(null);
    }

    /**
     * Sets the handler that will deal with when a tile is clicked
     * @param handler the handler that will deal with the tile being clicked
     */
    public void setTileClickedHandler(TileClickedHandler handler) {
        this.tileHandler = handler;
    }

    /**
     * An anonymous event handler to determine what buttons should do
     */
    EventHandler<ActionEvent> buttonHandler = new EventHandler<>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            Object o = actionEvent.getSource();
            if (o == button) {
                tileHandler.tileWasClicked(row, col);
            }
        }
    };

    /**
     * Highlights this button
     */
    public void highlight() {
        button.setStyle("-fx-background-color: #dbe300");
    }

    /**
     * unhighlights this button
     */
    public void unHighlight() {
        button.setStyle("-fx-background-color: #" + originalColor);
    }

    /**
     * Initializes if this tile is for the white squares
     * @param isWhite the variable to set if this square is white
     */
    public void setIsWhite(boolean isWhite) {
        this.isWhite = isWhite;
    }

    /**
     * Returns the value of this Tile's isWhite fields
     * @return true if this tile is for the white squares and false otherwise
     */
    public boolean getIsWhite() {
        return this.isWhite;
    }

}
