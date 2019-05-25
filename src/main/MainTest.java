package main;

import java.io.IOException;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		
		System.out.println("Test");
		Data data =new Data();
		System.out.println(data);
		double radius =2.0;
		QTMiner qt=new QTMiner(radius);
		int numIter=qt.compute(data);
		System.out.println("Number of clusters:"+numIter);
		System.out.println(qt.getC().toString(data));
		
		System.out.println("Fine");
	}

}
