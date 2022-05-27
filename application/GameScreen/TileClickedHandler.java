package application.GameScreen;

/**
 * Handles when a tile is clicked
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public interface TileClickedHandler {

    /**
     * What the game will do when a tile is clicked
     * @param row the row of the tile clicked
     * @param col the column of the tile that was clicked
     */
    void tileWasClicked(String row, String col);
}
