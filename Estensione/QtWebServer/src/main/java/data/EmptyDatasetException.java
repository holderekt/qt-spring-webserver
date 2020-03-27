package data;

/**
 *  Eccezzione
 *  Definisce l'errore : Dataset vuoto
 */
public class EmptyDatasetException extends Exception {
    public EmptyDatasetException(){
        super("Empty dataset");
    }
}
