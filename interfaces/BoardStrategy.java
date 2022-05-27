/**
 * An interface that models an implementation or strategy for drawing a 
 * board and provides a draw method used to draw the board.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
package interfaces;

/**
 * This interface models an implementation or strategy for drawing a board.
 * Allows for multiple implementations of this task, such as on the command
 * line or through a graphical interface.
 */
public interface BoardStrategy {
    /**
     * Draws or displays the board.
     * @param board The board to display or draw.
     */
    public void draw(BoardIF board);
}
