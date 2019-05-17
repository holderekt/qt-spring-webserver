package mapes1;

import mapes1.Attribute;

class DiscreteAttribute extends Attribute {
    private String[] values;

    public DiscreteAttribute(String name, int index, String[] values) {
        super(name, index);
        this.values = values;
    }

    public int getNumberOfDistinctValues() {
        return values.length;
    }

    public String getValue(int i) {
        return values[i];
    }
}