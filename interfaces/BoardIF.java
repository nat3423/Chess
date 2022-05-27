/**
 * An interface that models an implementation of a board and provides basic
 * methods that every class using a board would require.
 * 
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
package interfaces;
import enums.*;

/**
 * This interface models a Board for a game of chess.
 */
public interface BoardIF {
    /**
     * Initializes the squares of the chess board.
     */
    public void init_board();

    /**
     * Sets up the board to be ready for use.
     */
    public void setup();

    /**
     * Draws or displays the board for gameplay purposes.
     */
    public void draw();

    /**
     * Getter method for the squares of the board.
     * @return The SquareIF array holding the squares on the board.
     */
    public SquareIF[][] getSquares();

    /**
     * Sets the strategy or implementation for drawing the board.
     * @param d The specific implementation that will be set.
     */
    public void setDrawStrategy(BoardStrategy d);

    /**
     * Getter method for the width of the board.
     * @return The width of the board.
     */
    public int getWidth();

    /**
     * Getter method for the height of the board.
     * @return The height of the board.
     */
    public int getHeight();

    /**
     * Gets the piece at the specified rank and file on the board.
     * @param r The rank at which to get the piece from.
     * @param f The file at which to get the piece from.
     * @return The PieceIF object at the specified location on the board.
     */
    public PieceIF getPiece(Rank r, File f);

    /**
     * Gets the piece at the specified column and row on the board.
     * @param col The column of the piece to get.
     * @param row The row of the piece to get.
     * @return The PieceIF object at the specified location on the board.
     */
    public PieceIF getPiece(int row, char col);

    /**
     * Highlights a square at a given rank and file on the board
     * @param r The rank of the square to hightlight
     * @param f The file fo the square to highlight
     */
    public void highlightSquare(Rank r, File f);
}
