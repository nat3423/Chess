package application.GameScreen;

/**
 * For handling what happens when a piece is captured
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public interface PieceCapturedHandlerIF {

    /**
     * For displaying the captured piece to the screen
     * @param whiteCaptured if white captured this piece
     * @param piece the piece that was captured
     * @param color the color of the piece captured
     */
    void setCaptured(boolean whiteCaptured, String piece, String color);

}
