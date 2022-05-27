package controller;

import java.util.ArrayList;
import java.util.HashMap;
import enums.*;
import interfaces.*;
import uicli.*;
import model.*;
import model.pieces.*;
import ui.UI_FactoryIF;

/**
 * Models a game of chess with players that hold identifying
 * information along with game statistics and allows these players
 * to play against one another.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 *
 * BUGS Outstanding::
 * Not reliably figuring out checkmate, but it does announce a check.
 */
public class ChessGUI implements ChessIF{
    /** Holds the board object  which contains squares and pieces in those
     * squares
     */
    private BoardIF board;

    /** A reference to an object of type menu interface that calls upon
     * methods found in menu interface to display options and get input
     */
    // private MenuIF menu;

    /** A HashMap database to hold all the player objects with their
     * userID as the key
     * */
    private HashMap<String, PlayerIF> playerDataBase;

    /** A HashMap to track the board states to determine if
     * there is a three fold repetition
     */
    private HashMap<String, Integer> boardStates;

    /** An ArrayList of String options to be fed into the main menu*/
    private ArrayList<String> options;

    /** A player object to hold the current player of the black pieces*/
    private PlayerIF playerBlack;

    /** A player object to hold the current player of the white pieces*/
    private PlayerIF playerWhite;

    /** A string value representing the type of board, be it 'Color'
     * or 'Mono'
     * */
    private String boardColor;

    // For fifty move rule
    private int lastCaptureCnt = 0;
    private boolean check;
    private boolean checkmate;

    /**Entered by each player on their turn**/
    private Position currentTo;

    /**Entered by each player on their turn**/
    private Position currentFrom;

    /**Player requested to quit**/
    private boolean playerRequestedQuit = false;

    /**The factory for constructing UI implementations**/
    private UI_FactoryIF ui_factory;

    /**for displaying messages to the user**/
    private UI_Interface<Boolean> cli_message;

    /**
     * A constructor which sets up the menu field, initialized the player
     * database, sets the board color, and sets up the option list
     * @param uiFactory uiFactory Inject the UI factory responsible for building UI components CLI or GUI or other.
     */
    public ChessGUI(UI_FactoryIF uiFactory) {

        this.ui_factory = uiFactory;

        cli_message = this.ui_factory.buildMessageDisplay();//Consteuct the message UI;

        //  this.menu = new MenuCLI();
        this.playerDataBase = new HashMap<String,PlayerIF>();
        this.boardStates = new HashMap<String, Integer>();

        this.playerDataBase.put("Guest Player1", new Player("Guest", "One", "Guest Player1", ""));
        this.playerDataBase.put("Guest Player2", new Player("Guest", "Two", "Guest Player2", ""));

        this.boardColor = "Color";

        // adding the options for the main menu
        this.options = new ArrayList<String>();
        this.options.add("Log In and Play New Game");
        this.options.add("Play as Guests");
        this.options.add("Create a New Account");
        this.options.add("Change UserID");
        this.options.add("Toggle Board Color");
        this.options.add("Quit");
    }

    /**
     * A constructor which sets up the menu field, initialized the player
     * database, sets the board color, and sets up the option list
     */
    public ChessGUI() {
        this.playerDataBase = new HashMap<String,PlayerIF>();
        this.boardStates = new HashMap<String, Integer>();
        this.playerDataBase.put("Guest Player1", new Player("Player", "One", "Guest Player1", ""));
        this.playerDataBase.put("Guest Player2", new Player("Player", "Two", "Guest Player2", ""));
        this.boardColor = "Color";
    }

    /**
     * Two players play as as guests.
     */
    public void guestPlay() {
        this.playerWhite = this.playerDataBase.get("Guest Player1");
        this.playerWhite.resetStats();
        this.playerBlack = this.playerDataBase.get("Guest Player2");
        this.playerBlack.resetStats();
        this.newGame();
    }

