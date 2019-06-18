package data;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;


/**
 * Riga di una tabella, insieme di item
 *
 * @author Ivan Diliso
 */
public class Tuple implements Serializable {

    /**
     * Insieme di item
     */
    private Item[] tuple;


    /**
     * Costruttore, crea una nuova tupla della dimensione in input
     *
     * @param size Dimensione della tupla
     */
    public Tuple(int size){
        tuple = new Item[size];
    }

    /**
     * Restituisce la lunghezza della tupla
     *
     * @return tuple.length
     */
    public int getLength(){
        return tuple.length;
    }

    /**
     * Restituisce l'elemento in posizione i della tupla
     *
     * @param i Posizione dell'elemento
     * @return tuple[i] Elemento in posizione i
     */
    public Item get(int i){
        return tuple[i];
    }

    /**
     * Inserisce nella tupla l'item in ingresso nella posizione in ingresso
     *
     * @param c Item da aggiungere
     * @param i Posizione
     */
    public void add(Item c, int i){
        tuple[i] = c;
    }

    /**
     * Restituisce la distanza totale tra gli item della tupla corrente
     * e quelli della tupla in ingresso
     *
     * @param obj Tupla dalla quale calcolare la distanza
     * @return total - Distanza totale
     */
    public double getDistance(Tuple obj){

        double total = 0;

        for(int i = 0; i!= tuple.length; i++){
            total += this.get(i).distance(obj.get(i));
        }

        return total;
    }

    /**
     * Restituisce la media delle distanze tra la tupla corrente e
     * l'insieme delle tuple contenute in data  definito dagli indici
     * di clusteredData
     *
     * @param data Data contenente le tuple
     * @param clusteredData Insieme degli indici
     * @return p - Media delle distanze
     */
    public double avgDistance(Data data, Set<Integer> clusteredData){
        double p, sumD = 0.0;

        Iterator<Integer> elem = clusteredData.iterator();

        while(elem.hasNext()){
            double d = getDistance(data.getItemSet(elem.next()));
            sumD += d;
        }

        p = sumD / clusteredData.size();
        return p;
    }

    /**
     * Restituisce la tupla in formato stringa
     *
     * @return result
     */
    public String toString(){
        String result = "[";

        for(Item i : tuple){
            result += i.toString() + ",";
        }
        result += "]";

        return result;
    }
}
