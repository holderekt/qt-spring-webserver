package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {
    private int PORT = 8080;

    public MultiServer(int port) throws IOException {
        this.PORT = port;
        run();
    }

    private void run() throws IOException {
        ServerSocket s = new ServerSocket(PORT);
        while(true){
            Socket so = s.accept();
            new ServerOneClient(so);
        }
    }

    public static void main(String[] args) throws IOException {
        MultiServer server = new MultiServer(Integer.parseInt(args[0]));
    }
}
