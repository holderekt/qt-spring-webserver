package main.java;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import database.DatabaseConnectionException;
import database.EmptyTypeException;
import database.NoValueException;
import keyboardinput.Keyboard;
import mining.ClusteringRadiusException;
import mining.QTMiner;
import data.Data;
import data.EmptyDatasetException;


public class MainTest2{
	/**
	 * @param args
	 */
	
	private int menu(){
		int answer;
		do{
			System.out.println("(1) Load Clusters from File");
			System.out.println("(2) Load Data");
			System.out.print("(1/2):");
			answer=Keyboard.readInt();
		}
		while(answer<=0 || answer>2);
		return answer;
		
	}
	
	private QTMiner learningFromFile() throws FileNotFoundException, IOException, ClassNotFoundException{
		String fileName="";
		System.out.print("File name:");
		fileName=Keyboard.readString();
		return new QTMiner(fileName+".dmp");
		
	}
	public static void main(String[] args) throws NoValueException, SQLException, EmptyTypeException, DatabaseConnectionException, ClassNotFoundException, IllegalAccessException, InstantiationException {
		
		MainTest2 main=new MainTest2();
		do{
			int menuAnswer=main.menu();
			switch(menuAnswer)
			{
				case 1:
					try {
						QTMiner qt=main.learningFromFile();
						System.out.println(qt);
					} catch (FileNotFoundException e1) {
						System.out.println(e1.getMessage());
					} catch (IOException e1) {
						System.out.println(e1.getMessage());
					} catch (ClassNotFoundException e1) {
						System.out.println(e1.getMessage());
					}
					break;
				case 2:
					Data data =new Data("playtennis");
					System.out.println(data);
					char answer='y';
					do{
						double radius=1.0;
						do{
							System.out.print("Insert radius (>0):");
							radius=Keyboard.readDouble();
						}while(radius<=0);
						QTMiner qt=new QTMiner(radius);
						try
						{
							int numC=qt.compute(data);
							System.out.println("Number of clusters:"+numC);
							System.out.println(qt.getC().toString(data));
							System.out.print("Backup file name:");
							String fileName=Keyboard.readString()+".dmp";
							System.out.println("Saving clusters in "+fileName);
							try {
								qt.salva(fileName);
							} catch (FileNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println("Saving transaction ended!");
							
							
						}
						catch(EmptyDatasetException | ClusteringRadiusException e)
						{
							System.out.println(e.getMessage());
						} catch (IOException e) {
							e.printStackTrace();
						}
						System.out.print("New execution?(y/n)");
						answer=Keyboard.readChar();
					}
					while(Character.toUpperCase(answer)=='Y');
					break;
				default:
					System.out.println("Invalid option!");
		
			}
			
			System.out.print("Would you choose another option from the menu?(y/n)");
			if(Character.toUpperCase(Keyboard.readChar())!='Y')
				break;
			}
		while(true);
	}
	



}