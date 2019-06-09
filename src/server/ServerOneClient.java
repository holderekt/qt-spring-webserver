package server;

import data.Data;
import data.EmptyDatasetException;
import database.DatabaseConnectionException;
import database.EmptyTypeException;
import database.NoValueException;
import mining.ClusteringRadiusException;
import mining.QTMiner;
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
        in = new ObjectInputStream(so.getInputStream());
        out = new ObjectOutputStream(so.getOutputStream());
        this.start();
        System.out.println("THREAD " + this.getId() + " started on " + socket);
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

                String tablename;
                Double radius;

                int niter = 0;
                String output_message;

                switch(selection){

                    case 0: // Retrieve table from database

                        tablename = (String)in.readObject();

                        try{
                            db_table = retrieveDatabaseTable(tablename);
                            output_message = "OK";

                        } catch (DatabaseConnectionException | NoValueException | SQLException | EmptyTypeException e) {
                            e.printStackTrace();

                            if(e.getMessage() == null){
                                output_message = "Error: Could not retrieve data from database";
                            }else{
                                output_message = e.getMessage();
                            }
                        }

                        out.writeObject(output_message);

                        break;

                    case 1: // Learn from database table
                        radius = (Double)in.readObject();

                        try{
                            niter = learnFromTable(db_table, radius);
                            output_message = "OK";
                        } catch (EmptyDatasetException | ClusteringRadiusException | IOException e) {
                            e.printStackTrace();

                            if(e.getMessage() == null){
                                output_message = "Error: Could not learn from data";
                            }else{
                                output_message = e.getMessage();
                            }
                        }

                        out.writeObject(output_message);

                        if(output_message == "OK"){
                            out.writeObject(niter);
                            out.writeObject(kmeans.toString(db_table));
                        }

                        break;

                    case 2: // Store cluster in file

                        String filename = (String)in.readObject() + ".dmp";

                        try{
                            kmeans.salva(filename);
                            output_message = "OK";

                        } catch (IOException e) {

                            if(e.getMessage() == null){
                                output_message = "Error: Could not save cluster in file";
                            }else{
                                output_message = e.getMessage();
                            }
                        }

                        out.writeObject(output_message);
                        break;

                    case 3: // Learn from file

                        filename = (String)in.readObject();

                        try{
                            learnFromFile(filename);
                            output_message = "OK";
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();

                            if(e.getMessage() == null){
                                output_message = "Error: Could not learn from given file";
                            }else{
                                output_message = e.getMessage();
                            }
                        }

                        out.writeObject(output_message);

                        if(output_message == "OK"){
                            out.writeObject(kmeans.toString());
                        }

                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Closing THREAD " + this.getId() + " - " + socket);
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Data retrieveDatabaseTable(String tablename) throws SQLException, EmptyTypeException, NoValueException, DatabaseConnectionException {
        return new Data(tablename);
    }

    private int learnFromTable(Data data, double radius) throws EmptyDatasetException, IOException, ClusteringRadiusException {
        kmeans = new QTMiner(radius);
        return kmeans.compute(data);
    }

    private void learnFromFile(String filename) throws IOException, ClassNotFoundException {
        filename += ".dmp";
        kmeans = new QTMiner(filename);
    }
}
