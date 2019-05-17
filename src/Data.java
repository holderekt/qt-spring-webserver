class Data {
    private Object data[][];
    private int numberOfExamples;
    private Attribute attributeSet[];

    public Data() {
        data = new Object[2][2];
        attributeSet = new Attribute[2];

        data[0][0] = (String)"Sunny";
        data[0][1] = (String)"Hot";
        data[1][0] = (String)"Rain";
        data[1][1] = (String)"Mild";

        String outlookValues[] = { "Sunny, Overcast, Rain" };
        attributeSet[0] = new DiscreteAttribute("Outlook", 0, outlookValues);

        String temperatureValues[] = { "Hot, Mild, Cold" };
        attributeSet[1] = new DiscreteAttribute("Temperature", 1, temperatureValues);

        numberOfExamples = 2;
    }

    public int getNumberOfExplanatoryAttributes() {
        return numberOfExamples;
    }

    public Attribute[] getSchema() {
        return attributeSet;
    }

    Object getValues(int exampleIndex, int attributeIndex) {
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

    public static void main(String[] args){
        Data a = new Data();
        System.out.println(a);
    }

}