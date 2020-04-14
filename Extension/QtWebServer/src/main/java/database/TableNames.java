package database;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Contiene i nomi delle tabelle del database
 */
public class TableNames{
    /**
     *  Tool accesso al database
     */
    DbAccess db;

    /**
     * Elenco delle tabelle del database
     */
    List<String> tableNames = new LinkedList<>();

    /**
     * Costruttore, crea una nuovo TableNames
     *
     * @param db Tool per l'accesso al database
     * @throws SQLException Errore caricamento nomi tabelle dal database
     */
    public TableNames(DbAccess db) throws SQLException {

        this.db=db;
        Connection con =db.getConnection();
        DatabaseMetaData md = con.getMetaData();
        ResultSet rs = md.getTables(null, null, "%", new String[]{
                "TABLE"
        });
        while (rs.next()) {
            tableNames.add(rs.getString(3));
        }
    }

    /**
     * Restituisce i nomi delle tabelle
     * @return Array contenente i nomi delle tabelle
     */
    public String[] getTableNames(){
        return tableNames.stream().toArray(String[]::new);
    }

}
