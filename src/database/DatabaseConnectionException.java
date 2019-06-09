package database;

public class DatabaseConnectionException extends Exception {
    public DatabaseConnectionException(){
        super("Database Connection Failed");
    }

    public DatabaseConnectionException(String message){
        super(message);
    }
}
