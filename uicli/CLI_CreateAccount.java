package uicli;

import interfaces.PlayerIF;
import interfaces.UIFinishedObserverIF;
import interfaces.UI_Interface;
import model.Player;

/**
 * A commad line input for player account creation.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class CLI_CreateAccount implements UI_Interface<PlayerIF> {
	
	   /**The playerIF instance created**/
	   private PlayerIF player = null;
	   
	   /**The observer for when the UI is finished**/
	   private UIFinishedObserverIF<PlayerIF> uifo;
	   
	   /**Create an CLI for creating an account**/
	   public CLI_CreateAccount() {

	   }
	   
	   
    /**
     * Displays prompts for the users name, userID,and password,
     * and get user input to create a new player object
     * @return A new player object created from getting user input
     */
    public void showUI(){
        String first, last, userID, password;
        System.out.print("\n(Enter First Name)>>>> ");
        first = kbInput.next();
        System.out.print("(Enter Last Name)>>>> ");
        last = kbInput.next();
        userID = this.promptID(); 
        System.out.print("(Enter Password)>>>> ");
        password = kbInput.next();
        
        player = new Player(first, last, userID, password);
        
        finished();//when a GUI is used this method would be called by 
                   //clicking an ok button rather than just being called
        		   //like this.
    }
    
    
    /**
     * Prompts the user to input an id of length 10 or less
     * @return A String of at most length 10 representing the user's
     *         chosen id
     */
    private String promptID(){
        String userID;
        System.out.print("(Enter a User ID - 10 characters max)>>>> ");
        userID = kbInput.next();
        if(userID.length() > 10){
            userID = userID.substring(0, 10);
        }
        return userID;
    }
    
    
    /**
     * Set the class to be notified when the UI is finished.
     * @param uifo The UIFinishedObserverIF implementation.
     */
	public void setFinishedObserver(UIFinishedObserverIF<PlayerIF> uifo) { 
    	this.uifo =  uifo;
    }
    
    /**When the UI is finished notify the observer**/
    public void finished() {
    	if(uifo != null)
    		uifo.inputFinished(player,this);

    }

}
