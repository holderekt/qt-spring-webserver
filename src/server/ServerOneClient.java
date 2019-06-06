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
        in = new ObjectInputStream(so.getInputStream());
        out = new ObjectOutputStream(so.getOutputStream());
        this.start();
    }

    public void run(){
        Data data = null;
        QTMiner miner;


            while(socket.isConnected() && socket.isBound()){
                try {

                    int response = (int) in.readObject();
                    System.out.println(response);
                    switch (response) {
                        case 3:
                            // Voglio imparare da file
                            // TODO COMPLEATARE
                            break;


                        case 0: // STORE FROM DATABASE
                            String tableName = (String) in.readObject();
                            data = new Data(tableName);
                            out.writeObject("OK");
                            break;

                        case 1: // LEARN FROM DATA
                            System.out.println("soN uququ");
                            double radius = (Double)in.readObject();
                            System.out.println(data);
                            miner = new QTMiner(radius);
                            miner.compute(data);
                            String output = miner.toString(data);
                            out.writeObject(output);

                            break;

                    }
                } catch (EmptyTypeException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (DatabaseConnectionException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NoValueException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                   break;
                }catch (ClusteringRadiusException e){
                    e.printStackTrace();
                }catch (EmptyDatasetException e){
                    e.printStackTrace();
                } finally {
                /*
                try {
                    out.writeObject("ERROR");
                } catch (IOException e) {
                    e.printStackTrace();
                }


                 */
                }
            }



    }


}
