package uicli;

import interfaces.UIFinishedObserverIF;
import interfaces.UI_Interface;
import interfaces.PlayerIF;

/**
 * A CLI user interface for displaying player information.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class CLI_DisplayPlayer implements UI_Interface<Boolean> {
	
    /**constant Strings to represent color values like in the BoardCLI*/
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001b[38;5;196m";
    public static final String RESET = "\u001B[0m";
    public static final String GREY_BACKGROUND = "\u001B[48;5;248m";
	
    /**A reference to the current player**/
	private PlayerIF currPlayer;
	
	/**If this is a color output or mono**/
	private String boardColor;
	
	/**The optional message to output with the player info.**/
	private String message;
	
	 /**The observer for when the UI is finished**/
	 private UIFinishedObserverIF<Boolean> uifo;
	
    /**
     * A command line UI to display the player's name formatted to match 
     * the way the player's pieces are displayed on the board.
     * Along with an optional message;
     * @param currPlayer This is the Player Object whos name should be 
     *                   displayed.
     * @param boardColor A boolean value representing whether or not the board
     *                  is a color board or a mono board
     * @param message A string message to display after the players name
     */
	public CLI_DisplayPlayer(PlayerIF player, String boardColor, String message) {
		this.currPlayer = player;
		this.boardColor = boardColor;
		this.message = message;
	}//end constructor

	/**Show the UI on the screen for a player**/
	@Override
	public void showUI() {
        boolean isWhite = currPlayer.isWhite();
        String playerID = currPlayer.getID();
        if(!isWhite){
            if(boardColor.equals("Color")){
                playerID = GREY_BACKGROUND + BLACK + " " +playerID + 
                           message + " " + RESET; 
            }else{
                playerID = playerID.toUpperCase() + message; 
            }
        }else{
            if(boardColor.equals("Color")){
                playerID = GREY_BACKGROUND + RED +" "+ playerID +
                           message + " " +  RESET;
            }else{
                playerID = playerID.toLowerCase() + message;
            }
        }
        System.out.println(playerID);
        
        
        finished();//Tell the listener the UI is finished.
	}//end finished
	
	
	/**Set the observer for when this UI is complete
	 * @param uifo The UIFinishedObserver implementation that will listen for when its done.
	 */
	@Override
	public void setFinishedObserver(UIFinishedObserverIF<Boolean> uifo) {
		this.uifo = uifo;	
	}//end uifo

	/**
	 * Tell the observer the UI is done.
	 */
	@Override
	public void finished() {	
		if(uifo != null)
			uifo.inputFinished(true,this);
	}//end finished
	
	
}//end class
