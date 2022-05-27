/**
 * A player class to model player data including
 * personal data such as name, password, and email,
 * as well as game statistics for that player such as
 * the winCount, drawCount, and lossCount;
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
package model;
import enums.GameColor;
import interfaces.PlayerIF;

public class Player extends BlackAndWhite implements PlayerIF {
    /** The first name of the player */
    private String firstName;
    /** The last name of the player */
    private String lastName;
    /** The user's chosen ID */
    private String userID;
    /** The user's chosen password*/
    private String password;
    /** The user's email */
    private String email;
    /** The number of games this player has won */
    private int    winCount;
    /** The number of games this player has played in that resulted in a draw */
    private int    drawCount;
    /** The number of games this player has lost */
    private int    lossCount;

    /**
     * Player constructor initialize the name, userid, password, and player
     * stats for this player instance.
     * @param first The first name of this player
     * @param last The last name of this player
     * @param id The user id or username the player chose
     * @param password The password the player chose
     */
    public Player(String first, String last, String id, String password){
        super(GameColor.BLACK);
        this.firstName = first;
        this.lastName  = last;
        this.userID    = id;
        this.password  = password;
        this.winCount  = 0;
        this.drawCount = 0;
        this.lossCount = 0;
    }

    /**
     * sets the players email
     * @param email This player instance's email, as given by the player
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * returns the players email
     * @return A String containing the player's email
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * Resets the players user id to a new value
     * @param userID The new user id String to set the players user id to
     */
    public void setID(String userID){
        this.userID = userID;
    }

    /**
     * returns the players user id
     * @return A String containing the player's user id
     */
    public String getID(){
        return this.userID;
    }

    /**
     * returns the players first name
     * @return A String with players first name
     */
    public String getFirstName(){
        return this.firstName;
    } 

    /**
     * returns the players last name
     * @return A String with the players last name
     */
    public String getLastName(){
        return this.lastName;
    }

    /**
     * Returns the count of the number of games this player has won
     * @return An Integer representing the number of games this player has won
     */
    public int getWinCount(){
        return this.winCount;
    } 

    /**
     * Returns the count of the number of games this player has tied with 
     * another player in.
     * @return An Integer representing the number games this player has tied
     */
    public int getDrawCount(){
        return this.drawCount;
    }

    /**
     * Returns the number of games this player has lost
     * @return An Integer representing the number of games this player lost
     */
    public int getLossCount(){
        return this.lossCount;
    }

    /**
     * Adds a given number to the current number of games this player has won
     * @param winsNum The number of wins to add to the win count
     */
    public void addWins(int winsNum){
        this.winCount += winsNum;
    }

    /**
     * Adds a given number to the current number of games this player has tied
     * with another player
     * @param drawNum The number of wins to add to the draw count
     */
    public void addDraws(int drawNum){
        this.drawCount += drawNum;
    }

    /**
     * Adds a given number to the current number of games this player has lost
     * @param lossNum The number of wins to add to the loss count
     */
    public void addLosses(int lossNum){
        this.lossCount += lossNum;
    }

    /**
     * Resets the password to a new password if the correct current password 
     * is given 
     * @param oldPassword A String value representing the old value to validate 
     *                    before resetting the password.
     * @param newPassword The new String value to set this players password to
     * @return A Boolean value representing whether the reset was successful or 
     *         not
     */
    public boolean setPassword(String oldPassword, String newPassword){
        if(this.validatePassword(oldPassword)){
            this.password = newPassword;
            return true;
        }
        return false;
    }

    /**
     * 
     * @param passwordInput The password to check agains this players password
     *                      to log in.
     * @return A boolean value representing if the passwordInput is the same
     *         as this players password
     */
    public boolean validatePassword(String passwordInput){
        return passwordInput.equals(this.password);
    }

    /**
     * resets all this players stats
     */
    public void resetStats(){
        this.drawCount = 0;
        this.winCount = 0;
        this.lossCount = 0;
    }
}
