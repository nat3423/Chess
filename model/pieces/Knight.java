/**
 * Models a knight piece and extends from the parent class Piece.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
package model.pieces;
import model.*;
import enums.ChessPieceType;
import enums.GameColor;
import interfaces.*;

/**
 * Models a knight piece in a game of chess.
 */
public class Knight extends Piece {
    /**
     * Constructor for a Knight.
     * @param type The ChessPieceType, it will be assigned Queen.
     * @param color The color of the piece.
     */
    public Knight(ChessPieceType type, GameColor color, BoardIF board) {
        super(type, color, board);
    }

    /**
     * Validates the move from the given from and to positions.
     * @param from The starting position of the piece.
     * @param to The ending position of the piece.
     * @return true if the move is valid, false otherwise
     */
    public boolean validateMove(Position from, Position to) {
        boolean result = false;
        int rDiff = Math.abs(to.getRank().getRow() - from.getRank().getRow());
        int cDiff = Math.abs(to.getFile().getColumn() - from.getFile().getColumn());
        //return a position only if an L shape it made
        if ((rDiff == 1 && cDiff == 2) || (rDiff == 2 && cDiff == 1)){
            result = true;

            PieceIF piece = board.getPiece(to.getRank(), to.getFile());

            // if there is a piece there, check if it is capturable
            if(piece != null){
                result = super.getColor() != piece.getColor();
            }
        }
        return result;
    }
}
