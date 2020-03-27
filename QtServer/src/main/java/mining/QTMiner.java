package mining;

import data.Data;
import data.EmptyDatasetException;
import data.Tuple;

import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 *  Tool per l'esecuzione del clustering QT
 */
public class QTMiner {

    /**
     * Insieme di cluster
     */
    ClusterSet C;

    /**
     * Raggio clustering
     */
    double radius;

    /**
     * Indica se il clustering é stato caricato da file
     */
    boolean loadedFromFile;


    /**
     * Costruttore inizializza l'insieme di cluster e il raggio
     *
     * @param radius Raggio clustering
     */
    public QTMiner(double radius){
        C = new ClusterSet();
        this.radius = radius;
        this.loadedFromFile = false;
    }

    /**
     * Carica il risultato del clustering da un file sul sistema
     *
     * @param filename Nome del file
     * @throws FileNotFoundException File non trovato
     * @throws IOException Errore lettura file
     * @throws ClassNotFoundException Classe serializzata sul file non trovata (Non compatibile con clusterset)
     */
    public QTMiner(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream inFile = new FileInputStream(filename);
        ObjectInputStream inStream = new ObjectInputStream(inFile);
        C = (ClusterSet)inStream.readObject();
        inStream.close();
        inFile.close();
        this.loadedFromFile = true;
    }

    /**
     * Salva il risultato del clustering su un file
     *
     * @param filename Nome del file
     * @throws FileNotFoundException File inesistente o inaccessibile
     * @throws IOException Errore nella scrittura su file
     */
    public void salva(String filename) throws  FileNotFoundException, IOException {
        FileOutputStream outFile = new FileOutputStream(filename);
        ObjectOutputStream outStream = new ObjectOutputStream(outFile);
        outStream.writeObject(C);
        outStream.close();
        outFile.close();
    }

    /**
     * Restituisce l'insieme dei cluster
     *
     * @return C
     */
    public ClusterSet getC(){
        return C;
    }

    /**
     * Restituisce se il cluster é stato caricato da file
     *
     * @return Vero se caricato da file, falso altrimenti
     */
    public boolean isLoadedFromFile() {
        return loadedFromFile;
    }

    //TODO Completare implementazione

    /**
     * Effettua il clustering QT su un dataset in input
     *
     * @param data Insieme di tuple
     * @return Numero di cluster trovati
     * @throws ClusteringRadiusException Numero di cluster trovati é minore o uguale a 1
     * @throws EmptyDatasetException Dataset vuoto
     */
    public int compute(Data data)throws ClusteringRadiusException, EmptyDatasetException {
        int numclusters = 0;

        if(!(data.getNumberOfExplanatoryAttributes() > 0 && data.getSchema().size() > 0)){
            throw new EmptyDatasetException();
        }

        boolean[] isClustered = new boolean[data.getNumberOfExplanatoryAttributes()];

        for(int i=0; i<isClustered.length; i++){
            isClustered[i] = false;
        }

        int countClustered = 0;

        while(countClustered!=data.getNumberOfExplanatoryAttributes()){
            Cluster c = buildCandidateCluster(data, isClustered);
            C.add(c);
            numclusters++;

            Iterator<Integer> clusteredTupleId = c.iterator();

            while(clusteredTupleId.hasNext()){
                isClustered[clusteredTupleId.next()] = true;
            }

            countClustered += c.getSize();
        }

        if(numclusters <= 1){
            throw new ClusteringRadiusException();
        }

        return numclusters;
    }

    /**
     * Calcola e ricerca il cluster più popoloso
     * tra le tuple non ancora in un cluster
     *
     * @param data Insieme di tuple
     * @param isClustered Indica se la tupla all'inidce x é già contenuta in un cluster
     * @return max
     */
    public Cluster buildCandidateCluster(Data data, boolean[] isClustered) {

        ClusterSet temp = new ClusterSet();

        for(int i =0; i!= data.getNumberOfExplanatoryAttributes(); i++){
            if(!isClustered[i]){
                Cluster clu = new Cluster(data.getItemSet(i));

                for(int j = 0; j!= data.getNumberOfExplanatoryAttributes(); j++){
                    double distance = clu.getCentroid().getDistance(data.getItemSet(j));

                    if(!isClustered[j])
                        if (distance <= radius) {
                            clu.addData(j);
                        }
                }

                temp.add(clu);

            }
        }

        // RICERCA MAX

        Iterator<Cluster> iterclu = temp.iterator();

        Cluster max = temp.iterator().next();

        while(iterclu.hasNext()){
            Cluster next = iterclu.next();
            if(next.getSize() >= max.getSize()){
                max = next;
            }
        }


        Iterator<Integer> iter = max.iterator();

        while(iter.hasNext()){
            isClustered[iter.next()] = true;
        }


        return max;
    }

    /**
     * Restituisce l'insieme di cluster in fomato stringa
     *
     * @param data Insieme di tuple
     * @return Insieme di cluster in formato stringa
     */
    public String toString(Data data){
        return C.toString(data);
    }

    /**
     * Restituisce l'insieme di centroidi in formato stringa
     *
     * @return Insieme di cluster in formato stringa
     */
    public String toString(){
        return C.toString();
    }

    /**
     * Restituisce l'insieme di centroidi dei cluster trovati
     *
     * @return Centroidi dei cluster
     */
    public List<Tuple> getCentroids(){
        return C.getCentroids();
    }
}
