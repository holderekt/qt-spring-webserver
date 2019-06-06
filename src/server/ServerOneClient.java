package server;

import mining.QTMiner;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerOneClient extends Thread {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private QTMiner kmeans;

    public ServerOneClient(Socket so) throws IOException {
        // TODO Finite implementazione
    }

    public void run(){
        // TODO Finire implementazione
    }
}
