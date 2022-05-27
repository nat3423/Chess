package uicli;

import interfaces.UIFinishedObserverIF;
import interfaces.UI_Interface;

/**
 * A command line implementation of color options.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class CLI_ColorOptions implements UI_Interface<Boolean> {
	

    /**True if the user wants color, false otherwise**/
	private Boolean response = false;
	
    /**The observer for when the UI is finished**/
    private UIFinishedObserverIF<Boolean> uifo;
    
    /**
     * Construct a command line colorOptions implimentation.
     */
    public CLI_ColorOptions() {

    }

    /**
     * Show the user interface for entering in color options.
     */
	@Override
	public void showUI() {
		
		System.out.println("Do you want a Color(C) or Mono(m) command line board?");
		
		String resp = kbInput.next().trim().toLowerCase();
		while(resp.length() > 1 || resp.length()  <= 0 &&
			 resp.charAt(0) != 'c'&&  resp.charAt(0) == 'm') {
			System.out.println("Invalid input try again");
			
			
			System.out.println("Do you want a Color(C) or Mono(m) command line board?");
			resp = kbInput.next().trim().toLowerCase();
		}
		
		this.response = resp.charAt(0) == 'c'? true: false;
	
		finished();
		
	}//end showUI

	/**
	 * Set the observer for when this UI has completed
	 * @param uifo The UIFinishedObserverIF interface implimentation to notify.
	 */
	@Override
	public void setFinishedObserver(UIFinishedObserverIF<Boolean> uifo) {
		this.uifo = uifo;	
	}

	/**Finished so notify the observer**/
	@Override
	public void finished() {
		uifo.inputFinished(response,this);

	}
	

}
