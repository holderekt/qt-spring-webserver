package mining;
import data.Data;

import java.io.Serializable;
import java.util.*;

public class ClusterSet implements Iterable<Cluster>, Serializable  {
    // TODO risolvere questa ambiguità
    private Set<Cluster> C = new TreeSet<>();
    private int len = 0;

    public ClusterSet(){}

    public void add(Cluster c){
        C.add(c);
        len++;
    }

    public int length(){
        return len;
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

        Iterator<Cluster> iter = C.iterator();

        while(iter.hasNext()){
            str+= "1" + ":"+iter.next().toString(data)+"\n";
        }

        return str;
    }


    @Override
    public Iterator<Cluster> iterator() {
        return C.iterator();
    }
}
