/**
 * A position class used to group a File enum and Rank enum 
 * together to respresent a specific point on the chess board.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
package model;
import enums.File;
import enums.Rank;

/**
 * Models a position on a chess board for location specification.
 */
public class Position {
    /** The rank of the position */
    private Rank rank;
    /** The file of the position */
    private File file;

    /**
     * Constructor for the Position object.
     * @param rank The rank of the position.
     * @param file The file of the position.
     */
    public Position(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    /**
     * Getter method for the rank of the position.
     * @return The Rank enum of the position.
     */
    public Rank getRank() {
        return this.rank;
    }

    /**
     * Getter method for the file of the position.
     * @return The File enum of the position.
     */
    public File getFile() {
        return this.file;
    }

    /**
     * Setter method for the file of the position.
     * @param file The File enum of the position to set to.
     */
    public void setFile(File file) {
        this.file = file;
    }

    @Override
    /**
     * Compares two Position objects.
     * @return True if they are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass() == this.getClass()) {
            final Position other = (Position) obj;
            if (this.rank == other.rank && this.file == other.file)
                return true;
        }
        return false;
    }

    /**
     * Returns the position as a String output.
     * @return The Position object as a String.
     */
    public String toString() {
        if (this.file != null) {
            return this.file.toString() + this.rank.toString();
        } else return this.rank.toString();
    }
}
