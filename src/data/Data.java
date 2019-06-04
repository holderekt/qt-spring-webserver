package data;

import java.util.LinkedList;
import java.util.List;

public class Data {
    private Object[][] data;
    private int numberOfExamples;
    private List<Attribute> attributeSet = new LinkedList<>();

    public Data() {
        data = new Object[14][5];


        data[0][0] = "Sunny";
        data[0][1] = new Double(30.3);
        data[0][2] = "High";
        data[0][3] = "Weak";
        data[0][4] = "No";

        data[1][0] = "Sunny";
        data[1][1] = new Double(30.3);
        data[1][2] = "High";
        data[1][3] = "Strong";
        data[1][4] = "No";

        data[2][0] = "Overcast";
        data[2][1] = new Double(30);
        data[2][2] = "High";
        data[2][3] = "Weak";
        data[2][4] = "Yes";

        data[3][0] = "Rain";
        data[3][1] = new Double(13);
        data[3][2] = "High";
        data[3][3] = "Weak";
        data[3][4] = "Yes";

        data[4][0] = "Rain";
        data[4][1] = new Double(0);
        data[4][2] = "Normal";
        data[4][3] = "Weak";
        data[4][4] = "Yes";

        data[5][0] = "Rain";
        data[5][1] = new Double(0);
        data[5][2] = "Normal";
        data[5][3] = "Strong";
        data[5][4] = "No";

        data[6][0] = "Overcast";
        data[6][1] = new Double(0.1);
        data[6][2] = "Normal";
        data[6][3] = "Strong";
        data[6][4] = "Yes";

        data[7][0] = "Sunny";
        data[7][1] = new Double(13);
        data[7][2] = "High";
        data[7][3] = "Weak";
        data[7][4] = "No";

        data[8][0] = "Sunny";
        data[8][1] = new Double(0.1);
        data[8][2] = "Normal";
        data[8][3] = "Weak";
        data[8][4] = "Yes";


        data[9][0] = "Rain";
        data[9][1] = new Double(12);
        data[9][2] = "Normal";
        data[9][3] = "Weak";
        data[9][4] = "Yes";

        data[10][0] = "Sunny";
        data[10][1] = new Double(12.5);
        data[10][2] = "Normal";
        data[10][3] = "Strong";
        data[10][4] = "Yes";

        data[11][0] = "Overcast";
        data[11][1] = new Double(12.5);
        data[11][2] = "High";
        data[11][3] = "Strong";
        data[11][4] = "Yes";

        data[12][0] = "Overcast";
        data[12][1] = new Double(29.21);
        data[12][2] = "Normal";
        data[12][3] = "Weak";
        data[12][4] = "Yes";

        data[13][0] = "Rain";
        data[13][1] = new Double(12.5);
        data[13][2] = "High";
        data[13][3] = "Strong";
        data[13][4] = "No";


        String[] outlookValues = { "Sunny, Overcast, Rain" };
        attributeSet.add(new DiscreteAttribute("Outlook", 0, outlookValues));

        attributeSet.add(new ContinuousAttribute("Temperature", 1, 3.2,28.7));

        String[] humidityValues = {"High", "Normal"};
        attributeSet.add(new DiscreteAttribute("Humidity", 2, humidityValues));

        String[] windValues = {"Weak", "Strong"};
        attributeSet.add(new DiscreteAttribute("Wind", 3, windValues));

        String[] tennisValues = {"Yes", "No"};
        attributeSet.add(new DiscreteAttribute("PlayTennis", 4, tennisValues));

        numberOfExamples = 14;
    }


    public int getNumberOfExplanatoryAttributes() {
        return numberOfExamples;
    }

    public List<Attribute> getSchema() {
        return attributeSet;
    }

    public Object getValue(int exampleIndex, int attributeIndex) {
        return data[exampleIndex][attributeIndex];
    }

    public String toString() {
        String result = "";

        for (Attribute at : attributeSet) {
            result += at.getName() + ", ";
        }
        result += "\n";

        for (int i = 0; i != getNumberOfExplanatoryAttributes(); i++) {
            result += i + ".";
            for (int j = 0; j != attributeSet.size(); j++) {
                result += (String)data[i][j].toString() + ", ";
            }
            result += "\n";
        }

        return result;
    }

    public Tuple getItemSet(int index){
        Tuple tuple = new Tuple(attributeSet.size());
        for(int i = 0; i!= attributeSet.size(); i++){
            Object o = attributeSet.get(i);
            if(o instanceof DiscreteAttribute){
                tuple.add(new DiscreteItem((DiscreteAttribute)attributeSet.get(i), (String)data[index][i]), i);
            }else if( o instanceof  ContinuousAttribute){
                tuple.add(new ContinuousItem((ContinuousAttribute)attributeSet.get(i), (Double)data[index][i]), i);
            }
        }
        return tuple;
    }
}