package data;

import java.io.Serializable;

/**
 * Coppia attributo discreto, valore discreto
 *
 * @author Ivan Diliso
 */
public class DiscreteItem extends Item implements Serializable {

    /**
     * Costruttore, crea un nuovo attributo discreto
     *
     * @param attribute Attributo del valore
     * @param value Valore dell'item
     */
    public DiscreteItem(DiscreteAttribute attribute, String value){
        super(attribute, value);
    }

    /**
     * Restituisce la distanza tra il valore dell'item ed
     * il parametro in input
     *
     * @param a Oggetto dal quale calcolare la distanza
     * @return 0 se sono uguali, 1 se diversi
     */
    public double distance(Object a){
        return (getValue().equals(a.toString()) ? 0 : 1);
    }
}
