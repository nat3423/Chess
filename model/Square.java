/**
 * A square class used to respresent a single tile on the chess board, which
 * can either be black or white via extending the BlackAndWhite parent class.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */

package model;

import enums.GameColor;
import interfaces.PieceIF;
import interfaces.SquareIF;

/**
 * Models a square on a board for a game of chess.
 */
public class Square extends BlackAndWhite implements SquareIF {
    /** The piece on the current square */
    private PieceIF piece;
    /** The position of the current square */
    private Position position;
    /**boolean to represent if this square is highlighted */
    private boolean highlighted;
    
    /**
     * Constructor for a Square object.
     * @param color The color of the square.
     * @param position The position of the square.
     */
    public Square(GameColor color, Position position) {
        super(color);
        this.position = position;
        this.piece = null;
    }

    /**
     * Getter method for the position of the square.
     * @return The Position object held by the square.
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * Setter method for the position of the square.
     * @param position The Position object to set the square to.
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Getter for the piece on this square.
     * @return The PieceIF object currently on this square.
     */
    public PieceIF getPiece() {
        return this.piece;
    }

    /**
     * Setter for the piece on this square.
     * @param p The PieceIF object to set on this square.
     */
    public void setPiece(PieceIF piece) {
        this.piece = piece;
    }

    /**
     * Clears the piece held on this square.
     */
    public void clear() {
        this.piece = null;
    }

    @Override
    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    @Override
    public boolean getHighLighted() {
        return this.highlighted;
    }

    @Override
    /**
     * Returns the square as a String output.
     * @return The Square object as a String.
     */
    public String toString() {
        return this.position.toString();
    }
}
