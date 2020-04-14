package mining;

/**
 * Eccezzione
 * É stato ritrovato un solo cluster
 */
public class ClusteringRadiusException extends Exception {
    public ClusteringRadiusException(){
        super("Only 1 cluster found");
    }

}
