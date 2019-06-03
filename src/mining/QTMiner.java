package mining;

import data.Data;

import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Iterator;

public class QTMiner {

    ClusterSet C;
    double radius;

    public QTMiner(double radius){
        C = new ClusterSet();
        this.radius = radius;
    }

    public ClusterSet getC(){
        return C;
    }

    //TODO Completare implementazione
    public int compute(Data data) throws IOException, ClusteringRadiusException, EmptyDatasetException {
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

    public Cluster buildCandidateCluster(Data data, boolean[] isClustered) throws IOException {

        ClusterSet temp = new ClusterSet();

        for(int i =0; i!= data.getNumberOfExplanatoryAttributes(); i++){
            if(!isClustered[i]){
                // Aggiungo un cluster con centroide su i
                Cluster clu = new Cluster(data.getItemSet(i));
                //System.out.println("-------");
                //System.out.println("Centroid " + clu.getCentroid());

                for(int j = 0; j!= data.getNumberOfExplanatoryAttributes(); j++){
                    if(!isClustered[j]){
                       //System.out.println("Tuple" + data.getItemSet(j) + " " + clu.getCentroid().getDistance(data.getItemSet(j)));

                        if(clu.getCentroid().getDistance(data.getItemSet(j)) <= radius){

                            clu.addData(j);
                        }
                    }
                }

                //Iterator<Integer> cc = clu.iterator();
                //while(cc.hasNext()){
                  //  System.out.print(cc.next() + " ");
                //}
                //System.in.read();

                //System.out.println(clu.toString(data));
                //final int read = System.in.read();

                //System.out.println("\n\n");
                //System.out.println(clu.toString(data));
                //System.in.read();
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

        System.in.read();

        Iterator<Integer> iter = max.iterator();

        while(iter.hasNext()){
            isClustered[iter.next()] = true;
        }

        System.out.println(max);
        System.in.read();

        return max;
    }
}
