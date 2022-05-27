/**
 * A parent class that is used to model an object that is either
 * Black or White. This is used a parent class that can be
 * extended for any child class that requires this functionality.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
package model;
import interfaces.*;
import enums.GameColor;

/**
 * Models an object that can be Black or White.
 * This is done by utilizing the GameColor enum to hold a color
 * state BLACK or WHITE.
 */
public class BlackAndWhite implements BlackAndWhiteIF {
    /** The color of the object */
    private GameColor color;

    /**
     * Constructor for the BlackAndWhite object.
     * @param color The color of the object.
     */
    public BlackAndWhite(GameColor color) {
        this.color = color;
    }
    
    /**
     * Getter method for the color of the object.
     * @return The GameColor enum held by the object.
     */
    public GameColor getColor() {
        return this.color;
    }

    /**
     * Setter method for the color of the object.
     * @param gc The color to set the object to.
     */
    @Override
    public void setColor(GameColor gc) {
        this.color = gc;
    }

    /**
     * Determines if the object is the color Black.
     * @return True if the object is black, false otherwise.
     */
    public boolean isBlack() {
        return this.color.toString().equals("Black");
    }

    /**
     * Determines if the object is the color White.
     * @return True if the object is white, false otherwise.
     */
    public boolean isWhite() {
        return this.color.toString().equals("White");
    }

    /**
     * Sets the object's color to black.
     * @param b Boolean that states whether the object is white.
     */
    public void setBlack(boolean b) {
        if(b) {
            setColor(GameColor.BLACK);
        }
    }

    /**
     * Sets the object's color to white.
     * @param b Boolean that states whether the object is black.
     */
    public void setWhite(boolean b) {
        if(b) {
            setColor(GameColor.WHITE);
        }
    }
}
