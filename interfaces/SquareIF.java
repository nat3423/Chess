/**
 * An interface that models an implementation of a square on a 
 * game board and provides basic methods used to get information
 * about a specific square. The BlackAndWhiteIF interface is extended
 * to allow the square color to be returned.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
package interfaces;

import model.Position;

/**
 * This interface models a square on a board for a game of chess.
 */
public interface SquareIF extends BlackAndWhiteIF {
    /**
     * Clears the piece held on this square.
     */
    public void clear();

    /**
     * Setter for the piece on this square.
     * @param p The PieceIF object to set on this square.
     */
    public void setPiece(PieceIF p);

    /**
     * Getter for the piece on this square.
     * @return The PieceIF object currently on this square.
     */
    public PieceIF getPiece();

    /**
     * sets the highlighted field
     * @param highlighted - A boolean value representing whether or not
     * this square is highlighted
     */
    public void setHighlighted(boolean highlighted);

    /**
     * gets whether or not this square is hightlighted
     * @return boolean value representing whether or not this
     * square is highlighted
     */
    public boolean getHighLighted();

    /**
     * gets the position of this square
     * @return the position of this square
     */
    public Position getPosition();
}
