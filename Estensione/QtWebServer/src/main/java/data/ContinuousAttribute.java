package data;

import java.io.Serializable;

/**
 * Modella il concetto di attributo continuo di un valore,
 * caratterizzato da minimo e massimo
 *
 * @author Ivan Diliso
 */
public class ContinuousAttribute extends Attribute implements Serializable {

    /**
     * Valore massimo consentito
     */
    private double max;

    /**
     * Valore minimo consentito
     */
    private double min;

    /**
     * Costruttore, crea un nuovo attributo continuo
     *
     * @param name Nome dell'attributo
     * @param index Indice dell'attributo
     * @param min Minimo valore consentito
     * @param max Massimo valore consentito
     */
    public ContinuousAttribute(String name, int index, double min, double max) {
        super(name, index);
        this.max = max;
        this.min = min;
    }

    /**
     * Restituisce il valore scalato del parametro in ingresso
     *
     * @param v Valore da scalare
     * @return v scalato
     */
    public double getScaledValue(double v){
        return (v - min)/(max - min);
    }
}