package json;

/**
 * L'oggetto da modellare non è supportato
 */
public class JSONWrongTypeException extends Exception{
    public JSONWrongTypeException(String errorMessage){
        super(errorMessage);
    }
}
