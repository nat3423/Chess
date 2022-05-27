/**
 * An enum used to model all the possible values a color
 * could be on a chess board, which is either Black or White.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
package enums;

/**
 * This enum models the color of an object.
 */
public enum GameColor {
    BLACK("Black"),
    WHITE("White");

    /** The name of the color */
    private final String name;

    /**
     * Constructor for the different GameColor enums.
     * @param name The name of the color.
     */
    private GameColor(String name) {
        this.name = name;
    }

    @Override
    /**
     * Returns the enum as a String output.
     * @return The enum name as a String.
     */
    public String toString() {
        return this.name;
    }
}
