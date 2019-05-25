package cluster;

import item.Item;
import qtminer.Data;

public class Tuple {
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

    public double avgDistance(Data data, int[] clusteredData){
        double p = 0.0, sumD = 0.0;

        for(int i=0; i<clusteredData.length; i++){
            double d = getDistance(data.getItemSet(clusteredData[i]));
            sumD += d;
        }

        p = sumD / clusteredData.length;

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
