package application.GameScreen;

import application.MessageHandlerIF;
import application.ScreenChangeHandler;
import enums.File;
import enums.GameColor;
import enums.Rank;
import interfaces.ChessIF;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import model.Position;
import uigui.ChessGUIFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * A class modeling the center section of the game screen where the chess
 * board will be displayed
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class GameCenterPane implements GamePaneIF, TileClickedHandler,
        CenterPaneIF {

    ScreenChangeHandler sch;

    /** This pane is the root pane of the center */
    TilePane root;

    /**The array of buttons that comprise the chess board**/
    ChessTile[][] buttons;

    /**holds a reference to the chess game**/
    ChessIF chess;

    /**For creating the chess game**/
    ChessGUIFactory chessFactory;

    /**Finds whose turn it is**/
    boolean whiteTurn = true;

    /**Finds if the player has selected a piece**/
    boolean hasSelectedPiece = false;

    /**For converting a string to the corresponding char value of the board**/
    HashMap<String, Character> intToChar;

    /**The rank to move to**/
    Rank fromR;

    /**The File to move from**/
    File fromF;

    /**Holds the highlighted Tiles**/
    Position[] highlightedMoves;

    /**The handler to send messages to**/
    MessageHandlerIF messenger;

    /**The handler for updating the pieces**/
    PieceCapturedHandlerIF pieceCaptureHandler;

    /**
     * Makes a new center pane for the game screen
     */
    public GameCenterPane(){
        this.root = new TilePane();
        buttons = new ChessTile[8][8];
        chessFactory = new ChessGUIFactory();
        chess = chessFactory.createChessGUI();
        chess.newGame();
        defineLayout();
    }

    /**
     * Set the layout for this tilepane
     */
    public void defineLayout() {
        root.setPrefColumns(8);
        root.setPrefRows(8);
        root.setAlignment(Pos.CENTER);

        // Create a HashMap for converting an int to a char
        intToChar = new HashMap<String, Character>();

        // Add keys and values for later conversion
        intToChar.put("0", 'a');
        intToChar.put("1", 'b');
        intToChar.put("2", 'c');
        intToChar.put("3", 'd');
        intToChar.put("4", 'e');
        intToChar.put("5", 'f');
        intToChar.put("6", 'g');
        intToChar.put("7", 'h');

        this.defineChessBoard();
        this.displayBoard();
    }

    public void displayBoard() {
        char[] columns;
        columns = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

        int colCount = 0, rowCount = 0;
        String filename = "";

        for(int row = 0; row < buttons.length; row++) {
            colCount = 0;
            for(char col: columns) {

                //Then a piece exists at this location
                if(chess.getBoard().getPiece(row, Character.toLowerCase(col)) !=
                        null) {

                    //If the piece is black
                    if(chess.getBoard().getPiece(row, Character.
                            toLowerCase(col)).getColor() == GameColor.BLACK) {
                        filename = "B";

                    //The piece is white
                    } else {
                        filename = "W";
                    }

                    //Finds the path to the file
                    filename = filename + chess.getBoard().getPiece(row,
                            Character.toLowerCase(col)).getChessPieceType().
                            getSymbol();

                    //Adds the image to the button
                    addImage(buttons[rowCount][colCount].getB(),
                            "src/application/ChessPieces/"+filename+".png");
                }

                colCount++;
            }

            rowCount++;
        }
    }

    /**
     * Helper method to create the buttons in the nested array of buttons
     * and set their colors and images for the pieces. It calls two helper
     * methods to set the special rows of pieces
     * (8 for black and 1 for white) and adds pawns while iterating through.
     */
    public void defineChessBoard() {
        int k = 1;
        for (int i = 0; i < buttons.length; i++) {
            k--;
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j] = new ChessTile(Integer.toString(i),
                        Integer.toString(j));
                buttons[i][j].setTileClickedHandler(this);
                if (k % 2 == 0) {
                    buttons[i][j].setBStyle("BlackChessTile");
                    buttons[i][j].setIsWhite(false);
                } else {
                    buttons[i][j].setBStyle("WhiteChessTile");
                    buttons[i][j].setIsWhite(true);
                }
                k++;
            }
        }
        addButtons();
    }

    /**
     * Helper method to add all the buttons to the board
     */
    public void addButtons() {
        for (ChessTile[] but: buttons) {
            for (ChessTile b: but) {
                root.getChildren().add(b.getB());
            }
        }
    }

    /**
     * Helper method to add an image to a button
     * @param b the button to add an image to
     * @param image the path to the image
     */
    private void addImage(Button b, String image) {
        try {
            Image img = new Image(new FileInputStream(image));
            ImageView imv = new ImageView(img);
            imv.setFitHeight(70);
            imv.setPreserveRatio(true);
            b.setGraphic(imv);
        } catch (FileNotFoundException fnfe) {
            System.out.println("Error: Image not found");
            System.exit(1);
        }
    }

    /**
     * Returns the root pane for this part of the screen
     * @return the root pane for this part of the screen
     */
    public Pane getRoot(){
        return this.root;
    }

    /**
     * Sets the screen changer to be used with this screen
     * @param sch the screen changer to use
     */
    @Override
    public void setScreenChangeHandler(ScreenChangeHandler sch) {
        this.sch = sch;
    }

    /**
     * What the game will do when a tile is clicked
     * @param rowString the row of the tile clicked
     * @param colString the column of the tile that was clicked
     */
    @Override
    public void tileWasClicked(String rowString, String colString) {

        int row = Integer.parseInt(rowString);
        char col = intToChar.get(colString);

        GameColor color;
        if(whiteTurn) {
            color = GameColor.WHITE;
        } else {
            color = GameColor.BLACK;
        }
        Position kingPos = chess.getKingPosition(color);

        //Finds if the user is in checkmate
        if(chess.isCheckmate(kingPos, color)) {

            //Display end game message
            sendMessage("CHECKMATE! "+winnerName()+" Wins!");
        }

        //The user is attempting to move a piece
        else if(hasSelectedPiece) {

            //Set the this piece as selected
            Rank toR = Rank.searchRow(row);
            File toF = File.searchName(col);

            //If the User selects their own piece, then unhighlight the moves
            if(fromR.getRow() == toR.getRow() && fromF.getColumn() ==
                    toF.getColumn()) {
                unHighlightPieces(highlightedMoves);
                highlightedMoves = null;
                hasSelectedPiece = false;
                fromR = null;
                fromF = null;
            }

            //Checks if the selected place to move is valid
            else if(isValidMove(toR, toF, highlightedMoves)) {

                if(whiteTurn) {
                    color = GameColor.WHITE;
                } else {
                    color = GameColor.BLACK;
                }
                kingPos = chess.getKingPosition(color);

                //Finds if a player is in check
                if(chess.isCheck(kingPos, color)) {

                    //Moves the piece if the move does not put the player in
                    // check
                    if(!chess.checkMoveThenUndo(new Position(fromR, fromF),
                            new Position(toR, toF),
                            kingPos,
                            color)) {

                        if(chess.getBoard().getPiece(row, col) != null) {
                            pieceCaptureHandler.setCaptured(whiteTurn,
                                    chess.getBoard().getPiece(row, col).
                                            getChessPieceType().getSymbol(),
                                    chess.getBoard().getPiece(row, col).
                                            getColor().toString().substring(0, 1));
                        }

                        //Move the piece and reset the board
                        chess.move(fromF, fromR, toF, toR);
                        unHighlightPieces(highlightedMoves);
                        highlightedMoves = null;
                        hasSelectedPiece = false;
                        fromR = null;
                        fromF = null;
                        removeImages();
                        displayBoard();
                        whiteTurn = !whiteTurn;
                        sendMessage(playerNameTurn()+"'s Turn");
                    }

                    //The move will not move the player out of check
                    else {
                        sendMessage("Cannot move there, while in check");
                    }
                }

                //The player is not in check and wants to move
                else {

                    if(chess.getBoard().getPiece(row, col) != null) {
                        pieceCaptureHandler.setCaptured(whiteTurn,
                                chess.getBoard().getPiece(row, col).
                                        getChessPieceType().getSymbol(),
                                chess.getBoard().getPiece(row, col).
                                        getColor().toString().substring(0, 1));
                    }

                    chess.move(fromF, fromR, toF, toR);
                    unHighlightPieces(highlightedMoves);
                    highlightedMoves = null;
                    hasSelectedPiece = false;
                    fromR = null;
                    fromF = null;
                    removeImages();
                    displayBoard();
                    whiteTurn = !whiteTurn;
                    sendMessage(playerNameTurn()+"'s Turn");
                }
            }
        }

        //A piece has not been selected yet and the user wants to select this
        // piece
        else {

            //If the selected tile has a piece
            if (chess.getBoard().getPiece(row, col) != null) {

                //White is selecting a piece
                if (whiteTurn) {

                    //Finds if the piece selected is of the user's color
                    if (chess.getBoard().getPiece(row, col).getColor() ==
                            GameColor.WHITE) {

                        //Set the this piece as selected
                        fromR = Rank.searchRow(row);
                        fromF = File.searchName(col);

                        //Display available moves
                        highlightedMoves = chess.getBoard().getPiece(row, col).
                                showMoves(new Position(fromR, fromF));

                        //Only highlight the piece if there is a move for this
                        // piece
                        if(highlightedMoves.length > 0) {
                            //Highlights the moves and updates the board
                            highlightPieces(highlightedMoves);
                            displayBoard();
                            hasSelectedPiece = true;
                            sendMessage(playerNameTurn() + " selected " +
                                    Character.toUpperCase(col) + (row + 1));
                        } else {
                            sendMessage("No moves for that piece");
                        }

                    } else {
                        sendMessage(playerNameTurn()+
                                ", that piece is not your color");
                    }
                }

                //Black is selecting a piece
                else {

                    //Finds if the piece selected is of the user's color
                    if (chess.getBoard().getPiece(row, col).getColor() ==
                            GameColor.BLACK) {

                        //Set the this piece as selected
                        fromR = Rank.searchRow(row);
                        fromF = File.searchName(col);

                        //Display available moves
                        highlightedMoves = chess.getBoard().getPiece(row, col).
                                showMoves(new Position(fromR, fromF));

                        //Only highlight the piece if there is a move for
                        // this piece
                        if(highlightedMoves.length > 0) {
                            //Highlights the moves and updates the board
                            highlightPieces(highlightedMoves);
                            displayBoard();
                            hasSelectedPiece = true;
                            sendMessage(playerNameTurn() +
                                    " selected " + Character.toUpperCase(col) +
                                    (row + 1));
                        } else {
                            sendMessage("No moves for that piece");
                        }
                    } else {
                        sendMessage(playerNameTurn()+
                                ", that piece is not your color");
                    }
                }
            }
        }
    }

    /**
     * Gets the name of the winner
     * @return the name of the winner
     */
    private String winnerName() {
        if(whiteTurn) {
            return "Player 2";
        }
        return "Player 1";
    }

    /**
     * Finds the name of the current player
     * @return the name of the current player
     */
    private String playerNameTurn() {
        if(whiteTurn) {
            return "Player 1";
        }
        return "Player 2";
    }

    /**
     * Clears all of the images being displayed
     */
    private void removeImages() {
        for(int i = 0; i < buttons.length; i++) {
            for(int j = 0; j < buttons.length; j++) {
                buttons[i][j].setImage(null);
            }
        }
    }

    /**
     * Finds if a given move is valid
     * @param toR the rank to move to
     * @param toF the file to move to
     * @param highlightedMoves the list of moves available
     * @return true if this is a valid move
     */
    private boolean isValidMove(Rank toR, File toF,
                                Position[] highlightedMoves) {

        for(Position moves: highlightedMoves) {
            if(moves.getFile().getColumn() == toF.getColumn() &&
                moves.getRank().getRow() == toR.getRow()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Highlights the tiles at the specified locations
     * @param moves the positions to highlight
     */
    private void highlightPieces(Position[] moves) {
        for(Position pos: moves) {
            buttons[pos.getRank().getRow()][pos.getFile().getColumn()].
                    highlight();
        }
    }

    /**
     * Unhighlight the tiles to their original color
     * @param moves the places to unhighlight
     */
    private void unHighlightPieces(Position[] moves) {
        for(Position pos: moves) {
            buttons[pos.getRank().getRow()][pos.getFile().getColumn()].
                    unHighlight();
        }
    }

    /**
     * Initializes the messenger to set this class to
     * @param messenger the messenger to send messages to
     */
    public void setMessageHandler(MessageHandlerIF messenger) {
        this.messenger = messenger;
    }

    /**
     * Sets the color of the tiles to a new value
     * @param whiteColor the color of the white tiles to set
     * @param blackColor the color of the black ttiles to set to
     */
    @Override
    public void setTileColor(String whiteColor, String blackColor) {
        for(int i = 0; i < buttons.length; i++) {
            for(int j = 0; j < buttons.length; j++) {

                //Changes the color for the white squares
                if(buttons[i][j].getIsWhite() && whiteColor != null) {
                    buttons[i][j].setColor(whiteColor);

                //Changes the color for the black squares
                } else if( ! buttons[i][j].getIsWhite() && blackColor !=
                        null) {
                    buttons[i][j].setColor(blackColor);
                }

            }
        }
    }

    /**
     * Sends a message to the message handler
     * @param message the message to send to the messenger
     */
    public void sendMessage(String message) {
        messenger.sendMessage(message);
    }

    /**
     * Initializes the captured piece handler for sending the captured pieces to their
     *  respected players
     * @param pieceHandler the handler to set this class' piece handler to
     */
    @Override
    public void setPieceCaptureHandler(PieceCapturedHandlerIF pieceHandler) {
        this.pieceCaptureHandler = pieceHandler;
    }
}