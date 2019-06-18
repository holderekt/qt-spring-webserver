package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Tool accesso al database
 *
 * @author Ivan Diliso
 */
public class DbAccess {

    /**
     * Nome driver
     */
    private String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    /**
     * Nome dbms
     */
    private final String DBMS = "jdbc:mysql";

    /**
     * Indirizzo server
     */
    private final String SERVER = "localhost";

    /**
     * Nome database
     */
    private final String DATABASE = "MapDB";

    /**
     * Porta connessione
     */
    private final String PORT= "3306";

    /**
     * Nome utente database
     */
    private final String USER_ID = "MapUser";

    /**
     * Password utente database
     */
    private final String PASSWORD = "map";

    /**
     * Connessione al database
     */
    private Connection conn;


    /**
     * Inizializza la connessione al database
     *
     * @throws DatabaseConnectionException Errore connessione al database
     */
    public void initConnection() throws DatabaseConnectionException {

        // Load MYSQL Driver
        try{
            Class.forName(DRIVER_CLASS_NAME).newInstance();
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException e){
            throw new DatabaseConnectionException("MYSQL Driver not found");
        }

        String CONNECTION_URL = DBMS + "://" + SERVER + ":" + PORT + "/" + DATABASE;
        String CREDENTIALS = "user=" + USER_ID + "&password=" + PASSWORD;

        // Database Connection
        try{
            conn = DriverManager.getConnection(CONNECTION_URL + "?" + CREDENTIALS + "&serverTimezone=UTC");
        }catch(SQLException e){
            throw  new DatabaseConnectionException("Can't connect to MYSQL Database");
        }
    }

    /**
     * Restituisce la connessione al database
     *
     * @return conn
     */
    public Connection getConnection(){
        return conn;
    }

    /**
     * Chiude la connessione al database
     */
    public void closeConnection() {
        try{
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Can't close database connection");
        }
    }
}
