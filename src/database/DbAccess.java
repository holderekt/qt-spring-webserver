package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbAccess {
    private String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    private final String DBMS = "jdbc:mysql";
    private final String SERVER = "localhost";
    private final String DATABASE = "MapDB";
    private final String PORT= "3306";
    private final String USER_ID = "MapUser";
    private final String PASSWORD = "map";
    private Connection conn;


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


    public Connection getConnection(){
        return conn;
    }

    public void closeConnection() {
        try{
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Can't close database connection");
        }
    }
}
