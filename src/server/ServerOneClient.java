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

                String tablename = "";
                Double radius = 0.0;
                int niter = 0;
                String output_message;

                switch(selection){

                    case 0: // Retrieve table from database

                        tablename = (String)in.readObject();

                        try{
                            db_table = retrieveDatabaseTable(tablename);
                            output_message = "OK";
                        } catch (DatabaseConnectionException | NoValueException | SQLException | EmptyTypeException e) {
                            output_message = e.getMessage();
                        }

                        out.writeObject(output_message);

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
            // TODO COMPLETARE AGGIUSTANDO
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

    private  int learnFromTable(Data data, double radius){
        kmeans = new QTMiner(radius);
        return kmeans.compute(data);
    }

    private void learnFromFile(String filename){

        filename += ".dmp";
        System.out.println(socket + " " + filename);
        kmeans = new QTMiner(filename);

    }
}
