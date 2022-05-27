package interfaces;

import java.util.Scanner;

/**
 * An interface that any User Interface component should implement for loosely coupled interaction between the
 * user interface and the control logic of the program. This is required whethr the UI is command line or 
 * a GUI.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 *
 * @param <T>
 */
public interface UI_Interface<T> {
	
	/**For keyboard input a single scanner is used for all**/
	public static Scanner kbInput = new Scanner(System.in);
	
	/**Show the user interface to the user**/
	public void showUI() ;
	
	/**
	 * Set the obsever for the UI_Interface component developed. This is optional because
	 * there is a default iplimentation that does nothing.
	 * @param uifo The UIFinishedObserverIF to notify when the UI is done.
	 */
	public default  void setFinishedObserver(UIFinishedObserverIF<T> uifo) {};
	
	
	/**Notify the observer that the UI is finishe. Implimenting this is optional as
	 * there is a default implimentation**/
	public default void finished() {};
	
	/**
	 * Default methods do not need to be implemented, but probably should 
	 * when the behavior is to change.
	 * @param error
	 */
	public default void displayError(String error) {
		System.out.println("Error");
	}
	
	/**
	 * Set a the message to display to the user Implementation is optional as this method has a default 
	 * Implementation that does nothing. Its use is implementation specific. It can be used as
	 * a pre-rompt or to display a message to the user following a prompt.
	 * @param message
	 */
	public default void setMessage(String message) {}
	
	
	/**
	 * Set the prompt given when the ui is shown via the show UI function.
	 * Implementation is optional as a default implementation that does nothing is supplied.
	 * @param promt The prompt in the message.
	 */
	public default void setPrompt(String promt) {};
	

}//end interface
