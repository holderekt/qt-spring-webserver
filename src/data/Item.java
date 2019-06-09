package data;

import java.io.Serializable;

public abstract class Item implements Serializable {
    private Attribute attribute;
    private Object value;

    public Item(Attribute attribute, Object value) {
        this.attribute = attribute;
        this.value = value;
    }

    public Object getValue(){
        return value;
    }

    public Attribute getAttribute(){
        return attribute;
    }

    public String toString(){
        return value.toString();
    }

    public abstract double distance(Object a);
}

