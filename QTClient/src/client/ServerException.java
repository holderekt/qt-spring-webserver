package client;

/**
 *  Errore nella connesione e scambio di dati con
 *  il server
 */

public class ServerException extends Exception {
    public ServerException() {
        super("Server Connection Error");
    }

    public ServerException(String paramString) {
        super(paramString);
    }
}