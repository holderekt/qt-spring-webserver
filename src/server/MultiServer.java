package server;

public class MultiServer {
    private int port = 8080;

    public MultiServer(int port){
        this.port = port;
        run();
    }

    private void run(){
        // TODO Finire implementazione
    }


    public static void main(String[] args){
        MultiServer server = new MultiServer(Integer.parseInt(args[0]));
    }
}