    /**
     * Create a user new account
     */
    public void createAccount() {
        System.out.println("CREATE ACCOUNT=========================");
        UI_Interface<PlayerIF> createAccount = ui_factory.buildCreateAccount();
        //Observe when the player has interacted with the UI
        createAccount.setFinishedObserver(new UIFinishedObserverIF<PlayerIF>() {
            /**
             * Once the user has finished interacting with the UI
             * @param newPlayer The new player to check for input
             * @param sourceUI The source UI that invoked this method
             */
            @Override
            public void inputFinished(PlayerIF newPlayer,UI_Interface<PlayerIF> sourceUI) {

                //Chess.this refers to the outer classes this as this is technically
                //an inner class.
                ChessGUI.this.playerDataBase.put(newPlayer.getID(), newPlayer);
            }

        });
        createAccount.showUI();
    }//end createAccount


    /**
     * Change the user ID
     */
    public void changeUserID() {

        UI_Interface<Boolean> changeID = ui_factory.buildChangeUserID(playerDataBase);

        changeID.setFinishedObserver(new UIFinishedObserverIF<Boolean>() {

            /**
             * Once the user has finished interacting with the UI
             * @param result The value decided by the user
             * @param sourceUI The source UI that invoked this method
             */
            @Override
            public void inputFinished(Boolean result,UI_Interface<Boolean> sourceUI) {
                //if true user ID changed successfully.
            }

        });
        changeID.showUI();

    }//end changeUserID


    public void colorOptions() {

        UI_Interface<Boolean> colorOptions = ui_factory.buildColorOptions();
        //Observer when the user has interacted with the UI
        colorOptions.setFinishedObserver(new UIFinishedObserverIF<Boolean>() {

            /**
             * Once the user has finished interacting with the UI
             * @param result The value decided by the user
             * @param sourceUI The source UI that invoked this method
             */
            @Override
            public void inputFinished(Boolean result,UI_Interface<Boolean> sourceUI) {
                if (result)
                    ChessGUI.this.boardColor ="Color";
                else
                    ChessGUI.this.boardColor = "Mono";
            }

        });
        colorOptions.showUI();

    }//end colorOptions

    /**
     * Initializes the board to a new board and sets that boards
     * type, be it 'Color' or 'Mono'. Then it starts the gameplay.
     */
    public void newGame() {
        // creating the board for a new game
        this.board = new Board();

        this.playerWhite = this.playerDataBase.get("Guest Player1");
        this.playerWhite.resetStats();
        this.playerBlack = this.playerDataBase.get("Guest Player2");
        this.playerBlack.resetStats();

        this.playerWhite.setColor(GameColor.WHITE);
        this.playerBlack.setColor(GameColor.BLACK);

        // setting the draw strategy depending on the boardColor field
        if(boardColor.equals("Color")){
            this.board.setDrawStrategy(new BoardColorCLI());
        }else{
            this.board.setDrawStrategy(new BoardMonoCLI());
        }
    }

    /**
     * Given the column and row for a position to move from and to, this method
     * moves a piece on the board if the move is valid and there is nothing in the way.
     *
     * @param fromF The Column of the piece to move
     * @param fromR The Row of the piece to move
     * @param toF The Column of where to move it
     * @param toR The Row of where to move it
     */
    public void move(File fromF, Rank fromR, File toF, Rank toR) {
        // getting the intial piece
        PieceIF initial = this.board.getPiece(fromR, fromF);
        SquareIF[][] squares = this.board.getSquares();

        // only running if the move was valid
        if (initial.validateMove(new Position(fromR, fromF), new Position(toR, toF))) {
            SquareIF fromSquare = squares[fromR.getRow()][fromF.getColumn()];
            SquareIF toSquare = squares[toR.getRow()][toF.getColumn()];

            // Fifty move count increment
            if(toSquare.getPiece() != null ||
                    initial.getChessPieceType() == ChessPieceType.PAWN){
                this.lastCaptureCnt = 0;
                // also clears boardStates because we can never return
                // to a state equal to the previous ones
                this.boardStates.clear();
            } else {
                this.lastCaptureCnt++;
            }

            toSquare.setPiece(fromSquare.getPiece());
            fromSquare.clear();
        }else{
            cli_message.setMessage("Invalid Move!");
            cli_message.showUI();
        }
    }

