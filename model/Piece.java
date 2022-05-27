/**
 * Models a piece which serves as an abstract parent class for specific
 * pieces and extends from the BlackAndWhite Class so all child classes 
 * can have a color.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
package model;
import interfaces.*;
import java.util.ArrayList;
import enums.*;

public abstract class Piece extends BlackAndWhite implements PieceIF {
    /** The board containing this piece */
    protected BoardIF board;
    /** The type of the piece */
    private ChessPieceType type;

    /**
     * Constructor for a Piece.
     * @param type The ChessPieceType that will be assigned to the type field.
     * @param color The color of the piece.
     * @param board The board containing the piece.
     */
    public Piece(ChessPieceType type, GameColor color, BoardIF board) {
        super(color);
        this.type = type;
        this.board = board;
    }

    /**
     * Shows every possible move from a specific position on the board.
     * @param pos The starting position of the piece.
     * @return an array of Position objects for every possible spot that
     * a piece can move to.
     */
    public Position[] showMoves(Position pos) {
        ArrayList<Position> possibleMoves = new ArrayList<Position>();
        
        // looping through all positions
        for (Rank r : Rank.values()) {
            for (File f : File.values()) {
                Position checkPos = new Position(r, f);

                if (validateMove(pos, checkPos)) {
                    possibleMoves.add(checkPos);
                }
            }
        }

        Position[] result = new Position[possibleMoves.size()];
        result = possibleMoves.toArray(result);
        
        return result;
    }

    /**
     * Gives access to the type field.
     * @return the type field.
     */
    public ChessPieceType getChessPieceType() {
        return this.type;
    }

    /**
     * Sets the chessPieceType field to the parameter passed in.
     * @param t The chessPieceType enum that will replace the local field.
     */
    public void setChessPieceType(ChessPieceType t) {
        this.type = t;
    }

    @Override
    /**
     * Returns the type as a String output.
     * @return The type enum as a String.
     */
    public String toString() {
        return this.type.toString();
    }
}
