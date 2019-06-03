package mining;
import data.Data;

public class ClusterSet {
    private Cluster C[] = new Cluster[0];
    private int len = 0;

    public ClusterSet(){}

    public void add(Cluster c){
        Cluster tempC[] = new Cluster[C.length + 1];
        for(int i = 0; i != C.length; i++){
            tempC[i] = C[i];
        }

        tempC[C.length] = c;
        C = tempC;
        len++;
    }

    public int length(){
        return len;
    }

    public Cluster get(int i){
        return C[i];
    }

    public String toString(){
        String result = "";
        for(Cluster c :  C){
            result += c.toString() + "\n";
        }

        return result;
    }

    public String toString(Data data){
        String str = "";

        for(int i =0; i< C.length; i++){
            if(C[i] != null){
                str+= i+":"+C[i].toString(data)+"\n";
            }
        }

        return str;
    }



}