    /**
     * Handles the core gameplay loop by looping through until the player does
     * not want to continue, handling who's turn it is and asking them what piece they
     * want to move where. This method also calls the boards draw when needed to show the
     * players.
     */
    public void gameplay(){

        boolean keepPlaying = true;
        boolean whiteTurn = true;

        PlayerIF playerTurn;


        // loop until the user does not want to play anymore
        // (will change to loop until checkmate state is hit in the future)
        while(this.currentFrom == null && currentTo == null && keepPlaying){

            // setting the current player depending on whose turn it is
            if(whiteTurn){
                playerTurn = this.playerWhite;
            }else{
                playerTurn = this.playerBlack;
            }

            //display the board
            this.board.draw();

            UI_Interface<Boolean> playerDisplay;
            playerDisplay = ui_factory.buildDisplayPlayer(playerTurn, this.boardColor,"'s Turn");
            playerDisplay.showUI();


            keepPlaying = playerChooseFrom(playerTurn);
            //keepPlaying = !this.playerRequestedQuit;

            /////////////////////////
            //Make the move
            /////////////////////////
            if (keepPlaying && this.currentFrom != null && currentTo != null){
                // moving the actual piece
                this.move(this.currentFrom.getFile(), this.currentFrom.getRank(), this.currentTo.getFile(), this.currentTo.getRank());
                updateStates();
                keepPlaying = checkStatus(whiteTurn);

                //Reset for next go
                this.currentFrom = null;
                this.currentTo = null;
            }//end if

            // changing who's turn it is
            whiteTurn = !whiteTurn;
        }//end while keep playing

    }//end gamePlay


    /**
     * The player chooses the from position, input is repeated unil the user gets it righr or enters q.
     * @param playerTurn The player who's turn it is.
     * @return If the player should keep playing.
     */
    public boolean playerChooseFrom(PlayerIF playerTurn ) {

        //fromPosString = this.menu.promptInput("Enter from position (or q to quit game)").toLowerCase();///
        UI_Interface<String> posInput = ui_factory.buildPositionInput("Enter from position (or q to quit game)");

        //Listen for response from user
        posInput.setFinishedObserver(new UIFinishedObserverIF<String>() {
            /**
             * The user has responded
             * @param result the value entered bythe user.
             * @param sourceUI A reference to the source UI
             */
            @Override
            public void inputFinished(String result,UI_Interface<String>sourceUI) {

                String fromPosString = result;
                SquareIF[][] squares = ChessGUI.this.board.getSquares();//Squares from board

                /////////////////////////////////////
                //IF User wants to quit
                ///////////////////////////////////
                if(fromPosString.equals("q")){
                    playerRequestedQuit = true;

                }//end if
                else{
                    Position from = ChessGUI.this.parsePosition(fromPosString);
                    /////////////////////////////////
                    //If move is invalid
                    ////////////////////////////////
                    if( isMoveInvalid(from,squares,playerTurn)) {
                        sourceUI.displayError("Invalid Position");
                    }//end if
                    ///////////////////////////////////
                    //Move is valid check possible moves
                    //////////////////////////////////
                    else {
                        ChessGUI.this.currentFrom = from;//GLOBALIZE
                        String possibleMoves = getPossibleMoves(from);
                        //If no possible moves error and repromt
                        if(possibleMoves.equals("")) {
                            sourceUI.displayError("No possible moves!");
                        }
                        /////////////////////////////////////
                        //Moves are possible so get to postition
                        /////////////////////////////////////
                        else {
                            playerChooseTo(possibleMoves, sourceUI);
                        }//end else
                    }//end else
                }//end if
            }
        });
        posInput.showUI();

        return ! this.playerRequestedQuit;
    }//end playerTurn

