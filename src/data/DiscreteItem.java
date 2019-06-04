package data;

import java.io.Serializable;

public class DiscreteItem extends Item implements Serializable {
    public DiscreteItem(DiscreteAttribute attribute, String value){
        super(attribute, value);
    }

    public double distance(Object a){
        return (getValue().equals(a.toString()) ? 0 : 1);
    }
}
