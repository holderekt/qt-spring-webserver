package json;

/**
 * Stringa in JSON
 */
public class JSONString extends JSONItem {

    /**
     * Valore della stringa
     */
    private String item;

    /**
     * Costruttore a partire da una Stringa
     * @param item Stringa da rappresentare in JSON
     */
    public JSONString(String item) {
        this.item = item;
    }

    /**
     * Renderizza la stringa in formato JSON
     * @return Stringa contenente l'oggetto renderizzato
     */
    @Override
    public String render() {
        return "\""+item+"\"";
    }
}
