/**
 * An enum used to model all the possible values a
 * chess piece could be, along with symbols to respresent
 * a piece on a board.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
package enums;

/**
 * This enum models the various chess piece types in a game of chess.
 * @author Nathan Jenkins: 10%
 * @author Melanney Orta:
 * @author Christen Kangas
 * @author Nate Welch:
 * @version 5/03/2021
 */
public enum ChessPieceType {
    KING('K', "King"),
    QUEEN('Q', "Queen"),
    ROOK('R', "Rook"),
    BISHOP('B', "Bishop"),
    KNIGHT('N', "Knight"),
    PAWN('P', "Pawn");

    /** The symbol representing the chess piece type */
    private final char symbol;
    /** The name of the chess piece type */
    private final String name;

    /**
     * Constructor for the different ChessPieceType enums.
     * @param symbol The symbol of the piece type.
     * @param name The name of the piece type. 
     */
    private ChessPieceType(char symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    /**
     * Returns the piece type as a String output.
     * @return The ChessPieceType as a String.
     */
    public String toString(){
        return this.symbol + "";
    }

    /**
     * Gets the symbol of the piece
     * @return the symbol for this piece
     */
    public String getSymbol() {
        return String.valueOf(this.symbol);
    }
}
