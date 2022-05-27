package application.colorScreen;

/**
 * An interface that changes the color squares of this object
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
public interface ColorChangerIF {

    /**
     * Sets the color of the white Square to a new value
     * @param color the new color to set the white squares
     */
    void setColorWhite(String color);

    /**
     * Sets the color of the black Square to a new value
     * @param color the new color to set the black squares
     */
    void setColorBlack(String color);

    /**
     * Gets a value finding if the color to change is for white squares
     * @return true if the color to change is for white.
     */
    boolean getChangeWhite();
}
