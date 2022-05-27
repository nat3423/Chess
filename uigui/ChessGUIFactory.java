package uigui;

import controller.ChessGUI;
import interfaces.ChessIF;

/**
 * A class that creates ChessGUI references.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class ChessGUIFactory {

    /**
     * Creates the ChessGUI instance
     * @return the ChessGUI
     */
    public ChessIF createChessGUI() {
        return new ChessGUI();
    }

}
