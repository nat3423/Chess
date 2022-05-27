/**
 * An interface that models an implementation of a board piece and extends the
 * BlackAndWhiteIF interface so the colors of all pieces can be displayed.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
package interfaces;
import enums.ChessPieceType;
import model.Position;

/**
 * This interface models a piece in a game of chess.
 */
public interface PieceIF extends BlackAndWhiteIF {
    /**
     * Getter for the type of the chess piece.
     * @return The ChessPieceType enum of the object.
     */
    public ChessPieceType getChessPieceType();

    /**
     * Setter for the type of the chess piece.
     * @param t The type to set the chess piece to.
     */
    public void setChessPieceType(ChessPieceType t);

    /**
     * Validates the move from the given from and to positions.
     * @param from The starting position of the piece.
     * @param to The ending position of the piece.
     * @return true if the move is valid, false otherwise
     */
    public boolean validateMove(Position from, Position to);

    /**
     * Shows every possible position from a specific position on the board.
     * @param pos The starting position of the piece.
     * @return an array of Position objects for every possible spot that
     * a piece can move to.
     */
    public Position[] showMoves(Position pos);
}
