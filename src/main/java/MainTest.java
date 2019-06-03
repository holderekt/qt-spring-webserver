package main.java;
import data.Data;
import keyboardinput.Keyboard;
import mining.QTMiner;
import java.io.IOException;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		double radius;
		boolean repeat = true;

		while(repeat == true){
			System.out.print("Insert QT radius:");
			radius = Keyboard.readDouble();


			Data data =new Data();
			System.out.println(data);
			QTMiner qt=new QTMiner(radius);
			int numIter=qt.compute(data);
			System.out.println("Number of clusters:"+numIter);
			System.out.println(qt.getC().toString(data));

			System.out.print("Do you want to repeat the process N/y :");
			if(Keyboard.readChar() ==  'N'){
				repeat = false;
			}else{
				Runtime.getRuntime().exec("clear");
			}
		}




	}

}
