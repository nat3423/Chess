package uicli;

import java.util.HashMap;

import interfaces.PlayerIF;
import interfaces.UIFinishedObserverIF;
import interfaces.UI_Interface;

/**
 * A command line UI for player log in.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class CLI_PlayerLogIn implements UI_Interface<PlayerIF> {

	/**The player logged in**/
	private PlayerIF player = null;
	
	/**A reference to the data base of players**/
	private HashMap<String,PlayerIF> players;
	
   /**The observer for when the UI is finished**/
   private UIFinishedObserverIF<PlayerIF> uifo;

	/**
	 * Create a CLI player log in
	 * @param players The database of players
	 */
	public CLI_PlayerLogIn(HashMap<String,PlayerIF> players,String prompt) {
		this.players = players;
	}
	
	
	/**
	 * Prompt user to log in
	 */
	@Override
	public void showUI() {
	   String userID, password;
        PlayerIF player = null;
        
        //While the player is null
        while(player == null) {
	        System.out.print("\n(Enter your user ID)>>>> ");
	        userID = kbInput.next();
	        if(players.get(userID) != null){
	            System.out.print("(Enter your password)>>>> ");
	            password = kbInput.next();
	            if(players.get(userID).validatePassword(password)){
	                player = players.get(userID);
	            }
	        }
	        
	        if (player != null) 
	        	System.out.println("Error! invalid user IDPl, try again");
        }//end while
		
       
       this.player = player;//make global
        
        
        finished();//Notify the the observers the user has logged in.
                  //Had this been done with a GUI, this would be called
                 //By an ok button instead of after command line input ends.
        
	}//end main

	
	/**Set the observer to listen for when the player has logged in
	 * @param uifo The UIFinishedObserver that listens for when the player is logged in.
	 */
	@Override
	public void setFinishedObserver(UIFinishedObserverIF<PlayerIF> uifo) {
		this.uifo =uifo;
	}

	/**Tell the observer the input is finished and pass it the selected value**/
	@Override
	public void finished() {
		uifo.inputFinished(player,this);
	}//emd finished

}//end class
