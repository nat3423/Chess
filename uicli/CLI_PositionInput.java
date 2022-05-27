package uicli;


import interfaces.UIFinishedObserverIF;
import interfaces.UI_Interface;

/**
 * A command line interface for entering positions. 
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class CLI_PositionInput implements UI_Interface<String>{
	
	/**The user's response**/
	private String resp;
	
	/**To prompt the user**/
	private String prompt;
	
   /**The observer for when the UI is finished**/
   private UIFinishedObserverIF<String> uifo;
	
	/**Construct a UI position input**/
	public CLI_PositionInput(String prompt) {
		this.prompt = prompt;
	}

	
	/**Display the user prompt and store the entered value**/
	@Override
	public void showUI() {
        System.out.print(prompt + ":\n(Input Here)>>>> ");
       resp = kbInput.next().toLowerCase().trim();
		
		finished();
	}

	/**
	 * Change the prompt given the user before they enter data via showUI().
	 * @param prompt The text of the prompt
	 */
	@Override
	public void  setPrompt(String prompt) {
		this.prompt = prompt;
	}
	
	/**
	 * Display error message to the user and reprompt.
	 */
	@Override
	public void displayError(String message) {
		System.out.println(message);
		showUI();//repromot the user for input
	}
	
	
	/**
	 * Display a message to the user.
	 * @param message to show the user on screen.
	 */
	@Override
	public void setMessage(String message) {
		System.out.println(message);
	}
	
	
	/**
	 * Set the UIFinishedObserverIF implementation.
	 * @param uifo The object to be notified when the UI is done.
	 */
	@Override
	public void setFinishedObserver(UIFinishedObserverIF<String> uifo) {
		this.uifo = uifo;
		
	}

	/**Notify the observer that the UI has finished and pass it the user's 
	 * response.
	 */
	@Override
	public void finished() {
		if(uifo != null)
			uifo.inputFinished(resp,this);
	}

}//end class
