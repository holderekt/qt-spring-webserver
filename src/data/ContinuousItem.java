package data;

import java.io.Serializable;

/**
 * Coppia attributo continuo, valore continuo
 *
 * @author Ivan Diliso
 */
public class ContinuousItem extends Item implements Serializable {

    /**
     * Costruttore, crea un nuovo item continuo
     * @param attribute Attributo continuo dell'item
     * @param value Valore dell'item
     */
    public ContinuousItem(ContinuousAttribute attribute, Double value) {
        super(attribute, value);
    }

    /**
     * Restituisce la distanza in valore assoluto tra il valore dell'item scalato
     * ed il parametro in input scalato
     *
     * @param item Oggetto dal quale calcolare la distanza
     * @return Distanza tra i due oggetti
     */
    @Override
    public double distance(Object item) {
        ContinuousItem otherItem = (ContinuousItem)item;
        ContinuousAttribute thisAttribute = (ContinuousAttribute)this.getAttribute();

        double a = thisAttribute.getScaledValue((Double)this.getValue());
        double b = thisAttribute.getScaledValue((Double)otherItem.getValue());

        return Math.abs(b - a);
    }
}
