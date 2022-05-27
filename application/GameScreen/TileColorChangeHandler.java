package application.GameScreen;

/**
 * A handler for changing tile colors
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public interface TileColorChangeHandler {

    /**
     * Sets the tile color to the new color provided
     * @param whiteColor the new color to set the white tile colors to
     * @param blackColor the color to set the black tiles to
     */
    void setTileColor(String whiteColor, String blackColor);

}
