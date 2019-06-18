package database;

/**
 * Eccezzione
 * Restituito un resultset vuoto
 */
public class EmptyTypeException extends Exception {
    public EmptyTypeException(){
        super("Query result is empty");
    }
}