    /**
     * Player chooses their To location.
     * @param possibleMoves The possible moves a player has with the selected piece.
     * @param posInput  The UI for taking input of the position.
     */
    public void playerChooseTo(String possibleMoves, UI_Interface<String> posInput) {

        posInput.setMessage("All Possible Moves:\n" + possibleMoves);

        //The UI finished observer
        posInput.setPrompt("Enter to position (or q to choose another position)");
        //Listen for the user's response.
        posInput.setFinishedObserver(new UIFinishedObserverIF<String>() {

            /**User has entered the to location
             * @param toPosString The to location or q to choose another position
             */
            @Override
            public void inputFinished(String toPosString,UI_Interface<String>sourceUI) {

                if(toPosString.equals("q")){
                    //because these are null aftet thr players turn,
                    //they will be repromted
                    ChessGUI.this.currentFrom = null;
                    ChessGUI.this.currentTo = null;
                }else {
                    Position to = ChessGUI.this.parsePosition(toPosString);

                    //Input invalid show error and reprompt
                    if(to == null) {
                        sourceUI.displayError("Invalid Position");
                        sourceUI.showUI();
                    }else {
                        ChessGUI.this.currentTo = to;//GLOBALIZE
                    }
                }
            }
        });
        posInput.showUI();

    }


    public boolean isMoveInvalid(Position from, SquareIF[][] squares,PlayerIF playerTurn) {
        return from == null ||
                this.board.getPiece(from.getRank(), from.getFile())==null ||
                !this.board.getPiece(from.getRank(), from.getFile()).getColor().equals(playerTurn.getColor())
                || squares[from.getRank().getRow()][from.getFile().getColumn()].getPiece() == null;
    }


    public  String getPossibleMoves(Position from) {
        //showMoves from position
        Position[] posMoves = this.board.getPiece(from.getRank(), from.getFile()).showMoves(from);
        String possibleMoves = "";//to as to show possible moves.

        this.board.highlightSquare(from.getRank(), from.getFile());

        if(posMoves.length > 0){
            // creating a string of all possible moves
            for(Position pos : posMoves) {
                this.board.highlightSquare(pos.getRank(), pos.getFile());
                possibleMoves += pos.toString() + "  ";
            }

            //display the board
            this.board.draw();

            // display possible moves
            cli_message.setMessage("All Possible Moves:\n" + possibleMoves);
            cli_message.showUI();
            // this.menu.displayMessage("All Possible Moves:\n" + possibleMoves);

        }else{
            cli_message.setMessage("No Possible Moves!");
            cli_message.showUI();
            // this.menu.displayMessage("No Possible Moves!");
        }

        return possibleMoves;
    }

    /**
     * Checks if any conditions have been met that could
     * change the state of the game.
     * @return true if the game keeps going
     */
    public boolean checkStatus(boolean whiteTurn) {
        if(isDraw(whiteTurn)){
            this.playerBlack.addDraws(1);
            this.playerWhite.addDraws(1);

            this.board.draw();

            cli_message.setMessage("DRAW!");
            cli_message.showUI();
            //this.menu.displayMessage("DRAW!!");
            return false;
        }
        checkPawns();
        setCheckAndCheckmate(whiteTurn);
        // testing
        if(this.checkmate){

            this.board.draw();

            cli_message.setMessage("CHECKMATE!");
            cli_message.showUI();
            // this.menu.displayMessage("CHECKMATE!!");
            if(whiteTurn){
                this.playerWhite.addWins(1);
                this.playerBlack.addLosses(1);

                ui_factory.buildDisplayPlayer(this.playerWhite, this.boardColor, " WON!!!").showUI();
                // this.menu.displayPlayer(this.playerWhite, this.boardColor, " WON!!!");
            }else{
                this.playerBlack.addWins(1);
                this.playerWhite.addLosses(1);
                ui_factory.buildDisplayPlayer(this.playerBlack, this.boardColor, " WON!!!").showUI();
                //this.menu.displayPlayer(this.playerBlack, this.boardColor, " WON!!!");
            }
            return false;
        }
        if(this.check) {
            cli_message.setMessage("CHECK!");
            cli_message.showUI();
            //  this.menu.displayMessage("CHECK!");
        }

        return true;
    }

