package data;

import data.Attribute;

public abstract class Item {
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
        return (String)value.toString();
    }

    public abstract double distance(Object a);

    public static void main(String[] args){
        System.out.println("CIao");
    }
}
