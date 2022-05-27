package application.colorScreen.application;

import application.colorScreen.colorama.ColorChooser;
import application.colorScreen.colorama.ColorScene;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * A simple holder for other screen
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */

public class Setup extends Application {

	/**Get the instance of this application**/
	private static Setup instance;

	/**The screens that can be used by this application**/
	public enum Screens {Screen1, ColorChooser}

	/**The root pane that all screens fit in to**/
	private Pane root;

	/**the Scene on to which the root is situated**/
	private Scene scene;

	boolean  runOnce = true;

	/**When the application is started**/
	@Override
	public void start(Stage primaryStage) {
		try {
			switchUI(Screens.Screen1);
			scene.getStylesheets().add(getClass().
					getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

		//Maintin a singleton
		if(instance == null)
			instance = this;
	}//end start

	/**Get an instance of this application**/
	public static Setup getInstance(){
		return instance;
	}//end getInstance

	/**Java's main method starts a JavaFX program via the launch command**/
	public static void main(String[] args) {
		launch(args);
	}//end main

	/**Switch the root pane of this screen to change scenes
	 * @param screen The choise of screen*/
	public void switchUI(Screens screen){

		switch(screen){
			case Screen1:
			   root = ColorScene.getInstance();
			   break;
			case ColorChooser:
			   root = ColorChooser.getInstance();
			   break;
		}//end switch

		//Change the screen
		if(scene == null)
			scene = new Scene(root,400,600);
		else
			scene.setRoot(root);

		runOnce = false;
	}//end switchUI

}//end class
