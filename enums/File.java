/**
 * An enum used to model all the possible values a File 
 * could be, along with the corresponding column values
 * for each file.
 * 
 * @author Nathaniel Welch
 * @author Nathan Jenkins
 * @author Melanney Orta
 * @author Christen Kangas
 * @version 05/25/2022
 */
package enums;

/**
 * This enum models the file(column) on a chess board.
 */
public enum File {
    A('a',0),
    B('b',1),
    C('c',2),
    D('d',3),
    E('e',4),
    F('f',5),
    G('g',6),
    H('h',7); 

    /** The name of the file */
    private final char name;
    /** The column number of the file */
    private final int column;

    /**
     * Constructor for the different File enums.
     * @param name The name of the File.
     * @param col The column of the File. 
     */
    private File(char name, int col) {
        this.name = name;
        this.column = col;
    }

    /**
     * Getter method for the name of the File.
     * @return The name of the File.
     */
    public char getName(){
        return this.name;
    }

    /**
     * Getter method for the column of the File.
     * @return The column of the File.
     */
    public int getColumn(){
        return this.column;
    }

    /**
     * Searches the given name for a corresponding File.
     * @param c The name to search for.
     * @return The File enum with the given name.
     */
    public static File searchName(char c){
        File result = null;
        for(File f : File.values()){
            if(f.getName() == c){
                result = f;
            }
        }
        return result;
    }

    /**
     * Searches the given column for a corresponding File.
     * @param col The column to search for.
     * @return The File enum with the given column.
     */
    public static File searchColumn(int col){
        File result = null;
        for(File f : File.values()){
            if(f.getColumn() == col){
                result = f;
            }
        }
        return result;
    }

    @Override
    /**
     * Returns the File name as a String output.
     * @return The File enum as a String.
     */
    public String toString(){
        return this.name + "";
    }
}
