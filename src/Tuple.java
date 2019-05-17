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
        double totalDistance = 0;
        int maxPosition;

        if(this.getLength() <= obj.getLength()){
            maxPosition = this.getLength();
        }else{
            maxPosition = obj.getLength();
        }

        for(int i = 0; i!= maxPosition; i++){
            totalDistance += this.get(i).distance(obj.get(i));
        }

        return totalDistance;
    }

    public double avgDistance(Data data, int clusteredData[]){
        double avgDistacnce = 0;
        return avgDistacnce;
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
