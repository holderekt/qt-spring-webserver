package data;

import java.io.Serializable;


/**
 * Coppia attributo valore
 *
 * @author Ivan Diliso
 */
public abstract class Item implements Serializable {

    /**
     * Attributo del valore
     */
    private Attribute attribute;

    /**
     * Valore
     */
    private Object value;

    /**
     * Costruttore, crea un nuovo Item
     *
     * @param attribute Attributo del valore
     * @param value Valore dell'item
     */
    public Item(Attribute attribute, Object value) {
        this.attribute = attribute;
        this.value = value;
    }

    /**
     * Restituisce il valore dell'item
     *
     * @return value
     */
    public Object getValue(){
        return value;
    }

    /**
     * Restituisce l'attributo dell'item
     *
     * @return attribute
     */
    public Attribute getAttribute(){
        return attribute;
    }

    /**
     * Restituisce il valore dell'attributo in formato stringa
     *
     * @return value.toString()
     */
    public String toString(){
        return value.toString();
    }

    /**
     * Restituisce la distanza tra il valore dell'item ed
     * il parametro in input
     *
     * @param a Oggetto dal quale calcolare la distanza
     * @return Distanza tra i due oggetti
     */
    public abstract double distance(Object a);
}

