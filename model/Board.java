/**
 * A chess board class that contains all the components needed to
 * respresent each square and each piece on a chess board.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
package model;
import model.pieces.*;
import enums.*;
import interfaces.*;
import uicli.*;

/**
 * Models a chess board by implementing the BoardIF interface.
 */
public class Board implements BoardIF {
    /** The squares on the board */
    private SquareIF[][] squares;
    /** The width of the board */
    private int width;
    /** The height of the board */
    private int height;
    /** The implementation for drawing the board */
    private BoardStrategy drawStrategy;

    /**
     * Constructor for the Board object.
     */
    public Board() {
        setup();
    }

    /**
     * Initializes the squares of the chess board.
     */
    public void init_board() {
        for(int i = 0; i < this.height; i++){
            for(int j = 0; j < this.width; j++){
                // alternating black and white squares
                if((i + j) % 2 != 0){
                    squares[i][j] = new Square(GameColor.WHITE, 
                        new Position(Rank.searchRow(i), File.searchColumn(j)));
                }else{
                    squares[i][j] = new Square(GameColor.BLACK, 
                        new Position(Rank.searchRow(i), File.searchColumn(j)));
                }
                
            }
        }
        setBlackPieces();
        setWhitePieces();
    }

    /**
     * Helper method that correctly sets all the white pieces on the board.
     */
    private void setWhitePieces() {
        this.squares[Rank.R1.getRow()][File.A.getColumn()].setPiece(new Rook(ChessPieceType.ROOK,
            GameColor.WHITE, this));
        this.squares[Rank.R1.getRow()][File.H.getColumn()].setPiece(new Rook(ChessPieceType.ROOK,
            GameColor.WHITE, this));

        this.squares[Rank.R1.getRow()][File.B.getColumn()].setPiece(new Knight(ChessPieceType.KNIGHT,
            GameColor.WHITE, this));
        this.squares[Rank.R1.getRow()][File.G.getColumn()].setPiece(new Knight(ChessPieceType.KNIGHT,
            GameColor.WHITE, this));

        this.squares[Rank.R1.getRow()][File.C.getColumn()].setPiece(new Bishop(ChessPieceType.BISHOP, 
            GameColor.WHITE, this));
        this.squares[Rank.R1.getRow()][File.F.getColumn()].setPiece(new Bishop(ChessPieceType.BISHOP,
            GameColor.WHITE, this));

        this.squares[Rank.R1.getRow()][File.D.getColumn()].setPiece(new Queen(ChessPieceType.QUEEN,
            GameColor.WHITE, this));
        this.squares[Rank.R1.getRow()][File.E.getColumn()].setPiece(new King(ChessPieceType.KING, 
            GameColor.WHITE, this));
        
        for(int i = 0; i < this.getWidth(); i++){
            this.squares[Rank.R2.getRow()][i].setPiece(new Pawn(ChessPieceType.PAWN, GameColor.WHITE, this));
        }
    }
    
    /**
     * Helper method that correctly sets all the black pieces on the board.
     */
    private void setBlackPieces() {
        this.squares[Rank.R8.getRow()][File.A.getColumn()].setPiece(new Rook(ChessPieceType.ROOK,
            GameColor.BLACK, this));
        this.squares[Rank.R8.getRow()][File.H.getColumn()].setPiece(new Rook(ChessPieceType.ROOK,
            GameColor.BLACK, this));

        this.squares[Rank.R8.getRow()][File.B.getColumn()].setPiece(new Knight(ChessPieceType.KNIGHT,
            GameColor.BLACK, this));
        this.squares[Rank.R8.getRow()][File.G.getColumn()].setPiece(new Knight(ChessPieceType.KNIGHT,
            GameColor.BLACK, this));

        this.squares[Rank.R8.getRow()][File.C.getColumn()].setPiece(new Bishop(ChessPieceType.BISHOP,
            GameColor.BLACK, this));
        this.squares[Rank.R8.getRow()][File.F.getColumn()].setPiece(new Bishop(ChessPieceType.BISHOP,
            GameColor.BLACK, this));

        this.squares[Rank.R8.getRow()][File.D.getColumn()].setPiece(new Queen(ChessPieceType.QUEEN,
            GameColor.BLACK, this));
        this.squares[Rank.R8.getRow()][File.E.getColumn()].setPiece(new King(ChessPieceType.KING,
            GameColor.BLACK, this));
        
        for(int i = 0; i < this.getWidth(); i++){
            this.squares[Rank.R7.getRow()][i].setPiece(new Pawn(ChessPieceType.PAWN, GameColor.BLACK, this));
        }
        
    }
    

    /**
     * Sets up the board to be ready for use.
     */
    public void setup() {
        this.width = 8;
        this.height = 8;
        this.squares = new SquareIF[height][width];
        init_board();
        // setting default strategy
        setDrawStrategy(new BoardMonoCLI());
    }

    /**
     * Draws or displays the board for gameplay purposes.
     */
    public void draw() {
        this.drawStrategy.draw(this);
    }

    /**
     * Getter method for the squares of the board.
     * @return The SquareIF array holding the squares on the board.
     */
    public SquareIF[][] getSquares() {
        return this.squares;
    }

    /**
     * Sets the strategy or implementation for drawing the board.
     * @param d The specific implementation that will be set.
     */
    public void setDrawStrategy(BoardStrategy d) {
        this.drawStrategy = d;
    }

    /**
     * Getter method for the width of the board.
     * @return The width of the board.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Getter method for the height of the board.
     * @return The height of the board.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Gets the piece at the specified rank and file on the board.
     * @param r The rank at which to get the piece from.
     * @param f The file at which to get the piece from.
     * @return The PieceIF object at the specified location on the board.
     */
    public PieceIF getPiece(Rank r, File f) {
        return this.squares[r.getRow()][f.getColumn()].getPiece();
    }

    /**
     * Gets the piece at the specified column and row on the board.
     * @param col The column of the piece to get.
     * @param row The row of the piece to get.
     * @return The PieceIF object at the specified location on the board.
     */
    public PieceIF getPiece(int row, char col) {
        return this.squares[row][File.searchName(col).getColumn()].getPiece();
    }

    /**
     * Highlights a square given a rank and file on the board.
     * @param r The rank of the square to highlight
     * @param f The file of the square to hightlight
     */
    public void highlightSquare(Rank r, File f){
        this.squares[r.getRow()][f.getColumn()].setHighlighted(true);
    }

    @Override
    /**
     * Returns the board as a String output.
     * @return The Board's state as a String.
     */
    public String toString(){
        String result = "";
        PieceIF piece;
        for(int i = 0; i < this.height; i++){
            for(int j = 0; j < this.width; j++){
                piece = squares[i][j].getPiece();
                if(piece != null){
                    result += piece.toString();
                }else{
                    result += "E";
                }
            }
        }

        return result;
    }
}
