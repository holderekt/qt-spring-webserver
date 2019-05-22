package main;

import attribute.Attribute;
import attribute.DiscreteAttribute;
import cluster.Tuple;
import item.DiscreteItem;

public class Data {
    private Object[][] data;
    private int numberOfExamples;
    private Attribute[] attributeSet;

    Data() {
        data = new Object[14][5];
        attributeSet = new Attribute[5];

        data[0][0] = "Sunny";
        data[0][1] = "Hot";
        data[0][2] = "High";
        data[0][3] = "Weak";
        data[0][4] = "No";

        data[1][0] = "Sunny";
        data[1][1] = "Hot";
        data[1][2] = "High";
        data[1][3] = "Strong";
        data[1][4] = "No";

        data[2][0] = "Overcast";
        data[2][1] = "Hot";
        data[2][2] = "High";
        data[2][3] = "Weak";
        data[2][4] = "Yes";

        data[3][0] = "Rain";
        data[3][1] = "Mild";
        data[3][2] = "High";
        data[3][3] = "Weak";
        data[3][4] = "Yes";

        data[4][0] = "Rain";
        data[4][1] = "Cool";
        data[4][2] = "Normal";
        data[4][3] = "Weak";
        data[4][4] = "Yes";

        data[5][0] = "Rain";
        data[5][1] = "Cool";
        data[5][2] = "Normal";
        data[5][3] = "Strong";
        data[5][4] = "No";

        data[6][0] = "Overcast";
        data[6][1] = "Cool";
        data[6][2] = "Normal";
        data[6][3] = "Strong";
        data[6][4] = "Yes";

        data[7][0] = "Sunny";
        data[7][1] = "Mild";
        data[7][2] = "High";
        data[7][3] = "Weak";
        data[7][4] = "No";

        data[8][0] = "Sunny";
        data[8][1] = "Cool";
        data[8][2] = "Normal";
        data[8][3] = "Weak";
        data[8][4] = "Yes";


        data[9][0] = "Rain";
        data[9][1] = "Mild";
        data[9][2] = "Normal";
        data[9][3] = "Weak";
        data[9][4] = "Yes";

        data[10][0] = "Sunny";
        data[10][1] = "Mild";
        data[10][2] = "Normal";
        data[10][3] = "Strong";
        data[10][4] = "Yes";

        data[11][0] = "Overcast";
        data[11][1] = "Mild";
        data[11][2] = "High";
        data[11][3] = "Strong";
        data[11][4] = "Yes";

        data[12][0] = "Overcast";
        data[12][1] = "Hot";
        data[12][2] = "Normal";
        data[12][3] = "Weak";
        data[12][4] = "Yes";

        data[13][0] = "Rain";
        data[13][1] = "Mild";
        data[13][2] = "High";
        data[13][3] = "Strong";
        data[13][4] = "No";


        String[] outlookValues = { "Sunny, Overcast, Rain" };
        attributeSet[0] = new DiscreteAttribute("Outlook", 0, outlookValues);

        String[] temperatureValues = { "Hot, Mild, Cold" };
        attributeSet[1] = new DiscreteAttribute("Temperature", 1, temperatureValues);

        String[] humidityValues = {"High", "Normal"};
        attributeSet[2] = new DiscreteAttribute("Humidity", 2, humidityValues);

        String[] windValues = {"Weak", "Strong"};
        attributeSet[3] = new DiscreteAttribute("Wind", 3, windValues);

        String[] tennisValues = {"Yes", "No"};
        attributeSet[4] = new DiscreteAttribute("PlayTennis", 4, tennisValues);

        numberOfExamples = 14;
    }


    public int getNumberOfExplanatoryAttributes() {
        return numberOfExamples;
    }

    public Attribute[] getSchema() {
        return attributeSet;
    }

    public Object getValue(int exampleIndex, int attributeIndex) {
        return data[exampleIndex][attributeIndex];
    }

    public String toString() {
        String result = "";

        for (Attribute at : attributeSet) {
            result += at.getName() + ",";
        }
        result += "\n";

        for (int i = 0; i != getNumberOfExplanatoryAttributes(); i++) {
            result += i + ".";
            for (int j = 0; j != attributeSet.length; j++) {
                result += (String)data[i][j] + ",";
            }
            result += "\n";
        }

        return result;
    }

    public Tuple getItemSet(int index){
        Tuple tuple = new Tuple(attributeSet.length);
        for(int i = 0; i!= attributeSet.length; i++){
            tuple.add(new DiscreteItem((DiscreteAttribute)attributeSet[i], (String)data[index][i]), i);
        }
        return tuple;
    }

    public static void main(String[] args){
        Data a = new Data();
        System.out.println(a);
    }

}