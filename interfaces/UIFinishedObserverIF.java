package interfaces;

/**
 * Allows a class to be notified by a UI element that input has completed.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public interface UIFinishedObserverIF< T> {

	/**Tell the observer that the UI has finished and that you wish to complete
	 * @param origin A reference to the object that completed.
	 * **/
	public void inputFinished( T result, UI_Interface<T> source);
}
