package json;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe che modella un nodo Object in JSON
 */
public class JSONObject extends JSONKeyItem{

    /**
     * Elenco degli elementi presenti nell'Object
     */
    protected List<JSONKeyItem> items = new LinkedList<>();

    /**
     * Costruttore di default
     */
    public JSONObject(){
        super();
    }

    /**
     * Costruttore con chiave fornita in formato JSONString
     * @param key Chiave dell'oggetto
     */
    public JSONObject(JSONString key) {
        super(key);
    }

    /**
     * Costruttore con chiave fornita in formato Stringa
     * @param key Chiave dell'oggetto
     */
    public JSONObject(String key) {
        super(key);
    }

    /**
     * Aggiunge un item all'Object
     * @param item Item da aggiungere
     */
    public void addItem(JSONKeyItem item){
        this.items.add(item);
    }

    /**
     * Renderizza nella sintassi JSON l'Oggetto
     * @return Stringa contenente l'oggetto renderizzato
     */
    @Override
    public String render() {

        String renderValue = this.key != null ? this.key.render() + ":{" : "{";

        Iterator<JSONKeyItem> iter = items.iterator();
        while(iter.hasNext()){
            renderValue += iter.next().render();
            if(iter.hasNext())
                renderValue += ",";
        }

        return renderValue + "}";
    }
}
