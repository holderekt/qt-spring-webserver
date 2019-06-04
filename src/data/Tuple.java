package data;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

public class Tuple implements Serializable {
    private Item[] tuple;

    public Tuple(int size){
        tuple = new Item[size];
    }

    public int getLength(){
        return tuple.length;
    }

    public Item get(int i){
        return tuple[i];
    }

    public void add(Item c, int i){
        tuple[i] = c;
    }

    public double getDistance(Tuple obj){

        double total = 0;
        for(int i = 0; i!= tuple.length; i++){
            total += this.get(i).distance(obj.get(i));
        }

        return total;
    }

    public double avgDistance(Data data, Set<Integer> clusteredData){
        double p = 0.0, sumD = 0.0;

        Iterator<Integer> elem = clusteredData.iterator();
        while(elem.hasNext()){
            double d = getDistance(data.getItemSet((Integer)elem.next()));
            sumD += d;
        }

        p = sumD / clusteredData.size();
        return p;
    }

    public String toString(){
        String result = "[";
        for(Item i : tuple){
            result += i.toString() + ",";
        }
        result += "]";

        return result;
    }
}
