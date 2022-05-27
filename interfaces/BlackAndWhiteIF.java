/**
 * Used to model an object that can have a state of BLACK or WHITE.
 * Any class that needs a color state makes use of this interface.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
package interfaces;
import enums.GameColor;

/**
 * This interface models an object that can be Black or White.
 * This is done by utilizing the GameColor enum to hold a color
 * state BLACK or WHITE.
 */
public interface BlackAndWhiteIF {
    /**
     * Getter method for the color of the object.
     * @return The GameColor enum held by the object.
     */
    public GameColor getColor();

    /**
     * sets the color
     * @param color the color to set to
     */
    public void setColor(GameColor color);

    /**
     * Determines if the object is the color Black.
     * @return True if the object is black, false otherwise.
     */
    public boolean isBlack();
    
    /**
     * Determines if the object is the color White.
     * @return True if the object is white, false otherwise.
     */
    public boolean isWhite();
}