    /**
     * Checks if pawns need to be upgraded to a queen by determining if a pawn
     * is at the enemy's end of the board
     */
    public void checkPawns() {
        SquareIF square;
        ChessPieceType pawn = ChessPieceType.valueOf("PAWN");

        for (File f: File.values()) {
            //Check if white pawn becomes queen
            square = board.getSquares()[7][f.getColumn()];
            if (square.getPiece() != null && square.getPiece().getChessPieceType().equals(pawn)
                    && square.getPiece().getColor().equals(GameColor.WHITE))
                square.setPiece(new Queen(ChessPieceType.QUEEN, GameColor.WHITE, this.board));
            //Check if black pawn becomes queen
            square = board.getSquares()[1][f.getColumn()];
            if (square.getPiece() != null && square.getPiece().getChessPieceType().equals(pawn)
                    && !square.getPiece().getColor().equals(GameColor.WHITE))
                square.setPiece(new Queen(ChessPieceType.QUEEN, GameColor.WHITE, this.board));
        }
    }

    /**
     * Checks if the game is at a draw
     * @return true if the game is at a draw, false otherwise
     */
    public boolean isDraw(boolean whiteTurn) {
        return isFiftyMoveRule() || isStalemate(whiteTurn) || isThreeFold();
    }

    /**
     * Checks if the Fifty Move Rule condition has been met.
     * The condition is that 50 moves have been made with no captures
     * or no pawns having been moved.
     * @return true if this has been met, false otherwise
     */
    public boolean isFiftyMoveRule(){
        return this.lastCaptureCnt >= 50;
    }

    /**
     * Checks if the current player is not in check but cannot
     * make any moves without putting themselves into check.
     * @return true if this has been met, false otherwise
     */
    public boolean isStalemate(boolean whiteTurn){
        Position kingPos;
        boolean result = false;
        GameColor color;

        if(whiteTurn){
            color = GameColor.BLACK;
        }else{
            color = GameColor.WHITE;
        }
        kingPos = getKingPosition(color);
        result = !isCheck(kingPos, color) && isCheckmate(kingPos, color);

        return result;
    }

    /**
     * Checks if the same board presence has occurred three
     * times in a row.
     * @return true if this has been met, false otherwise
     */
    public boolean isThreeFold(){
        boolean result = false;
        for(Integer count : this.boardStates.values()){
            if(count >= 3){
                result = true;
            }
        }
        return result;
    }

    /**
     * Helper method that adds the current board state
     * to boardStates to keep track if there is a
     * repeating scenario for a three fold stalemate.
     */
    public void updateStates(){
        String boardStr = this.board.toString();

        if(boardStates.containsKey(boardStr)){
            boardStates.put(boardStr, boardStates.get(boardStr) + 1);
        }else{
            boardStates.put(boardStr, 1);
        }
    }

    /**
     * Helper method for getting the position of the King.
     * @param color - The color of the king to be grabbed.
     * @return the position of the King piece.
     */
    public Position getKingPosition(GameColor color){
        Position result = null;
        PieceIF piece;
        for (Rank r : Rank.values()) {
            for (File f : File.values()) {
                piece = this.board.getPiece(r, f);
                if (piece != null && piece.getColor() == color
                        && piece.getChessPieceType() == ChessPieceType.KING)
                    result = new Position(r, f);
            }
        }

        return result;
    }

    /**
     * Sets the check and checkmate fields to their appropriate values based
     * on the enemy king's current status.
     * @param whiteTurn - A boolean evaluating to true if it is currently
     * white's turn.
     */
    public void setCheckAndCheckmate(boolean whiteTurn) {
        //if its whiteTurn, check if black is in check or checkmate
        String whiteorblack = whiteTurn ? "BLACK" : "WHITE";
        GameColor color = GameColor.valueOf(whiteorblack);
        Position king = getKingPosition(color);
        this.check = false;

        //Check if check, and if so, check if checkmate
        if (isCheck(king, color)) {
            this.check = true;
            this.checkmate = isCheckmate(king, color);
        }
    }

