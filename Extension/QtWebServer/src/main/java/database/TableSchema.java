package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Schema di un tabella MySQL
 */
public class TableSchema {

	/**
	 * Database Access Tool
	 */
	DbAccess db;

	/**
	 * Colonna di una tabella
	 */
	public class Column{

		/**
		 * Nome degli elementi della colonna
		 */
		private String name;

		/**
		 * Tipo degli elemeni della colonna
		 */
		private String type;

		/**
		 * Costruttore, crea un nuova colonna
		 * @param name Nome della colonna
		 * @param type Nome del tipo elementi colonna
		 */
		Column(String name,String type){
			this.name=name;
			this.type=type;
		}

		/**
		 * Restituisce il nome della colonna
		 *
		 * @return name
		 */
		public String getColumnName(){
			return name;
		}

		/**
		 * Restituisce se il tipo di colonna é numerico
		 *
		 * @return True se numerico, False altrimenti
		 */
		public boolean isNumber(){
			return type.equals("number");
		}

		/**
		 * Restituisce la colonna in formato stringa
		 *
		 * @return name + ":" + type
		 */
		public String toString(){
			return name+":"+type;
		}
	}

	/**
	 * Insieme di colonne
	 */
	List<Column> tableSchema=new ArrayList<Column>();


	/**
	 * Ricava lo schema delle colonne di una tabella MySQL
	 *
	 * @param db Database da utilizzare
	 * @param tableName Nome della tabella
	 * @throws SQLException Errore esecuzione query per metadata
	 */
	public TableSchema(DbAccess db, String tableName) throws SQLException{
		this.db=db;
		HashMap<String,String> mapSQL_JAVATypes=new HashMap<String, String>();
		mapSQL_JAVATypes.put("CHAR","string");
		mapSQL_JAVATypes.put("VARCHAR","string");
		mapSQL_JAVATypes.put("LONGVARCHAR","string");
		mapSQL_JAVATypes.put("BIT","string");
		mapSQL_JAVATypes.put("SHORT","number");
		mapSQL_JAVATypes.put("INT","number");
		mapSQL_JAVATypes.put("LONG","number");
		mapSQL_JAVATypes.put("FLOAT","number");
		mapSQL_JAVATypes.put("DOUBLE","number");
		
		 Connection con=db.getConnection();
		 DatabaseMetaData meta = con.getMetaData();
	     ResultSet res = meta.getColumns(null, null, tableName, null);
		   
	     while (res.next()) {
	         
	         if(mapSQL_JAVATypes.containsKey(res.getString("TYPE_NAME")))
	        		 tableSchema.add(new Column(
	        				 res.getString("COLUMN_NAME"),
	        				 mapSQL_JAVATypes.get(res.getString("TYPE_NAME")))
	        				 );
	      }
	      res.close();
	}

	/**
	 * Restituisce il numero di colonne nella tabella
	 *
	 * @return tableSchema.size()
	 */
	public int getNumberOfAttributes(){
		return tableSchema.size();
	}

	/**
	 * Restituisce la colonna nella posizione index
	 *
	 * @param index Indice posizione colonna
	 * @return tableSchema.get(index)
	 */
	public Column getColumn(int index){
		return tableSchema.get(index);
	}
}

