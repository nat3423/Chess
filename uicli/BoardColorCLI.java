/**
 * A class used to implement colors on a chess board and display them
 * to a user.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
package uicli;
import interfaces.*;
import enums.*;

/**
 * Models an implementation that uses color on the command line to display
 * a chess board.
 */
public class BoardColorCLI implements BoardStrategy {
    /** Standard Foreground Colors */
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001b[38;5;196m";
    public static final String GREY = "\u001B[38;5;231m";
    public static final String WHITE = "\u001B[37m";

    /** Standard Background Colors */
    public static final String BLACK_BACKGROUND = "\u001B[40m";
    public static final String GREY_BACKGROUND = "\u001B[48;5;248m";
    public static final String WHITE_BACKGROUND = "\u001B[47m";
    public static final String GREEN_DARK_BACKGROUND = "\u001b[48;5;34m";
    public static final String GREEN_LIGHT_BACKGROUND = "\u001b[48;5;40m";

    /** Code for Resetting Color Format */
    public static final String RESET = "\u001B[0m";

    /**
     * Draws or displays the board.
     * @param board The board to display or draw.
     */
    public void draw(BoardIF board) {
        String result = "";
        SquareIF[][] squares = board.getSquares();

        // iterating through every square
        for(int i = board.getHeight(); i >= 0; i--){
            for(int j = 0; j < board.getWidth(); j++){
                if(i > 0) {
                    // getting the formatted output for a specific square
                    result += colorOutput(squares[i-1][j]);
                } else {
                    // outputting the column labels
                    File file = File.searchColumn(j);
                    result += " " + file.getName() + " ";
                }
            }
            if(i > 0) {
                // outputting the row labels 
                Rank rank = Rank.searchRow(i-1);
                result += " " + rank.getName() + "\n";
            }
        }

        // printing the resulting string
        System.out.println(result);
    }

    /**
     * Helper method that formats a square for output.
     * @param square The square that will be formatted.
     * @return A string output with the correct color format.
     */
    private String colorOutput(SquareIF square) {
        String output = " ";
        PieceIF piece = square.getPiece();

        // checks if there is a piece
        if(piece != null) {
            if(piece.isBlack()) {
                output = BLACK + piece.toString();
            } else {
                output = RED + piece.toString();
            }
            
        }
        
        if(square.isBlack()) {
            if(!square.getHighLighted()){
                output = GREY_BACKGROUND + " " + output + " ";
            }else{
                output = GREEN_DARK_BACKGROUND + " " + output + " ";
                square.setHighlighted(false);
            }
        } else {
            if(!square.getHighLighted()){
                output = WHITE_BACKGROUND + " " + output + " ";
            }else{
                output = GREEN_LIGHT_BACKGROUND + " " + output + " ";
                square.setHighlighted(false);
            }
        }

        // formatted string + reset code
        return output + RESET;
    }
}
