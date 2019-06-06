package database;

import data.EmptyDatasetException;

public class EmptyTypeException extends Exception {
    public EmptyTypeException(){
        super("Query result is empty");
    }
}
