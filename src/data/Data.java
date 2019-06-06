package data;

import database.*;
import java.sql.SQLException;
import java.util.*;

public class Data {
    private List<Example> data = new ArrayList<>();
    private int numberOfExamples;
    private List<Attribute> attributeSet = new LinkedList<>();

    public Data(String tablename) throws SQLException, EmptyTypeException, NoValueException, DatabaseConnectionException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        DbAccess db = new DbAccess();
        db.initConnection();
        TableData table = new TableData(db);
        data = table.getDistinctTransazioni(tablename);
        numberOfExamples = data.size();


        TableSchema tb = new TableSchema(db,tablename);

        for(int i=0;i< tb.getNumberOfAttributes();i++){
            String attributeName = tb.getColumn(i).getColumnName();
            if(tb.getColumn(i).isNumber()){
                // Caso Numerico
                Float min = (Float)table.getAggregateColumnValue(tablename,tb.getColumn(i), QUERY_TYPE.MIN);
                Float max = (Float)table.getAggregateColumnValue(tablename,tb.getColumn(i), QUERY_TYPE.MAX);

                attributeSet.add(new ContinuousAttribute(attributeName,i,min,max));

            }else{
                // Caso Stringa
                Set<Object> values_ob = table.getDistinctColumnValues(tablename,tb.getColumn(i));
                String[] elements = new String[values_ob.size()];
                Iterator iter = values_ob.iterator();
                int j = 0;
                while(iter.hasNext()){
                    elements[j] = (String)iter.next().toString();
                    j++;
                }

                attributeSet.add(new DiscreteAttribute(attributeName, i, elements));
            }
        }
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