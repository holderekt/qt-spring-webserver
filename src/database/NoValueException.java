package database;

public class NoValueException extends Exception {
    public NoValueException(){
        super("No values found in query result");
    }

    public NoValueException(String s){
        super(s);
    }
}
