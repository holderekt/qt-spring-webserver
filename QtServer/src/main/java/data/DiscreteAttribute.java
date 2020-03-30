package data;

import java.io.Serializable;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Modella il concetto di attributo discreto di un valore catatterizzato
 * da un insieme di valori
 *
 * @author Ivan Diliso
 */
public class DiscreteAttribute extends Attribute implements Iterable<String>, Serializable {

    /**
     * Insieme dei valori consentiti
     */
    private TreeSet<String> values = new TreeSet<>();

    /**
     * Costruttore, crea un nuovo attributo discreto
     * @param name Nome dell'attributiìo
     * @param index Indice dell'attributo
     * @param values Insieme dei valori consentiti
     */
    public DiscreteAttribute(String name, int index, String[] values) {
        super(name, index);
        for(String val : values){
            this.values.add(val);
        }
    }

    /**
     * Restituisce un iteratore per scorrere l'elenco dei valori consentitis
     * @return values.iterator()
     */
    @Override
    public Iterator<String> iterator() {
        return values.iterator();
    }
}