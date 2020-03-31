package database;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class TableNames{
    /**
     *  Tool accesso al database
     */
    DbAccess db;

    List<String> tableNames = new LinkedList<>();

    /**
     * Costruttore, crea una nuovo TableNames
     *
     * @param db Tool per l'accesso al database
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

        System.out.println(tableNames);
    }

    public String[] getTableNames(){
        return tableNames.stream().toArray(String[]::new);
    }

}
