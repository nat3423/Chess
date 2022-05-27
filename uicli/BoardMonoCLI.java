/**
 * A class used to implement a black and white chess board and display it
 * to the user. 
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
 * Models an implementation that uses black and white on the
 * command line to display a chess board.
 */
public class BoardMonoCLI implements BoardStrategy {
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
                    // getting the formatted output
                    result += monoOutput(squares[i-1][j]);
                } else {
                    // printing the column labels
                    File file = File.searchColumn(j);
                    result += " " + file.getName() + " ";
                }
            }
            if(i > 0) {
                // printing the row labels
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
     * @return A string output with the correct B/W format.
     */
    private String monoOutput(SquareIF square) {
        String output = " ";
        PieceIF piece = square.getPiece();

        // checking if there is a piece
        if(piece != null) {
            if(piece.isBlack()){
                output = piece.toString();
            } else {
                output = piece.toString().toLowerCase();
            }
            
        }

        if(square.getHighLighted()){
            output+="*";
            square.setHighlighted(false);
        }
        
        if(square.isBlack()) {
            output = "[" + output + "]";
        } else {
            output = ":" + output + ":";
        }

        // returning the formatted output
        return output;
    }
}
