package application.colorScreen.colorama;

import application.colorScreen.application.Setup;
import application.colorScreen.application.Setup.Screens;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;

/**
 * Defined a scene which shows a color
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class ColorScene extends GridPane   {
	
	/**The area on to which the color is shown**/
	StackPane color;
	
	/**The selected color**/
	String selectedColor;
	
	/**The button for when a color is selected**/
	Button buttonColor;
	
	private static ColorScene instance;
	
	/**Create a color screene with black as the default color**/
	private ColorScene(){
		this("000000");
	}

	/**
	 * Create a color scene with a defined color
	 * @param colorSelection the default colors set.
	 */
	private ColorScene(String colorSelection){
		
		selectedColor =  colorSelection;

		RowConstraints row0 = new RowConstraints();
		row0.setPercentHeight(50);
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(50);
		
		//grid constraints for columns
		ColumnConstraints col0 = new ColumnConstraints();
		col0.setPercentWidth(100);

		//Apply constraints
		this.getRowConstraints().addAll(row0, row1);
		this.getColumnConstraints().addAll(col0);

		color = new StackPane();
		color.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		
		buttonColor = new Button("Choose Color");
		GridPane.setHalignment(buttonColor, HPos.CENTER);
		
		this.add(color, 0, 0,1,1);
		this.add(buttonColor,0, 1, 1, 1);
		
		setColor(colorSelection);
		
		//Wire the button to a handler.
		buttonColor.setOnAction(buttonHandler);
	}//end constructor.
	
	/**
	 * Set a color and display the result
	 * @param colorSelection the new color to set
	 */
	public void setColor(String colorSelection){
		selectedColor = colorSelection;
		color.setStyle("-fx-background-color: #" + colorSelection );
	}//nd setColor


	/**An event handler as a field*/
	EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {    
        @Override
        public void handle(ActionEvent event) {
        	//This is tightly coupled, not loosely coupled way to do this.
        	//Easier but not optimal for reuse.
        	Setup.getInstance().switchUI(Screens.ColorChooser);
        }
	};//end buttonHandler

	/**
	 * Gets a singleton instance of this class
	 * @return instance the instance of this class.
	 */
	public static ColorScene getInstance(){
		if(instance == null)
			instance = new ColorScene();
		
		return instance;
	}//end getInstance.
	
}//end class
