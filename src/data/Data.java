package data;

import database.*;
import java.sql.SQLException;
import java.util.*;

/**
 *  Dataset, tabella contenente un inseme di colonne ognuna definita
 *  da un attributo
 *
 * @author Ivan Diliso
 */
public class Data {

    /**
     * Insieme di righe che formano la tabell
     */
    private List<Example> data;

    /**
     * Numero di righe presenti
     */
    private int numberOfExamples;

    /**
     * Insieme di attributi dei valori del dataset
     */
    private List<Attribute> attributeSet;


    /**
     * Costruttore, costruisce la tabella caricando i dati da
     * una tabella su database definita da "tablename"
     *
     * @param tablename Nome della tabella sul database
     * @throws DatabaseConnectionException Errore nella connessione al database
     * @throws SQLException Errore esecuzione query
     * @throws EmptyTypeException Ricevuto un resultset vuoto
     * @throws NoValueException Ricevuto un resulset vuoto durante l'esecuzione operatori sql di aggregazione
     */
    public Data(String tablename) throws DatabaseConnectionException, SQLException, EmptyTypeException, NoValueException {

        attributeSet = new LinkedList<>();

        DbAccess db = new DbAccess();
        db.initConnection();

        TableData table = new TableData(db);
        TableSchema table_schema = new TableSchema(db,tablename);

        data = table.getDistinctTransazioni(tablename);

        for(int i=0;i< table_schema.getNumberOfAttributes();i++){

            String attributeName = table_schema.getColumn(i).getColumnName();

            if(table_schema.getColumn(i).isNumber()){

                Float min = (Float)table.getAggregateColumnValue(tablename,table_schema.getColumn(i), QUERY_TYPE.MIN);
                Float max = (Float)table.getAggregateColumnValue(tablename,table_schema.getColumn(i), QUERY_TYPE.MAX);

                attributeSet.add(new ContinuousAttribute(attributeName,i,min,max));

            }else{

                Set<Object> columns_values = table.getDistinctColumnValues(tablename,table_schema.getColumn(i));
                String[] elements = new String[columns_values.size()];
                Iterator<Object> iter = columns_values.iterator();

                for(int j=0; j!=columns_values.size(); j++){
                    elements[j] = (String)iter.next();
                }

                attributeSet.add(new DiscreteAttribute(attributeName, i, elements));
            }
        }

        numberOfExamples = data.size();
    }


    /**
     * Restituisce il numero di examples (numero righe)
     *
     * @return numberOfExamples
     */
    public int getNumberOfExplanatoryAttributes() {
        return numberOfExamples;
    }

    /**
     * Restituisce l'insieme degli attributi del dataset
     *
     * @return attributeSets
     */
    public List<Attribute> getSchema() {
        return attributeSet;
    }

    /**
     * Restituisce valore contenuto nella riga indicata da exampleIndex e dalla
     * colonna indicate da attributeIndex
     *
     * @param exampleIndex Indice riga
     * @param attributeIndex Indice colonna
     * @return data.get(exampleIndex).get(attributeIndex)
     */
    public Object getValue(int exampleIndex, int attributeIndex) {
        return data.get(exampleIndex).get(attributeIndex);
    }

    /**
     * Restituisce il dataset in formato stringa
     *
     * @return result
     */
    public String toString() {
        String result = "";

        for (Attribute at : attributeSet) {
            result += at.getName() + ", ";
        }
        result += "\n";

        for (int i = 0; i != getNumberOfExplanatoryAttributes(); i++) {
            result += i + ".";
            for (int j = 0; j != attributeSet.size(); j++) {
                result += (String)data.get(i).get(j).toString()  + ", ";
            }
            result += "\n";
        }

        return result;
    }

    /**
     * Restituisce la riga del dataset indicata dalla posizione index
     * sotto forma di tupla
     *
     * @param index Indice della riga
     * @return tuple
     */
    public Tuple getItemSet(int index){
        Tuple tuple = new Tuple(attributeSet.size());

        for(int i = 0; i!= attributeSet.size(); i++){
            Object o = attributeSet.get(i);
            if(o instanceof DiscreteAttribute){
                tuple.add(new DiscreteItem((DiscreteAttribute)attributeSet.get(i), (String)data.get(index).get(i)), i);
            }else if( o instanceof  ContinuousAttribute){
                tuple.add(new ContinuousItem((ContinuousAttribute)attributeSet.get(i), (Double)data.get(index).get(i)), i);
            }
        }

        return tuple;
    }

}