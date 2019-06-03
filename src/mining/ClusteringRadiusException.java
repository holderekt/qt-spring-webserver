package mining;

public class ClusteringRadiusException extends Exception {
    public ClusteringRadiusException(){
        super("Only 1 cluster found");
    }

}
