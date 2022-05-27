package application.colorScreen.colorama;

import application.ScreenChangeHandler;
import application.colorScreen.ColorChangerIF;
import application.colorScreen.components.SliderPane;
import application.colorScreen.components.SliderWasChanged;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * A simple color chooser using three SliderPanes
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */

public class ColorChooser extends GridPane implements EventHandler<ActionEvent>,
		SliderWasChanged /*Any more needed??*/{
	
	/**The selected color as a hex value (RGB)**/
	String selectedColor;
	
	/**Red color slider panel**/
	private SliderPane red;
	
	/**Green color slider panel**/
	private SliderPane green;
	
	/**Blue color slider panel**/
	private SliderPane blue;
	
	/**Fields to hold the buttonse**/
	private Button select, exit;
	
	/**The area on to which the color is shown**/
	StackPane color;
	
	/**The color text label**/
	Label hexColor;
	
	/**Maximum color intensity**/
	private final int MAX_INTEN = 255;
	
	/**Minimum color intensity**/
	private final int MIN_INTEN = 0;
	
	/**The singleton instance of this class**/
	private static ColorChooser instance;

	/**A screen changer to allow for switching screens**/
	private ScreenChangeHandler sch;

	/**A single instance of this class.*/
	private ColorChangerIF colorChanger;

	
	/**Create a singleton instance of a ColorChooser**/
	public static ColorChooser getInstance(){
		if (instance == null) instance = new ColorChooser();
		return instance;
	}//end getInstance
	
	/**Construct a color chooser. */
	private  ColorChooser(){

		this.setId("Background");

		this.setVgap(20);
		//Grid constraints for row
		RowConstraints row0 = new RowConstraints();
		row0.setPercentHeight(10);
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(40);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(40);
		RowConstraints row3 = new RowConstraints();
		row3.setPercentHeight(10);
		
		//grid constraints for columns
		ColumnConstraints col0 = new ColumnConstraints();
		col0.setPercentWidth(50);
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(50);
		
		//Apply constraints
		this.getRowConstraints().addAll(row0, row1,row2, row3);
		this.getColumnConstraints().addAll(col0,col1);
		
		
		//Top panel for color
		color = new StackPane();
		color.getStyleClass().add("color");
		hexColor = new Label();
		hexColor.getStyleClass().add("color_text");
		color.getChildren().add(hexColor);
		setBackround(0, 0, 0);//set to black

		//Create Panel for sliders with centered layout
		HBox  sliders = new HBox();
		sliders.setSpacing(20.0);
		sliders.setAlignment(Pos.TOP_CENTER);
		
		//Construct 3 sliders for RGB
		red = new SliderPane("Red",MIN_INTEN, MIN_INTEN, MAX_INTEN);
		green = new SliderPane("Green",MIN_INTEN, MIN_INTEN, MAX_INTEN);
		blue = new SliderPane("Blue",MIN_INTEN, MIN_INTEN, MAX_INTEN);

		//Add the sliders
		sliders.getChildren().add(red);
		sliders.getChildren().add(green);
		sliders.getChildren().add(blue);

		select = new Button("Select");
		exit = new Button("Exit");
		select.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		exit.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

		select.getStyleClass().add("buttonStyle1");
		exit.getStyleClass().add("buttonStyle1");

		
		sliders.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
		color.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);

		//Positions the nodes onto the screen
		Label title = new Label("Select Color");
		title.getStyleClass().add("bigLabel");
		this.add(title, 0, 0, 1, 1);
		this.add(color, 0,1,2,1);
		this.add(sliders, 0, 2,2,1);
		this.add(exit, 1,3, 1,1);
		this.add(select, 0,3,1,1);

		//Sets what the buttons will do
		select.setOnAction(this);
		exit.setOnAction(this);
		select.setOnAction(buttonHandler);
		exit.setOnAction(buttonHandler);

		red.setSliderWasChangedObserver(this);
		green.setSliderWasChangedObserver(this);
		blue.setSliderWasChangedObserver(this);

	}//end constructor
	
	/**
	 * Set the background color of the  chooser
	 * @param r The red intensity 0..255
	 * @param g The blue intensity 0..255
	 * @param b The green intensity 0..255
	 */
	public void setBackround(int r, int g, int b){
		//Avoid invalid values
		if(r > 255 || g > 255 || b >255) return;
		
		String hr = Integer.toHexString(r);
		String hg = Integer.toHexString(g);
		String hb = Integer.toHexString(b);
		
		if(r+g+b/3 > 127)
			hexColor.setTextFill(Color.BLACK);
		else
			hexColor.setTextFill(Color.WHITE);
		
		//Add preceeding 0 if only 1 char
		if(hr.length() == 1)
			hr = 0 + hr;	
		if(hg.length() == 1)
			hg = 0 + hg;	
		if(hb.length() == 1)
			hb = 0 + hb;
		
		selectedColor = hr+hg+hb;
		color.setStyle("-fx-background-color: #" + selectedColor);
		hexColor.setText("#" + selectedColor);

	}//end setBackground

	/**The method of the button EventHandler interface implementation that
	 * this class has**/
	@Override
	public void handle(ActionEvent event) {

	}//end handle

	@Override
	public void sliderWasChanged(int value, SliderPane slider) {
		this.setBackround(red.getValue(), green.getValue(), blue.getValue());
	}

	/**
	 * An anonymous event handler to determine what buttons should do
	 */
	EventHandler<ActionEvent> buttonHandler = new EventHandler<>() {
		@Override
		public void handle(ActionEvent actionEvent) {
			Object o = actionEvent.getSource();
			if (o == select) {

				if(colorChanger.getChangeWhite()) {
					colorChanger.setColorWhite(selectedColor);
				} else {
					colorChanger.setColorBlack(selectedColor);
				}
				sch.switchScreen(4);

			} else if (o == exit) {
				sch.switchScreen(4);
			}
		}
	};

	/**
	 * Sets the screen changer to be used with this screen
	 * @param sch the screen changer to use
	 */
	public void setScreenChangeHandler(ScreenChangeHandler sch) {
		this.sch = sch;
	}

	/**
	 * Initializes this class's colorChanger
	 * @param newColorChanger the color changer to set to
	 */
	public void setColorChanger(ColorChangerIF newColorChanger) {
		this.colorChanger = newColorChanger;
	}

}//end class

