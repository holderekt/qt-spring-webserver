package jsonconverter;
import data.Tuple;
import json.*;
import data.Data;
import mining.Cluster;
import mining.ClusterSet;;
import java.util.Iterator;
import java.util.List;

public class JSONConverter {
    public static String convert(Data data) throws JSONWrongTypeException {
        JSONObject dataobj = new JSONObject("data");
        for(int i=0; i<data.getSchema().size(); i++){
            JSONArray items = new JSONArray(data.getSchema().get(i).getName());
            for(Object item : data.getColumns(i)){
                items.addItem(JSONUtils.getJsonItem(item));
            }
            dataobj.addItem(items);
        }
        JSONObject finaljson = new JSONObject();
        finaljson.addItem(dataobj);
        return finaljson.render();
    }

    public static String convert(ClusterSet C, Data data){

        JSONObject finalobj = new JSONObject();
        JSONArray dataarray = new JSONArray("data");

        for(Cluster c : C){

            JSONObject centroidObject = new JSONObject();
            JSONArray centroidData = new JSONArray("centroid");

            for(int i=0; i<c.getCentroid().getLength(); i++){
                centroidData.addItem(new JSONString(c.getCentroid().get(i).getValue().toString()));
            }

            centroidObject.addItem(centroidData);
            centroidObject.addItem(new JSONTuple("averagedistance", new JSONValue(c.getAvgDistance(data))));


            JSONArray exampleArray = new JSONArray("examples");

            Iterator<Integer> iter = c.iterator();
            while(iter.hasNext()){

                Tuple tup = data.getItemSet(iter.next());

                JSONObject exampleData = new JSONObject();
                exampleData.addItem(new JSONTuple("r", new JSONValue(c.getCentroid().getDistance(tup))));

                JSONArray exampleList = new JSONArray("l");

                for(int i=0; i<tup.getLength(); i++){
                    exampleList.addItem(new JSONString(tup.get(i).getValue().toString()));
                }

                exampleData.addItem(exampleList);
                exampleArray.addItem(exampleData);
                centroidObject.addItem(exampleArray);
            }

            dataarray.addItem(centroidObject);
        }

        finalobj.addItem(dataarray);
        return finalobj.render();
    }

    public static String convert(List<Tuple> data) throws JSONWrongTypeException {

        JSONObject finalobj = new JSONObject();
        JSONObject dataobj = new JSONObject("data");

        if(data.size() != 0){
            int len = data.get(0).getLength();
            for(int i =0; i<len; i++){
                JSONArray examplearray = new JSONArray(data.get(0).get(i).getAttribute().getName());
                for(Tuple t : data){
                    examplearray.addItem(JSONUtils.getJsonItem(t.get(i).getValue()));
                }
                dataobj.addItem(examplearray);
            }
        }
        finalobj.addItem(dataobj);
        return finalobj.render();
    }
}
