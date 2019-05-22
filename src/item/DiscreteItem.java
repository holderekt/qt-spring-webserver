package item;

import attribute.DiscreteAttribute;

public class DiscreteItem extends Item {
    public DiscreteItem(DiscreteAttribute attribute, String value){
        super(attribute, value);
    }

    public double distance(Object a){
        return (getValue().equals(a) ? 0 : 1);
    }
}
