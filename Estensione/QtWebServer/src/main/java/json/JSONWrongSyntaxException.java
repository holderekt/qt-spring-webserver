package json;

public class JSONWrongSyntaxException extends Exception {
    public JSONWrongSyntaxException(String errorMessage){
        super(errorMessage);
    }
}
