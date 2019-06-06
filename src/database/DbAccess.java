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


    public void initConnection() throws DatabaseConnectionException, ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        Class.forName(DRIVER_CLASS_NAME);
        String CONNECTION_URL = DBMS + "://" + SERVER + ":" + PORT + "/" + DATABASE;
        String CREDENTIALS = "user=" + USER_ID + "&password=" + PASSWORD;

        System.out.println(CONNECTION_URL + "?" + CREDENTIALS);

        try{
            conn = DriverManager.getConnection(CONNECTION_URL + "?" + CREDENTIALS + "&serverTimezone=UTC");
        }catch(Exception e){
            e.printStackTrace();
            throw  new DatabaseConnectionException();
        }
    }

    public Connection getConnection(){
        return conn;
    }

    public void closeConnection() throws SQLException {
        conn.close();
    }
}
