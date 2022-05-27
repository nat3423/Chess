package application.GameScreen;

import javafx.scene.control.Button;

/**
 * An interface to be used when making chess tiles
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public interface ChessTileIF {

    /**
     * Gets the button for this chess tile
     * @return the button for this chess tile
     */
    Button getB();

    /**
     * Sets the style of the button for this chess tile
     * @param style the style of this chess tile
     */
    void setBStyle(String style);

    /**
     * Sets the color of this chess tile's button
     * @param color the number representing the hex code for the color
     */
    void setColor(String color);

    /**
     * Adds an image to this tile
     * @param path the path of the image to find
     */
    void setImage(String path);

}
