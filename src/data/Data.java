package data;

import database.*;
import java.sql.SQLException;
import java.util.*;

public class Data {

    private List<Example> data;
    private int numberOfExamples;
    private List<Attribute> attributeSet;

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


    public int getNumberOfExplanatoryAttributes() {
        return numberOfExamples;
    }

    public List<Attribute> getSchema() {
        return attributeSet;
    }

    public Object getValue(int exampleIndex, int attributeIndex) {
        return data.get(exampleIndex).get(attributeIndex);
    }

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