package interfaces;

import enums.File;
import enums.GameColor;
import enums.Rank;
import model.Position;


/**
 * An interface for the ChessGUI class to communicate with.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public interface ChessIF {



//    /**
//     * Loops the main menu, calling other methods such as newGame()
//     * when the option to start the game is selected. Handles creating new
//     * players and adding them to the database as well as changing user
//     * information
//     */
//    void mainMenu();

//    /**
//     * A valid option has been entered carry it out.
//     * @param option The menu option selected.
//     */
//    void doMenuOptions(int option);

//    /**
//     * Conduct player 1 log in
//     */
//    void player1LogIn();

//    /**
//     * Conduct player 2 log in.
//     */
//    void player2LogIn();

    /**
     * Two players play as as guests.
     */
    void guestPlay();

    /**
     * Create a user new account
     */
    void createAccount();

    /**
     * Creates the options for a color
     */
    void colorOptions();

    /**
     * Initializes the board to a new board and sets that boards
     * type, be it 'Color' or 'Mono'. Then it starts the gameplay.
     */
    void newGame();

    /**
     * Given the column and row for a position to move from and to, this method
     * moves a piece on the board if the move is valid and there is nothing in the way.
     *
     * @param fromF The Column of the piece to move
     * @param fromR The Row of the piece to move
     * @param toF The Column of where to move it
     * @param toR The Row of where to move it
     */
    void move(File fromF, Rank fromR, File toF, Rank toR);

    /**
     * Handles the core gameplay loop by looping through until the player does
     * not want to continue, handling who's turn it is and asking them what piece they
     * want to move where. This method also calls the boards draw when needed to show the
     * players.
     */
    void gameplay();

    /**
     * The player chooses the from position, input is repeated unil the user gets it righr or enters q.
     * @param playerTurn The player who's turn it is.
     * @return If the player should keep playing.
     */
    boolean playerChooseFrom(PlayerIF playerTurn );

    /**
     * Player chooses their To location.
     * @param possibleMoves The possible moves a player has with the selected piece.
     * @param posInput  The UI for taking input of the position.
     */
    void playerChooseTo(String possibleMoves, UI_Interface<String> posInput);

    /**
     * Finds if a given move is valid
     * @param from the position to move from
     * @param squares the board to check with
     * @param playerTurn the current player's turn
     * @return true if the move is invalid
     */
    boolean isMoveInvalid(Position from, SquareIF[][] squares, PlayerIF playerTurn);

    /**
     * Returns a list of the possible moves for a piece
     * @param from the location to get the possible moves from
     * @return a string showing the list of moves
     */
    String getPossibleMoves(Position from);

    /**
     * Checks if any conditions have been met that could
     * change the state of the game.
     * @return true if the game keeps going
     */
    boolean checkStatus(boolean whiteTurn);

    /**
     * Checks if pawns need to be upgraded to a queen by determining if a pawn
     * is at the enemy's end of the board
     */
    void checkPawns();

    /**
     * Checks if the game is at a draw
     * @return true if the game is at a draw, false otherwise
     */
    boolean isDraw(boolean whiteTurn);

    /**
     * Checks if the Fifty Move Rule condition has been met.
     * The condition is that 50 moves have been made with no captures
     * or no pawns having been moved.
     * @return true if this has been met, false otherwise
     */
    boolean isFiftyMoveRule();

    /**
     * Checks if the current player is not in check but cannot
     * make any moves without putting themselves into check.
     * @return true if this has been met, false otherwise
     */
    boolean isStalemate(boolean whiteTurn);

    /**
     * Checks if the same board presence has occurred three
     * times in a row.
     * @return true if this has been met, false otherwise
     */
    boolean isThreeFold();

    /**
     * Helper method that adds the current board state
     * to boardStates to keep track if there is a
     * repeating scenario for a three fold stalemate.
     */
    void updateStates();

    /**
     * Helper method for getting the position of the King.
     * @param color - The color of the king to be grabbed.
     * @return the position of the King piece.
     */
    Position getKingPosition(GameColor color);

    /**
     * Sets the check and checkmate fields to their appropriate values based
     * on the enemy king's current status.
     * @param whiteTurn - A boolean evaluating to true if it is currently
     * white's turn.
     */
    void setCheckAndCheckmate(boolean whiteTurn);

    /**
     * Determines if the passed in king is currently in check
     * @param king - The position of the passed in color's king
     * @param color - The color of the king to be checked for check
     * @return - true if the king of the specified color is in check
     */
    boolean isCheck(Position king, GameColor color);

    /**
     * Checks if the specified color's king is in checkmate by determining if every
     * possible move results in check
     * @param king - The position of the passed in color's king
     * @param color - The color of the king to be checked for check
     * @return true if every possible scenario results in a check
     */
    boolean isCheckmate(Position king, GameColor color);

    /**
     * Makes a move from the 'from' position to the 'to' position,
     * determines if that move results in check or not, and then undoes it
     * @param from - The initial position
     * @param to - The position the piece is being moved to
     * @param king - The passed in color's king to be checked
     * @param color - The color of the king to be checked for check
     * @return true if the move still results in check
     */
    boolean checkMoveThenUndo(Position from, Position to, Position king, GameColor color);

    /**
     * Determines whether a string given represents a valid board position
     * and if so turns it into a Position object.
     * @param pos The string to check
     * @return a Position object if the position is valid or null if not
     */
    Position parsePosition(String pos);

    /**
     * Returns the chess's board
     * @return the board for the chess game
     */
    BoardIF getBoard();

    /**
     * Gets the current position to
     * @return this chess's current position to
     */
    Position getCurrentTo();

    /**
     * Gets the current position from
     * @return this chess's current position from
     */
    Position getCurrentFrom();

    /**
     * Sets a new value to this chess field's currentFrom
     * @param newFrom the new position to set it with
     */
    void setCurrentFrom(Position newFrom);

    /**
     * Sets a new value to this chess field's currentTo
     * @param newTo the new position to set it with
     */
    void setCurrentTo(Position newTo);

    /**
     * Gets the black player
     * @return the player playing the black pieces
     */
    PlayerIF getPlayerBlack();

    /**
     * Gets the white player
     * @return the player playing the white pieces
     */
    PlayerIF getPlayerWhite();

    /**
     * Finds if a player is in check
     * @return true if in check, false otherwise
     */
    boolean getInCheck();

}
