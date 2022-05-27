package application;

import application.GameScreen.GameScreen;
import application.GameScreen.TileColorChangeHandler;
import application.Settings.*;
import application.colorScreen.colorama.ColorChooser;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The driver for the JavaFX GUI
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class Main extends Application implements ScreenChangeHandler,
        TileColorChangeHandler {
    /**The scene of a stage**/
    Scene scene;

    /**The roots of all the screens**/
    Pane screen1, screen2, screen3, screen4, screen5;

    /***/
    LastScreenIF obsLastScreen;

    /**The Gamescreen of Chess*/
    GameScreen gameScreen;

    /**A reference to the last screen we changed to.*/
    private int lastScreen;

    /**
     * Creates the primary stage of the GUI
     * @param primaryStage the primary stage on which screens are displayed
     */
    @Override
    public void start(Stage primaryStage) {
        try {

            //Sets the last screen as our starting scene
            lastScreen = 1;

            //Create the screens
            MainScreen ms = new MainScreen();
            PlayerNames pn = new PlayerNames();
            GameScreen gs = new GameScreen("user1", "user2");
            Settings ss = new Settings();
            ColorChooser cc = ColorChooser.getInstance();

            screen1 = ms.getRoot();
            screen2 = pn.getRoot();
            screen3 = gs.getRoot();
            screen4 = ss.getRoot();
            screen5 = cc;

            //Setting the changers
            ms.setScreenChangeHandler(this);
            pn.setScreenChangeHandler(this);
            pn.setNameChangeHandler(gs);
            gs.getRight().setScreenChangeHandler(this);
            gs.getTop().setScreenChangeHandler(this);
            ss.setScreenChangeHandler(this);
            ss.setTileChangeHandler(this);
            cc.setScreenChangeHandler(this);
            cc.setColorChanger(ss);
            this.setLastScreenObs(ss);

            //Get the root pane of the main screen and place it in the scene.
            scene = new Scene (screen1,800,700);
            //Add the style sheet
            scene.getStylesheets().add(getClass().
                    getResource("application.css").toExternalForm());

            //Setup and show the primary stage.
            primaryStage.setTitle("Chess Meister");
            primaryStage.setScene(scene);
            primaryStage.show();

            gameScreen = gs;

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Entry point to the GUI
     * @param args the arguments that are passed to launch the application
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Allows the user to switch screens by pressing different buttons
     * @param screen the number of the screen to switch to
     */
    public void switchScreen(int screen) {
        //Notified observers of the last screen change of the last
        // screen before it is changed.
        obsLastScreen.lastScreenSelected(lastScreen);

        if (screen == 1) {
            //MainScreen
            lastScreen = 1;
            scene.setRoot(screen1);
        } else if (screen == 2) {
            //PlayerScreen
            lastScreen = 2;
            scene.setRoot(screen2);
        } else if (screen == 3) {
            //GameScreen
            lastScreen = 3;
            scene.setRoot(screen3);
        } else if (screen == 4) {
            //SettingsScreen
            scene.setRoot(screen4);
        } else if (screen == 5) {
            //ColorChooser
            scene.setRoot(screen5);
        }
    }

    /**
     * Sets the tile color to the new color provided
     * @param whiteColor the new color to set white to
     * @param blackColor the new color to set black to
     */
    @Override
    public void setTileColor(String whiteColor, String blackColor) {
        gameScreen.setTileColor(whiteColor, blackColor);
    }

    /**
     * Sets observers of the last screen changed to.
     * @param obs the last screen the application swapped to.
     */
    public void setLastScreenObs(LastScreenIF obs){
        this.obsLastScreen = obs;
    }
}