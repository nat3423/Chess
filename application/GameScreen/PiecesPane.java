package application.GameScreen;

import application.ScreenChangeHandler;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

/**
 * A class modeling the section on the sides of the game screen where the
 * player's captured pieces are shown
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class PiecesPane implements GamePaneIF{

    /**The main pieces pane**/
    Pane root;

    /**A screen changer to allow for switching screens**/
    ScreenChangeHandler sch;

    /**
     * Constructor to create the pieces pane
     */
    public PiecesPane(){
        this.root = new TilePane();
        this.root.getStyleClass().add("GamePiecesPane");
    }

    /**
     * Gets the root of this screen
     * @return root, the root of this screen
     */
    public Pane getRoot(){
        return this.root;
    }

    /**
     * Sets the screen changer to be used with this screen
     * @param sch the screen changer to use
     */
    public void setScreenChangeHandler(ScreenChangeHandler sch) {
        this.sch = sch;
    }
}