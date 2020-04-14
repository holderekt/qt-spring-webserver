package json;

/**
 * Valore numerico in JSON
 */
public class JSONValue extends JSONItem {
    /**
     * Valore
     */
    private Object value;

    /**
     * Costruttore
     * @param item Oggetto da salvare in value
     */
    public JSONValue(Object item) {
        this.value = item;
    }

    /**
     * Renderizza il valore in formato JSON
     * @return Stringa contenente il valore in formato JSON
     */
    @Override
    public String render() {
        return value.toString();
    }

}
