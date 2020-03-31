package json;

/**
 * L'oggetto da modellare non e' supportato
 */
public class JSONWrongTypeException extends Exception{
    public JSONWrongTypeException(String errorMessage){
        super(errorMessage);
    }
}
