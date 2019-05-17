public class DiscreteItem extends Item {
    public DiscreteItem(DiscreteAttribute attribute, String value){
        super(attribute, value);
    }

    double distance(Object a){
        return (getValue().equals(a) ? 0 : 1);
    }
}
