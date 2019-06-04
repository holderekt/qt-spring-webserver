package data;

public class ContinuousItem extends Item{

    public ContinuousItem(ContinuousAttribute attribute, Double value) {
        super(attribute, value);
    }

    @Override
    public double distance(Object item) {
        ContinuousItem otherItem = (ContinuousItem)item;
        ContinuousAttribute thisAttribute = (ContinuousAttribute)this.getAttribute();
        ContinuousAttribute otherAttribute = (ContinuousAttribute)otherItem.getAttribute();

        double a = thisAttribute.getScaledValue((Double)this.getValue());
        double b = otherAttribute.getScaledValue((Double)otherItem.getValue());

        return Math.abs(a - b);
    }
}
