package application.colorScreen.components;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * Defines a simple slider pane with an input field, slider and title.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class SliderPane extends VBox {
	
	/**The label of the control**/
	private Label title;
	
	/**The title above the value text field**/
	private Label valueTitle;
	
	/**The value of the control**/
	private TextField value;
	
	/**The slider control**/
	private Slider slider;
	
	/**The minimum slider value**/
	private int min;
	
	/**The maximum slider value**/
	private int max;

	/**A reference to the observers for this Slide changing.*/
	private SliderWasChanged obs;
	
	/**
	 * Construct a slider
	 * @param titleText The title of the slider.
	 * @param initVal The initial value of the slider.
	 * @param min The minimum value on the slider.
	 * @param max The maximum value on  the slider
	 */
	public SliderPane(String titleText, int initVal, int min , int max){
		
		this.min = min;
		this.max = max;
		
		this.setAlignment(Pos.TOP_CENTER);
		this.setSpacing(10.0);
	
		//Create child components
		title = new Label(titleText);
		valueTitle = new Label("value");
		value = new TextField(initVal+"");
		slider = new Slider();
		slider.setOrientation(Orientation.VERTICAL);
		
		//Add them to the layout
		this.getChildren().add(title);
		this.getChildren().add(valueTitle);
		this.getChildren().add(value);
		this.getChildren().add(slider);
		
		//Apply CSS Styles
		title.getStyleClass().add("littlerLabel");
		valueTitle.getStyleClass().add("littlerLabel");
		value.getStyleClass().add("text_input");
		value.getStyleClass().add("text_input");

		
		//Set up slider
		slider.setMin(min);
		slider.setMax(max);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(max);
		slider.setMinorTickCount(5);
		slider.setBlockIncrement(10);

		slider.valueProperty().addListener(valueChangeListener);
		value.textProperty().addListener(textValueChangeListener);

	}//end constructor

	/**
	 * Setter for all of the observers of this class.
	 * @param obs the observer of a slide changing.
	 */
	public void setSliderWasChangedObserver(SliderWasChanged obs) {
		this.obs = obs;
	}

	/**
	 * Notifies all the observers of changes.
	 * @param value The new value that a user has changed the slide to.
	 */
	public void notifyObs(int value) {
		if(obs != null) {
			obs.sliderWasChanged(value, this);
		}
	}


	/**
	 * Set the value of the slider
	 * @param newValue the new value of the slider
	 */
	public void set(int newValue){
		value.setText(newValue+"");
		slider.setValue(newValue);

		notifyObs(newValue);
	
	}//end set
	
	/**Get the selected value on the slider**/
	public int getValue(){
		return (int) slider.getValue();
	}//end getValue

	/**Change listender for when the slider is changed**/
	ChangeListener<Number> valueChangeListener = new ChangeListener<Number>(){

		@Override
		public void changed(ObservableValue<? extends Number> observable,
							Number oldValue, Number newValue) {
			if(!oldValue.equals(newValue))
			   set(newValue.intValue());
	
		}//end changed
		
	};//end valueChangeListener

	/**The textView needs another listener because its
	 * value type is String.**/
	ChangeListener<String> textValueChangeListener = new ChangeListener<String>(){

		@Override
		public void changed(ObservableValue<? extends String> observable,
							String oldValue, String newValue) {
			if(!oldValue.equals(newValue)){
				try{
				  int n = Integer.parseInt(newValue);
				  set(n);
				}
				catch(NumberFormatException ex){
					//do nothing.
				}
		   
			}//end if	
		
		}	
	};

}//end SliderPanel
