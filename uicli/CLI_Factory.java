package uicli;

import java.util.HashMap;

import interfaces.PlayerIF;
import interfaces.UI_Interface;
import ui.UI_FactoryIF;

/**
 * This class creates command line interfaces
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public class CLI_Factory  implements UI_FactoryIF{
	
	/**A singleton(ish) for the cli_message instance**/
	private UI_Interface<Boolean> cli_message_instance;
	
	/**
	 * Construct a command line version of the main menu.
	 * @return An instance of UI_Interface implementation that defines the main menu
	 */
	public UI_Interface<Integer> BuildMainOptions() {
		return new CLI_MainOptions();
	}
	
	
	/**
	 * Construct a command line version of the player log in screen.
	 * @param db A reference to the player log in Database.
	 * @param prompt A prompt to display in the UI
	 * @return An instance of the UI_Inferface implementation that defines a command line 
	 * log in screen.
	 */
	public UI_Interface<PlayerIF> buildLogInScreen(HashMap<String,PlayerIF>db,String prompt){
		return  new  CLI_PlayerLogIn(db,prompt);
	}

	
	/**
	 * Create a command line createAccount
	 * @return A newUI instance who's observer will receive a PlayerIF.
	 */
	public UI_Interface<PlayerIF> buildCreateAccount(){
		return new CLI_CreateAccount();
	}
	
	/**
	 * Construct a command line version of the UI control to enter the main menu.
	 * @param db
	 * @return UI_Interface
	 */
	public UI_Interface<Boolean> buildChangeUserID(HashMap<String,PlayerIF>db){
		return new CLI_ChangeUserID(db);
	}
	
	
	/**
	 * Build a new colorOptions UI for the command line.
	 * @return A new UI_Instance who's observer will receive a boolean.
	 */
	public UI_Interface<Boolean> buildColorOptions(){
		return new CLI_ColorOptions();
	}
	
	
	/**
	 * Build a new CLI component for displaying player info
	 * @param playerTurn The player who's turn it is.
	 * @param boardColor The color mode of the board.
	 * @param message The message to prompt to the user.
	 * @return A UI_Interface implementation that an observer can register with to listen for then the UI is done.
	 */
	public UI_Interface<Boolean> buildDisplayPlayer(PlayerIF playerTurn,String boardColor, String message){
		return new CLI_DisplayPlayer(playerTurn, boardColor, message);
	}
	
	
	/**
	 * Build a CLI component for displaying messages.
	 * @return A UI_Interface implementation for displaying messages on the CLI UI.
	 */
	public UI_Interface<Boolean> buildMessageDisplay(){
		if(cli_message_instance == null)
			this.cli_message_instance=  new CLI_Message("");
		
		return this.cli_message_instance;
	}
	
	
	/**
	 * Build a CLI UI control for taking input of position.
	 * @param prompt The prompt to display the user.
	 * @return
	 */
	public UI_Interface<String> buildPositionInput(String prompt){
		return new CLI_PositionInput(prompt);
	}
}//end class
