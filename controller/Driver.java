/**
 * This is a driver that runs the chess object
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
package controller;

import application.Main;
import javafx.application.Application;
import ui.UI_FactoryIF;
import uicli.CLI_Factory;
import java.util.Scanner;

public class Driver {
    /**
     * Main method that creates a chess object and runs it.
     * @param args Command line arguements
     */    
    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        System.out.print("Play CLI Chess (C) or GUI Chess (G)?> ");
        String response = input.next();


        //Prompts the user until they give valid input.
        while (!response.equals("C") && !response.equals("G")){
            System.out.print("Play CLI Chess (C) or GUI Chess (G)?> ");
            response = input.next();
        }

        //Starts chess, either with cla or gui.
        if (response.equals("C")) {
            UI_FactoryIF UIFactory = new CLI_Factory();
            Chess chess = new Chess(UIFactory);
            chess.mainMenu();
        } else {
            Application.launch(Main.class, "ChessMeister");
        }
    }
}
