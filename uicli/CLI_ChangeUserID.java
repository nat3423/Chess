package uicli;

import java.util.HashMap;

import interfaces.PlayerIF;
import interfaces.UIFinishedObserverIF;
import interfaces.UI_Interface;

/**
 * Prompt the user to log in and change their user id.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class CLI_ChangeUserID  implements UI_Interface<Boolean>{
	
    /**The user's response, true if change successful**/
	private Boolean response = false;
	
    /**The observer for when the UI is finished**/
    private UIFinishedObserverIF<Boolean> uifo;
    
	/**A reference to the data base of players**/
	private HashMap<String,PlayerIF> playerDB;
	
	/**
	 * Create a CLI player log in
	 * @param players The database of players
	 */
	public CLI_ChangeUserID(HashMap<String,PlayerIF> playerDB) {
		this.playerDB= playerDB;
	}
	

    /**Show the UI to the user so they may respond**/
	@Override
	public void showUI() {
		boolean cont = getConfirmation("You must login, Continue?");
		
		if(cont) {
		 	  String prompt1 = "Log in:";
		 	  
		 	  //Launch the log in UI - Not using the factory because its already been decided we have
		 	  //a CLI implimentation.
			  UI_Interface<PlayerIF> player1Login = new CLI_PlayerLogIn(playerDB,prompt1);
			  //Observer implimentatin for the log in.
	    	  player1Login.setFinishedObserver(new UIFinishedObserverIF<PlayerIF>() {
	
	    		/**
	    		 * When the player has loggedin.
	    		 * @param result the logged in player.
	    		 * @param source A reference to the source UI.
	    		 */
				@Override
				public void inputFinished(PlayerIF result,UI_Interface<PlayerIF> source) {
					
					if(result != null) {
						String id = promptID();
						result.setID(id);
						response = true;//success
						finished();
					}
				
				}//end inputFinished;
		    		  
	    	  });//Anon class
		}//end if
		else
			finished();
	}//end showUI
	
    /*
     * Prompts the user to input an id of length 10 or less
     * @return A String of at most length 10 representing the user's
     *         chosen id
     */
    public String promptID(){
        String userID = null;
        
        while(userID == null) {
	        System.out.print("(Enter a User ID - 10 characters max)>>>> ");
	        userID = kbInput.next();
	        if(userID.length() > 10){
	            userID = userID.substring(0, 10);
	        }
        }//end while
        return userID;
    }
	
    /**
     * A generalized prompt to get a boolean value representing yes or no
     * to a prompt given
     * @param message A String of the prompt message
     * @return A value representing a yes or no response
     */
    private boolean getConfirmation(String message){
        boolean confirm = false;
        String response;
        System.out.print("\n"+message+"\n(Y to confirm)>>>> ");
        response = kbInput.next().substring(0, 1);
        
        if(response.toUpperCase().equals("Y")){
            confirm = true;
        }
        
        return confirm;
    }//end getConformation

    /**
     * Set the observer that listens to see if the UI has finished.
     * @param uifo The observer listening for the UI to finish.
     */
	@Override
	public void setFinishedObserver(UIFinishedObserverIF<Boolean> uifo) {
		this.uifo = uifo;	
	}

	/**The UI is finished**/
	@Override
	public void finished() {
		uifo.inputFinished(response,this);

	}

}//end class
