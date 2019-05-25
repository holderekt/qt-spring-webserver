package main;

import cluster.ClusterSet;
import cluster.Tuple;
import cluster.*;

import java.io.IOException;

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
    public int compute(Data data) throws IOException{
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

    public Cluster buildCandidateCluster(Data data, boolean[] isClustered) throws IOException {

        ClusterSet temp = new ClusterSet();

        for(int i =0; i!= data.getNumberOfExplanatoryAttributes(); i++){
            if(!isClustered[i]){
                // Aggiungo un cluster con centroide su i
                Cluster clu = new Cluster(data.getItemSet(i));
                //System.out.println("Centroid " + clu.getCentroid());

                for(int j = 0; j!= data.getNumberOfExplanatoryAttributes(); j++){
                    if(!isClustered[j]){
                       // System.out.println("Tuple" + data.getItemSet(j));

                        if(clu.getCentroid().getDistance(data.getItemSet(j)) <= radius){

                            clu.addData(j);
                        }
                    }
                }

                /*
                for(int a : clu.iterator()){
                    System.out.print(a + " ");
                }
                System.in.read();

                System.out.println(clu.toString(data));
                final int read = System.in.read();
                */
                temp.add(clu);

            }
        }



        Cluster max = temp.get(0);
        for(int i = 1; i<temp.length(); i++){
                System.out.println(temp.get(i) + " " + temp.get(i).iterator().length);
                System.out.println(max + " " + max.iterator().length);
                if(temp.get(i).iterator().length > max.iterator().length){
                    max = temp.get(i);
                }

        }



        for(int i = 0; i<max.iterator().length; i++){
            isClustered[max.iterator()[i]] = true;
        }

        return max;
    }
}
