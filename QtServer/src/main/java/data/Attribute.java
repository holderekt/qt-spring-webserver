package data;

import java.io.Serializable;


/**
 * Modella il concetto di attributo di un valore
 *
 * @author  Ivan Diliso
 */
public class Attribute implements Serializable {

    /**
     * Nome dell'attributo
     */
    private String name;

    /**
     * Indice
     */
    private int index;

    /**
     * Costruttore, crea un nuovo attributo sulla base di un nome e indice
     * in input
     *
     * @param name Nome dell'attributo
     * @param index Indice dell'attributo
     */
    public Attribute(String name, int index) {
        this.name = name;
        this.index = index;
    }

    /**
     * Restituisce il nome dell'attributo
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Restituisce l'attributo sotto forma di stringa
     * @return name
     */
    public String toString() {
        return name;
    }
}