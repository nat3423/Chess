/**
 * Models the King piece class. Extends the abstract piece class
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

public class King extends Piece {
    /**
     * Constructor for a King.
     * @param type The ChessPieceType, it will be assigned King.
     * @param color The color of the piece.
     */
    public King(ChessPieceType type, GameColor color, BoardIF board) {
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

        if((rDiff <= 1 && cDiff <= 1) && !(rDiff == 0 && cDiff == 0)){
            result = true;

            PieceIF piece = board.getPiece(to.getRank(), to.getFile());

            if(piece != null){
                result = super.getColor() != piece.getColor();
            }
        }

        SquareIF[][] squares = this.board.getSquares();

        squares[from.getRank().getRow()][from.getFile().getColumn()].clear();

        PieceIF tempPiece =squares[to.getRank().getRow()][to.getFile().getColumn()].getPiece();

        if(result){
            squares[to.getRank().getRow()][to.getFile().getColumn()].setPiece(this);
        
            for(int i = 0; i < squares.length && result == true; i++){
                for(int j = 0; j < squares[i].length && result == true; j++){
                    if(squares[i][j].getPiece()!=null &&
                       squares[i][j].getPiece().getColor()!=this.getColor()&&
                       squares[i][j].getPiece().validateMove(squares[i][j].getPosition(), to)){
                           result = false;
                       }
                }
            }
            
            squares[to.getRank().getRow()][to.getFile().getColumn()].clear();
        }
        squares[to.getRank().getRow()][to.getFile().getColumn()].setPiece(tempPiece);

        squares[from.getRank().getRow()][from.getFile().getColumn()].setPiece(this);

        return result;
    }
}
