package json;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe che modella gli array in JSON.
 */
public class JSONArray extends JSONKeyItem {
    /**
     * Insieme degli item dell'array
     */
    private List<JSONItem> items = new LinkedList<>();

    /**
     * Costruttore con chiave fornita in formato JSONString
     * @param key Chiave dell'array
     */
    public JSONArray(JSONString key){
        super(key);
    }

    /**
     * Costruttore con chiave fornita in formato Stringa
     * @param key Chiave dell'array
     */
    public JSONArray(String key){
        super(key);
    }

    /**
     * Aggiunge un item all'array
     * @param item Item da aggiungere
     */
    public void addItem(JSONItem item){
        this.items.add(item);
    }


    /**
     * Renderizza l'array utilizzando la sintassi JSON
     * @return Stringa contenente l'array in sintassi JSON
     */
    @Override
    public String render() {
        String renderValue = key.render() + ":[";
        Iterator<JSONItem> iter = items.iterator();
        while(iter.hasNext()){
            renderValue += iter.next().render();
            if(iter.hasNext())
                renderValue += ",";
        }

        return renderValue + "]";
    }
}
