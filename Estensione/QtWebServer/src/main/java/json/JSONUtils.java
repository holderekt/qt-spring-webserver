package json;

/**
 * Utils per gli oggetti JSON
 */
public class JSONUtils {

    /**
     * Fornisce l'oggetto JSON piu appropriato per l'oggetto in ingresso
     * @param obj Oggetto da trasformare in JSON
     * @return Versione JSON dell'oggetto in ingresso
     * @throws JSONWrongTypeException Oggetto in input non supportato
     */
    public static JSONItem getJsonItem(Object obj) throws JSONWrongTypeException {
        if(obj instanceof String){
            return new JSONString((String) obj);
        }else if(obj instanceof Double || obj instanceof Integer || obj instanceof Boolean ){
            return new JSONValue(obj);
        }else{
            throw new JSONWrongTypeException("Type is not supported");
        }
    }
}