    /**
     * Determines if the passed in king is currently in check
     * @param king - The position of the passed in color's king
     * @param color - The color of the king to be checked for check
     * @return - true if the king of the specified color is in check
     */
    public boolean isCheck(Position king, GameColor color) {
        PieceIF piece;
        for (Rank r : Rank.values()) {
            for (File f : File.values()) {
                piece = board.getPiece(r, f);
                //check if current turns piece has a move that captures the enemy king
                if (piece != null && !piece.getColor().equals(color)) {
                    for (Position pos : piece.showMoves(new Position(r, f))) {
                        if (pos.equals(king)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if the specified color's king is in checkmate by determining if every
     * possible move results in check
     * @param king - The position of the passed in color's king
     * @param color - The color of the king to be checked for check
     * @return true if every possible scenario results in a check
     */
    public boolean isCheckmate(Position king, GameColor color) {
        PieceIF piece;
        for (Rank r : Rank.values()) {
            for (File f : File.values()) {
                piece = board.getPiece(r, f);
                //check if next turn can do something
                if (piece != null && piece.getColor().equals(color)) {
                    for (Position pos : piece.showMoves(new Position(r, f))) {
                        if (checkMoveThenUndo(new Position(r, f), pos, king, color) == false) {
                            return false;
                        }

                    }
                }
            }
        }
        return true;
    }

    /**
     * Makes a move from the 'from' position to the 'to' position,
     * determines if that move results in check or not, and then undoes it
     * @param from - The initial position
     * @param to - The position the piece is being moved to
     * @param king - The passed in color's king to be checked
     * @param color - The color of the king to be checked for check
     * @return true if the move still results in check
     */
    public boolean checkMoveThenUndo(Position from, Position to, Position king, GameColor color) {
        boolean check;
        SquareIF fromSquare = this.board.getSquares()[from.getRank().getRow()][from.getFile().getColumn()];
        SquareIF toSquare = this.board.getSquares()[to.getRank().getRow()][to.getFile().getColumn()];
        PieceIF piece = toSquare.getPiece();

        if (fromSquare.getPiece() != null &&
                fromSquare.getPiece().getChessPieceType().equals(ChessPieceType.valueOf("KING"))) {
            king = new Position(to.getRank(), to.getFile());
        }

        //move to new spot then see if check is still true
        move(from.getFile(), from.getRank(), to.getFile(), to.getRank());
        check = isCheck(king, color);
        fromSquare.setPiece(toSquare.getPiece());
        toSquare.setPiece(piece);
        return check;
    }

    /**
     * Determines whether a string given represents a valid board position
     * and if so turns it into a Position object.
     * @param pos The string to check
     * @return a Position object if the position is valid or null if not
     */
    public Position parsePosition(String pos) {
        Position result = null;
        // only parse if the string is two characters
        pos = pos.toLowerCase();
        if(pos.length() == 2) {
            File file = File.searchName(pos.charAt(0));
            int row = Character.getNumericValue(pos.charAt(1));
            Rank rank = Rank.searchName(row);
            // only create the position object if a valid rank and file were found
            if(rank != null && file != null) {
                result = new Position(rank, file);
            }
        }
        return result;
    }

    /**
     * Returns the chess's board
     * @return the board for the chess game
     */
    @Override
    public BoardIF getBoard() {
        return this.board;
    }

    /**
     * Gets the current position to
     * @return this chess's current position to
     */
    @Override
    public Position getCurrentTo() {
        return this.currentTo;
    }

    /**
     * Gets the current position from
     * @return this chess's current position from
     */
    @Override
    public Position getCurrentFrom() {
        return this.currentFrom;
    }

    /**
     * Sets a new value to this chess field's currentFrom
     * @param newFrom the new position to set it with
     */
    @Override
    public void setCurrentFrom(Position newFrom) {
        this.currentFrom = newFrom;
    }

    /**
     * Sets a new value to this chess field's currentTo
     * @param newTo the new position to set it with
     */
    @Override
    public void setCurrentTo(Position newTo) {
        this.currentTo = newTo;
    }

    /**
     * Gets the black player
     * @return the player playing the black pieces
     */
    @Override
    public PlayerIF getPlayerBlack() {
        return this.playerBlack;
    }

    /**
     * Gets the white player
     * @return the player playing the white pieces
     */
    @Override
    public PlayerIF getPlayerWhite() {
        return this.playerWhite;
    }

    /**
     * Finds if a player is in check
     * @return true if in check, false otherwise
     */
    @Override
    public boolean getInCheck() {
        return this.check;
    }
}
