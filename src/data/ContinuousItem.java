package data;

import java.io.Serializable;

public class ContinuousItem extends Item implements Serializable {

    public ContinuousItem(ContinuousAttribute attribute, Double value) {
        super(attribute, value);
    }

    @Override
    public double distance(Object item) {
        ContinuousItem otherItem = (ContinuousItem)item;
        ContinuousAttribute thisAttribute = (ContinuousAttribute)this.getAttribute();
        ContinuousAttribute otherAttribute = (ContinuousAttribute)otherItem.getAttribute();

        double a = thisAttribute.getScaledValue((Double)this.getValue());
        double b = thisAttribute.getScaledValue((Double)otherItem.getValue());

        //System.out.println("A:" + (Double)this.getValue()+ "   B:" + otherItem.getValue());
        //System.out.println("Max " + thisAttribute.toString());
        //System.out.println(" Calcolo " + a + " " + b + " " + Math.abs(b - a));
        return Math.abs(b - a);


    }
}
