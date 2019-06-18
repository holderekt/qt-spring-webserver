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
        try{
            while(true){
                Socket so = s.accept();
                try{
                    new ServerOneClient(so);
                }catch(IOException e){
                    so.close();
                }
            }
        }finally{
            s.close();
        }
    }

    public static void main(String[] args) throws IOException {
        new MultiServer(Integer.parseInt(args[0]));
    }
}
