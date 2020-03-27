package mining;

import data.Data;
import data.Tuple;

import java.io.Serializable;
import java.util.*;

/**
 * Insieme di Cluser
 *
 * @author Ivan Diliso
 */
public class ClusterSet implements Iterable<Cluster>, Serializable  {

    // TODO risolvere questa ambiguità
    /**
     * Insieme di cluster
     */
    private Set<Cluster> C = new TreeSet<>();

    /**
     * Numero di cluster contenuti
     */
    private int len = 0;

    /**
     * Costruttore
     */
    public ClusterSet(){}

    /**
     * Aggiunge un cluster all'insieme
     *
     * @param c Cluster da aggiungere
     */
    public void add(Cluster c){
        C.add(c);
        len++;
    }


    /**
     * Restituisce l'insieme dei centroidi in formato stringa
     *
     * @return result
     */
    public String toString(){
        String result = "";
        for(Cluster c :  C){
            result += c.toString() + "\n";
        }

        return result;
    }

    /**
     * Restituisce l'insieme dei cluster in formato stringa
     *
     * @param data Insieme delle tuple
     * @return str
     */
    public String toString(Data data){
        String str = "";

        Iterator<Cluster> iter = C.iterator();

        while(iter.hasNext()){
            str+= "1" + ":"+iter.next().toString(data)+"\n";
        }

        return str;
    }


    /**
     * Restituisce l'iteratore dell'insieme di cluster
     *
     * @return Iteratore dell'insieme di cluster
     */
    @Override
    public Iterator<Cluster> iterator() {
        return C.iterator();
    }

    /**
     * Restituisce i centroidi dell'insieme di cluster
     *
     * @return centroids
     */
    public List<Tuple> getCentroids(){
        List<Tuple> centroids = new ArrayList<>();

        for(Cluster c : C){
            centroids.add(c.getCentroid());
        }

        return centroids;
    }

}
