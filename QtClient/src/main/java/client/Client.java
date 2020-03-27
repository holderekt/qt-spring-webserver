package client;

import clioutputmanager.IOManager;
import clioutputmanager.Menu;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Client per l'esecuzione di clustering QT
 * tramite connessione ad un server
 */

public class Client {


    /**
     * Stream per inviare dati al server
     */
    private ObjectOutputStream out;

    /**
     * Stream per ricevere dati dal server
     */
    private ObjectInputStream in;

    /** Messaggio inviato dal server in caso di connesione o
     * scambio di dati avvenuto correttamente
     */
    private final String SUCCESS_MESSAGE = "OK";

    /**
     * Menu principale
     */
    private Menu mainMenu = new Menu("Main menu", new String[]{
            "Load clusters from file",
            "Calculate clusters from database data"
    });

    /**
     * Menu per scegliere se ripetere l'esecuzione del clustering su database
     */
    private Menu repeatMenu = new Menu("Would you repeat?", new String[]{
            "YES",
            "NO"
    });

    /**
     * Menu per il ritorno al menu principale o uscita dal programma
     */
    private Menu returnMenu = new Menu("Would you choose a new operation from menu?", new String[]{
            "YES",
            "NO"
    });

    /**
     *  Connessione al server
     * @param IP Ip del server
     * @param PORT Porta del server
     * @throws IOException Errore nella connessione al server e creazione dei socket
     */
    public Client(String IP, int PORT) throws IOException {
        InetAddress inetAddress = InetAddress.getByName(IP);
        Socket socket = new Socket(inetAddress, PORT);
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());

        System.out.println("[INFO] Connected to sever: " + inetAddress.getCanonicalHostName() + ":" + PORT);
    }

    /**
     * Loop principale del programma, visualizzazione dei menu
     */
    public void mainLoop(){
        try{
            do {
                char c;
                switch (mainMenu.chooseOption()) {
                    case 1:
                        try {
                            System.out.println(this.learningFromFile());
                            break;
                        } catch (ServerException serverException) {
                            System.out.println(serverException.getMessage());
                            break;
                        }
                    case 2:

                        try{
                            this.storeTableFromDb();
                        } catch (ServerException serverException) {
                            System.out.println(serverException.getMessage());
                            break;
                        }

                        do {
                            try {
                                System.out.println(this.learningFromDbTable());
                                this.storeClusterInFile();
                            } catch (ServerException serverException) {
                                System.out.println(serverException.getMessage());
                            }
                        } while (repeatMenu.chooseOption() == 1);
                        break;
                }
            } while (returnMenu.chooseOption() == 1);
        }finally {
            this.stop();
        }
    }

    /**
     * Procedura per la chiusura sicura del client, viene
     * notificato il server della chiusura
     */
    private void stop(){
        try {
            this.sendData(Integer.valueOf(4));
            System.out.println("Successful shutdown!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Legge una stringa dallo stream di input dal server
     *
     * @return Stringa ricevuta
     */
    private String readStringResponse() {
        String response;
        try{
            response =  (String)this.in.readObject();
        }catch (IOException | ClassNotFoundException e){
            response = "";
        }
        return response;
    }

    /**
     * Legge un intero dallo stream di input dal server
     *
     * @return Intero ricevuto
     */
    private int readIntResponse(){
        int response;
        try{
            response =  (Integer) this.in.readObject();
        }catch (IOException | ClassNotFoundException e){
            response = 0;
        }
        return response;
    }

    /**
     * Invio di un oggetto al server tramite stream di output
     *
     * @param obj Oggetto da inviare
     * @throws IOException Errore invio oggetto al server
     */
    private void sendData(Object obj) throws IOException {
        this.out.writeObject(obj);
    }


    /**
     * Controlla se la risposta del server rappresenta un successo
     *
     * @param response Stringa di risposta dal server
     * @return Vero se risposta valida falso altrimenti
     */
    private boolean isValidResponse(String response){
        return response.equals(SUCCESS_MESSAGE);
    }

    /**
     * Invia al server il comando per la lettura dei cluster da file, invia un codice
     * identificativo della operazione e il nome del file da leggere, il nome del file
     * viene letto da tastiera
     *
     * @return Cluster letti da file in formato stringa
     * @throws ServerException File non trovato
     */
    private String learningFromFile() throws  ServerException{
        this.serverCommand(3, IOManager.readString("Filename"));
        return readStringResponse();
    }


    /**
     * Invia al server il comando per il caricamento della tabella dal database. Invia un codice
     * identificativo dell'operazione e il nome della tabella da leggere. Il nome della tabella viene letto da tastiera
     *
     * @throws ServerException Errore connessione al database o lettura tabella dal database
     */
    private void storeTableFromDb() throws ServerException{
        this.serverCommand(0, IOManager.readString("Table name"));
    }

    /**
     * Invia al server il comando per l'esecuzione del clustering su tabella caricata da database. Invia un codice
     * identificativo della operazione a il raggio per l'esecuzione del clustering. Il raggio viene letto da tastiera
     *
     * @return Risultato del clustering in formato stringa
     * @throws ServerException Errore scambio di dati con il server
     */
    private String learningFromDbTable() throws  ServerException{
        this.serverCommand(1, IOManager.readDouble("Radius", 0.0, Double.MAX_VALUE));
        System.out.println("Number of Clusters:" + this.readIntResponse());
        return this.readStringResponse();
    }


    /**
     * Invia al server il comando per il salvataggio dei risultati del clustering su file. Invia un codice
     * identificativo della operazione e il nome del file sul quale salvare i dati. Il nome del file viene letto da
     * tastiera
     *
     * @throws ServerException Errore scambio di dati con il server
     */
    private void storeClusterInFile() throws  ServerException{
        this.serverCommand(2, IOManager.readString("Filename"));
    }

    /**
     * Procedura generica per l'invio di un comando al server
     *
     * @param commandNumber Codice identificativo del comando
     * @param value Oggetto sul quale il server eseguira successive elaborazioni
     *
     * @throws ServerException Errore scambio di dati con il server
     */
    private void serverCommand(int commandNumber, Object value) throws ServerException {
        try{
            this.sendData(Integer.valueOf(commandNumber));
            this.sendData(value);
        }catch (IOException e){
            throw new ServerException("Could not send data to server");
        }

        String serverResponse = this.readStringResponse();

        if (!this.isValidResponse(serverResponse)){
            throw new ServerException(serverResponse);
        }
    }
}
