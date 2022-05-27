/**
 * Models a rook piece and extends from the parent class Piece.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */

package model.pieces;
import model.*;
import enums.*;
import interfaces.*;

/**
 * Models a Rook piece in a game of chess.
 */
public class Rook extends Piece {
    /**
     * Constructor for a Rook.
     * @param type The ChessPieceType, it will be assigned Queen.
     * @param color The color of the piece.
     */
    public Rook(ChessPieceType type, GameColor color, BoardIF board) {
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
        // get the differences of the rows and columns
        int rDiff = to.getRank().getRow() - from.getRank().getRow();
        int cDiff = to.getFile().getColumn() - from.getFile().getColumn();
        // Ensure that only rows or only columns are traversed.
        if ((rDiff == 0 && cDiff != 0) || (cDiff == 0 && rDiff != 0)) {
            result = true;
            // get the signs of the differences to determine whether the path goes
            // left, right, up, and/or down
            int rSign = rDiff > 0 ? 1 : -1;
            int cSign = cDiff > 0 ? 1 : -1;
            int pathLen = Math.abs(rDiff) + Math.abs(cDiff);

            // looping through the path
            int i = 1;
            while(i <= pathLen && result){
                PieceIF piece;
                
                // horizontal
                if(rDiff == 0){
                    piece = board.getPiece(from.getRank(),
                        File.searchColumn(from.getFile().getColumn() + (i * cSign)));
                // vertical
                }else{
                    piece = board.getPiece(Rank.searchRow(from.getRank().getRow() + (i * rSign)),
                        from.getFile());
                }

                if(piece != null){
                    // if it is the actual square the piece is trying to move to,
                    // check if it is an enemy piece to capture it
                    if(i == pathLen){
                        result = super.getColor() != piece.getColor();
                    // otherwise the path is blocked and it's an invalid move
                    }else{
                        result = false;
                    }
                }
                i++;
            }
        }
        return result;
    }
    
}
