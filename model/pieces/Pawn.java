/**
 * Models a pawn piece and extends from the parent class Piece.
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
import enums.*;
import interfaces.*;

/**
 * Models a pawn piece in a game of chess.
 */
public class Pawn extends Piece {
    /**
     * Constructor for a Pawn.
     * @param type The ChessPieceType, it will be assigned Queen.
     * @param color The color of the piece.
     */
    public Pawn(ChessPieceType type, GameColor color, BoardIF board) {
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
        boolean firstMove = false;
        boolean backwards = !super.isWhite();
        
        int rDiff = to.getRank().getRow() - from.getRank().getRow();
        int cDiff = to.getFile().getColumn() - from.getFile().getColumn();

        int rSign = rDiff > 0 ? 1 : -1;
        PieceIF piece;
        // check if the color is black or white and determine if its the pawn's
        // first move or not
        if (backwards) {
            firstMove = from.getRank().getRow() == 6;
            // case where the pawn moves down 2 spaces on its first move
            if(firstMove && cDiff == 0 && rDiff == -2){
                piece = board.getPiece(Rank.searchRow(from.getRank().getRow() + rSign),
                    from.getFile());
                // checking that the path is clear
                if(piece == null){
                    piece = board.getPiece(to.getRank(), to.getFile());
                    result = piece == null;
                }
            // case where the pawn is attempting to move diagnolly
            }else if(Math.abs(cDiff) == 1 && rDiff == -1){
                piece = board.getPiece(to.getRank(), to.getFile());
                if(piece != null){
                    // only valid if there is a piece to capture there
                    result = super.getColor() != piece.getColor();
                }
            // case where the pawn is attempting to move down 1 space
            }else if(cDiff == 0 && rDiff == -1){
                piece = board.getPiece(to.getRank(), to.getFile());
                result = piece == null;
            }
        } else {
            firstMove = from.getRank().getRow() == 1;
            // case where the pawn moves up 2 spaces on its first move
            if(firstMove && cDiff == 0 && rDiff == 2){
                piece = board.getPiece(Rank.searchRow(from.getRank().getRow() + rSign),
                    from.getFile());

                // checking that the path is clear
                if(piece == null){
                    piece = board.getPiece(to.getRank(), to.getFile());
                    result = piece == null;
                }
            // case where the pawn is attempting to move diagnolly
            }else if(Math.abs(cDiff) == 1 && rDiff == 1){
                piece = board.getPiece(to.getRank(), to.getFile());
                if(piece != null){
                    // only valid if there is a piece to capture there
                    result = super.getColor() != piece.getColor();
                }
            // case where the pawn is attempting to move up 1 space
            }else if(cDiff == 0 && rDiff == 1){
                piece = board.getPiece(to.getRank(), to.getFile());
                result = piece == null;
            }
        }

        return result;
    }
}
