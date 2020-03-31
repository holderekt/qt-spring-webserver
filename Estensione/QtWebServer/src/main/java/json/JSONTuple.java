package json;

/**
 * Tupla in JSON, presenta una chiave e un singolo valore
 */
public class JSONTuple extends JSONKeyItem {
    /**
     * Valore della tupla
     */
    JSONItem item;

    /**
     * Costruttore
     * @param key Chiave della tupla
     * @param item Valore della tupla
     */
    public JSONTuple(String key, JSONValue item) {
        super(key);
        this.item = item;
    }

    /**
     * Costruttore
     * @param key Chiave della tupla
     * @param item Valore della tupla
     */
    public JSONTuple(JSONString key, JSONValue item){
        super(key);
        this.item = item;
    }

    /**
     * Costruttore
     * @param key Chiave della tupla
     * @param item Valore della tupla
     */
    public JSONTuple(String key, JSONString item) {
        super(key);
        this.item = item;
    }

    /**
     * Costruttore
     * @param key Chiave della tupla
     * @param item Valore della tupla
     */
    public JSONTuple(JSONString key, JSONString item){
        super(key);
        this.item = item;
    }

    /**
     * Renderizza la tupla con la sintassi JSON
     * @return Stringa contenente la tupla renderizzata
     */
    public String render() {
        return this.key.render() + ":" + this.item.render();
    }
}
