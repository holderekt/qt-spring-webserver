package database;

/**
 * Eccezzione
 * Nessun valore presente all'interno del risultato di una query
 */
public class NoValueException extends Exception {
    public NoValueException(){
        super("No values found in query result");
    }

    public NoValueException(String s){
        super(s);
    }
}
