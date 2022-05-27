package uicli;

import interfaces.UI_Interface;

/**
 * This simple class shows message to the user using a CLI. It may seem strange
 * to have something so simple made this complex, but the idea is to code to an 
 * interface so this is substitutable for some GUI component that also obeys the
 * UI_Interface<Boolean> interface.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 *
 */
public class CLI_Message implements UI_Interface<Boolean>{
	
	/**The message to display**/
	private String message;
	
	/**
	 * A CLI UI for showing a message to the user.
	 * @param message The message to display
	 */
	public CLI_Message(String message) {
		this.message = message;
	}


	/**
	 * Set the message to show the user.
	 * @param message The message to show.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**Show the message to the user**/
	@Override
	public void showUI() {
		System.out.println(message);	
	}


}//end class