package json;

/**
 *  Classe nodo principale per gli elementi
 *  di una struttura JSON. Rappresenta un nodo che puo'
 *  possedere una chiave.
 *
 */
public abstract class JSONKeyItem extends JSONItem{

    /**
     * Chiave del nodo
     */
    protected JSONString key;

    /**
     * Costruttore con chiave fornita in formato JSONString
     * @param key Chiave del nodo
     */
    public JSONKeyItem(JSONString key){
        this.key = key;
    }

    /**
     * Costruttore con chiave nulla
     */
    public JSONKeyItem(){
        this.key = null;
    }

    /**
     * Costruttore con chiave fornita in formato Stringa
     * @param key Valore della chiave
     */
    public JSONKeyItem(String key){
        this.key = new JSONString(key);
    }
}
