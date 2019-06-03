package data;

import data.Attribute;

import java.util.Iterator;
import java.util.TreeSet;

public class DiscreteAttribute extends Attribute implements Iterable<String> {
    private TreeSet<String> values = new TreeSet<>();

    public DiscreteAttribute(String name, int index, String[] values) {
        super(name, index);
        for(String a : values){
            this.values.add(a);
        }
    }

    public int getNumberOfDistinctValues() {
        return values.size();
    }

    @Override
    public Iterator<String> iterator() {
        return values.iterator();
    }
}