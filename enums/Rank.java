/**
 * An enum used to model all the possible values a Rank
 * could be on a chess board, along with the corresponding row
 * values for each rank.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
package enums;

/**
 * This enum models the rank(row) on a chess board.
 */
public enum Rank {
    R1(1,0),
    R2(2,1),
    R3(3,2),
    R4(4,3),
    R5(5,4),
    R6(6,5),
    R7(7,6),
    R8(8,7); 

    /** The name of the rank */
    private final int name;
    /** The row number of the rank */
    private final int row;

    /**
     * Constructor for the different Rank enums.
     * @param name The name of the Rank.
     * @param row The row of the Rank. 
     */
    private Rank(int name, int row) {
        this.name = name;
        this.row = row;
    }

    /**
     * Getter method for the name of the Rank.
     * @return The name of the Rank.
     */
    public int getName(){
        return this.name;
    }

    /**
     * Getter method for the row of the Rank.
     * @return The row of the Rank.
     */
    public int getRow(){
        return this.row;
    }

    /**
     * Searches the given name for a corresponding Rank.
     * @param name The name to search for.
     * @return The Rank enum with the given name.
     */
    public static Rank searchName(int name){
        Rank result = null;
        for(Rank r : Rank.values()){
            if(r.getName() == name){
                result = r;
            }
        }
        return result;
    }

    /**
     * Searches the given row for a corresponding Rank.
     * @param row The row to search for.
     * @return The Rank enum with the given row.
     */
    public static Rank searchRow(int row){
        Rank result = null;
        for(Rank r : Rank.values()){
            if(r.getRow() == row){
                result = r;
            }
        }
        return result;
    }

    @Override
    /**
     * Returns the Rank as a String output.
     * @return The Rank enum as a String.
     */
    public String toString(){
        return this.name + "";
    }
}
