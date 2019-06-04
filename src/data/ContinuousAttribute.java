package data;

import data.Attribute;

import java.io.Serializable;

public class ContinuousAttribute extends Attribute implements Serializable {
    private double max;
    private double min;

    public ContinuousAttribute(String name, int index, double min, double max) {
        super(name, index);
        this.max = max;
        this.min = min;
    }

    public double getScaledValue(double v){
        double up = v - min;
        double down = max - min;
        return (up / down);
    }
}