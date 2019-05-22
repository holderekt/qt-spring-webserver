package attribute;

public class ContinuousAttribute extends Attribute {
    private double max;
    private double min;

    public ContinuousAttribute(String name, int index, double min, double max) {
        super(name, index);
        this.max = max;
        this.min = min;
    }

    public double getScaledValue(double v){
        return ((v-min)/(max-min));
    }
}