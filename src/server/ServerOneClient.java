package server;

import data.Data;
import data.EmptyDatasetException;
import database.DatabaseConnectionException;
import database.EmptyTypeException;
import database.NoValueException;
import mining.ClusteringRadiusException;
import mining.QTMiner;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

public class ServerOneClient extends Thread {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private QTMiner kmeans;

    public ServerOneClient(Socket so) throws IOException {
        socket = so;
        System.out.println("THREAD started on " + socket);
        in = new ObjectInputStream(so.getInputStream());
        out = new ObjectOutputStream(so.getOutputStream());
        this.start();
    }

    public void run(){
        try{
            Data db_table = null;
            while(true){

                Object temp_selection = (int)in.readObject();
                int selection = 0;

                if(temp_selection instanceof Integer){
                    selection = (int)temp_selection;
                }

                String tablename = "";
                Double radius = 0.0;
                int niter = 0;

                switch(selection){
                    case 0: // Retrieve table from database
                        tablename = (String)in.readObject();
                        db_table = retrieveDatabaseTable(tablename);
                        out.writeObject("OK");
                        break;

                    case 1: // Learn from database table
                        radius = (Double)in.readObject();
                        niter = learnFromTable(db_table, radius);
                        out.writeObject("OK");
                        out.writeObject(niter);
                        out.writeObject(kmeans.toString(db_table));
                        break;

                    case 2: // Store cluster in file
                        String filename = (String)in.readObject();
                        filename += ".dmp";
                        kmeans.salva(filename);
                        out.writeObject("OK");
                        break;

                    case 3: // Learn from file
                        filename = (String)in.readObject();
                        learnFromFile(filename);
                        this.out.writeObject("OK");
                        System.out.println("Mario");
                        this.out.writeObject(kmeans.toString());
                        System.out.println("Mario2");
                        break;
                }
            }

        }catch(IOException e){
            // Close socket
            try {
                socket.close();
            } catch (IOException ex) {
                // Cannot close the socket
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            // Object class not found
            e.printStackTrace();
        }finally{
            System.out.println("Closing " + socket);
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Data retrieveDatabaseTable(String tablename){
        Data result = null;
        try {
            result =  new Data(tablename);
        } catch (Exception e) {

        }
        return result;
    }

    private  int learnFromTable(Data data, double radius){
        kmeans = new QTMiner(radius);

        try {
           return kmeans.compute(data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClusteringRadiusException e) {
            e.printStackTrace();
        } catch (EmptyDatasetException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private int learnFromFile(String filename){
        try {
            filename += ".dmp";
            System.out.println(socket + " " + filename);
            kmeans = new QTMiner(filename);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
