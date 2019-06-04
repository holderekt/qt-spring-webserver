package main.java;
import data.Data;
import keyboardinput.Keyboard;
import mining.ClusteringRadiusException;
import data.EmptyDatasetException;
import mining.QTMiner;
import java.io.IOException;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		double radius = 0;
		boolean repeat = true;

		while(repeat == true){

			do{
				clearScreen();
				System.out.print("Insert QT radius:");
				radius = Keyboard.readDouble();
			}while(radius <= 0);


			int numIter;

			Data data =new Data();
			System.out.println(data);
			QTMiner qt=new QTMiner(radius);

			try{
				numIter = qt.compute(data);

				System.out.println("Number of clusters:"+numIter);
				System.out.println(qt.getC().toString(data));

			}catch (ClusteringRadiusException e){
				e.printStackTrace();
			} catch (EmptyDatasetException e) {
				e.printStackTrace();
			}

			System.out.print("Do you want to repeat the process N/y :");
			if(Keyboard.readChar() ==  'N'){
				repeat = false;
			}else{
				clearScreen();
			}

		}
	}

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

}
