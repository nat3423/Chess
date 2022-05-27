package uigui;

import application.MainScreen;
import application.OptionWasSelected;
import javafx.application.Application;
import application.Main;
import interfaces.UIFinishedObserverIF;
import interfaces.UI_Interface;

/**
 * A GUI for the main options in chess
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class GUI_MainOptions  implements UI_Interface<Integer>,
        OptionWasSelected {
    /**The observer for when the UI is finished**/
    private UIFinishedObserverIF<Integer> uifo;

    /**The response by the user**/
    private Integer response;

    /**A reference to MainOptions*/
    private static GUI_MainOptions instance;

    /**How to get the single instance of Main Options.*/
    public static GUI_MainOptions getInstance() {
        if (instance == null) instance = new GUI_MainOptions();
        System.out.println("TEST - getInstance called from MainScreen");
        return instance;
    }

    /**
     * Show the user interface to the user
     **/
    @Override
    public void showUI() {
        Application.launch(Main.class,"ChessMeister");
        MainScreen ms = MainScreen.getInstance();
        ms.setOptionWasSelected(this);
    }

    /**Set the observer for this class to listen for when the UI is done.
     * @param uifo The UIFinishedObserverIF will listen for when the user has
     *             finished their selection.
     */
    @Override
    public void setFinishedObserver(UIFinishedObserverIF<Integer> uifo) {
        this.uifo =  uifo;
    }

    /**
     * Notify the observer that the user has finished input
     * **/
    @Override
    public void finished() {
        if(uifo != null) {
            uifo.inputFinished(response, this);
        }
    }

    /**
     * When notified by the MainMenu, it sends Chess which screen was chosen.
     * @param option The screen selected in MainMenu (from the application)
     */
    public void optionWasSelected(int option){
        response = option;
        finished();
    }
}