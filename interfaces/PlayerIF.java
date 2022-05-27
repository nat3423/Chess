/**
 * A player interface that models a player for a game of Chess,
 * storing and giving access to that player's data.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
package interfaces;

/**
 * An interface that models a player for a game of Chess.
 */
public interface PlayerIF extends BlackAndWhiteIF {
    /**
     * sets the players email
     * @param email This player instance's email, as given by the player
     */
    public void setEmail(String email);

    /**
     * returns the players email
     * @return A String containing the player's email
     */
    public String getEmail();

    /**
     * Resets the players user id to a new value
     * @param userID The new user id String to set the players user id to
     */
    public void setID(String userID);

    /**
     * returns the players user id
     * @return A String containing the player's user id
     */
    public String getID();

    /**
     * returns the players first name
     * @return A String with players first name
     */
    public String getFirstName();
    /**
     * returns the players last name
     * @return A String with the players last name
     */
    public String getLastName();

    /**
     * Returns the count of the number of games this player has won
     * @return An Integer representing the number of games this player has won
     */
    public int getWinCount();

    /**
     * Returns the count of the number of games this player has tied with 
     * another player in.
     * @return An Integer representing the number games this player has tied
     */
    public int getDrawCount();

    /**
     * Returns the number of games this player has lost
     * @return An Integer representing the number of games this player lost
     */
    public int getLossCount();

    /**
     * Adds a given number to the current number of games this player has won
     * @param winsNum The number of wins to add to the win count
     */
    public void addWins(int winsNum);

    /**
     * Adds a given number to the current number of games this player has tied
     * with another player
     * @param drawNum The number of wins to add to the draw count
     */
    public void addDraws(int drawNum);

    /**
     * Adds a given number to the current number of games this player has lost
     * @param lossNum The number of wins to add to the loss count
     */
    public void addLosses(int lossNum);

    /**
     * Resets the password to a new password if the correct current password 
     * is given 
     * @param oldPassword A String value representing the old value to validate 
     *                    before resetting the password.
     * @param newPassword The new String value to set this players password to
     * @return A Boolean value representing whether the reset was successful or 
     *         not
     */
    public boolean setPassword(String oldPassword, String newPassword);

    /**
     * 
     * @param passwordInput The password to check agains this players password
     *                      to log in.
     * @return A boolean value representing if the passwordInput is the same
     *         as this players password
     */
    public boolean validatePassword(String passwordInput);


    /**
     * A function to reset all stats to 0 for a player
     */
    public void resetStats();
}
