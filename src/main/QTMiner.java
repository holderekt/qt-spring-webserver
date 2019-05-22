package main;

import cluster.ClusterSet;
import cluster.Tuple;
import cluster.*;

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
    public int compute(Data data){
        int numclusters = 0;
        boolean[] isClustered = new boolean[data.getNumberOfExplanatoryAttributes()];

        for(int i=0; i<isClustered.length; i++){
            isClustered[i] = false;
        }

        int countClustered = 0;

        while(countClustered!=data.getNumberOfExplanatoryAttributes()){
            Cluster c = buildCandidateCluster(data, isClustered);
            C.add(c);
            numclusters++;

            int[] clusteredTupleId = c.iterator();

            for(int i=0; i < clusteredTupleId.length; i++){
                isClustered[clusteredTupleId[i]] = true;
            }

            countClustered += c.getSize();
        }



        return numclusters;
    }

    public Cluster buildCandidateCluster(Data data, boolean[] isClustered){
        return new Cluster(new Tuple(3));

    }
}
