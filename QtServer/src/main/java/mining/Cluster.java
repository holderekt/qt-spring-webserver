package mining;

import data.Data;
import data.Tuple;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Cluster definito da una tupla centroide e
 * dagli indici delle tuple contenute in esso
 *
 * @author Ivan Diliso
 */
public class Cluster implements Iterable<Integer>, Comparable<Cluster>, Serializable {

	/**
	 * Tupla centroide
	 * (tupla dalla quale é calcolato il cluster in base al raggio)
	 */
	private Tuple centroid;

	/**
	 * Indici delle tuple contenute nel cluster
	 */
	private Set<Integer> clusteredData = new HashSet<Integer>();


	/**
	 * Costruttore, crea un nuovo cluster istanziando il centroide
	 *
	 * @param centroid Centroide del cluster
	 */
	public Cluster(Tuple centroid){
		this.centroid=centroid;
	}

	/**
	 * Restituisce il centroide del cluster
	 *
	 * @return centroid
	 */
	public Tuple getCentroid(){
		return centroid;
	}

	/**
	 * Inserisce un indice di tupla all'insieme di indici del cluster
	 *
	 * @param id Indice della tupla
	 * @return True se l'insieme non contiene l'elemento (pre inserimento), falso altrimenti
	 */
	public boolean addData(int id){
		return clusteredData.add(id);

	}

	/**
	 * Verifica se l'id in input é contenuto nel cluster
	 *
	 * @param id Indice di tupla
	 * @return Vero se contenuto, falso altrimenti
	 */
	public boolean contain(int id){
		return clusteredData.contains(id);
	}

	/**
	 * Rimuove una tupla dal cluster
	 *
	 * @param id Indice tupla da rimuovere
	 */
	public void removeTuple(int id){
		clusteredData.remove(id);

	}

	/**
	 * Restituisce il numero di tuple nel cluster
	 *
	 * @return Numero tuple nel cluster
	 */
	public int  getSize(){
		return clusteredData.size();
	}

	/**
	 * Restituisce un iteratore per le tuple nel cluster
	 *
	 * @return Iteratore
	 */
	public Iterator<Integer> iterator(){
		return clusteredData.iterator();
	}

	/**
	 * Restituisce il centroide in formato stringa
	 *
	 * @return str
	 */
	public String toString(){
		String str="Centroid=(";
		for(int i=0;i<centroid.getLength();i++)
			str+=centroid.get(i);
		str+=")";
		return str;
	}

	/**
	 * Restituisce il cluster in formato stringa
	 *
	 * @param data Insieme di tuple
	 * @return str
	 */
	public String toString(Data data){
		String str="Centroid=(";
		for(int i=0;i<centroid.getLength();i++)
			str+=centroid.get(i)+ " ";

		str+=")\nExamples:\n";
		Iterator<Integer>  array = clusteredData.iterator();

		while(array.hasNext()){

			int next = array.next();
			str+="[";
			for(int j=0;j<data.getSchema().size();j++)
				str+=data.getValue(next, j)+" ";
			str+="] dist="+getCentroid().getDistance(data.getItemSet(next))+"\n";
		}

		str+="\nAvgDistance="+getCentroid().avgDistance(data, clusteredData);
		return str;
	}

	/**
	 * Confronta due cluster
	 *
	 * @param cluster Cluster da confrontare
	 * @return 1 se il cluster corrente é più grande, -1 altrimenti
	 */
	@Override
	public int compareTo(Cluster cluster) {
		return (this.getSize() >cluster.getSize() ? 1 : -1);
	}

}
