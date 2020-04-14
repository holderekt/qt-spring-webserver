package database;

import java.util.ArrayList;
import java.util.List;

/**
 * Riga di una tabella
 */
public class Example implements Comparable<Example>{

	/**
	 * Insieme elementi riga
	 */
	private List<Object> example=new ArrayList<Object>();

	/**
	 * Aggiunge un elemento alla riga
	 *
	 * @param o Elemento da aggiungere
	 */
	public void add(Object o){
		example.add(o);
	}

	/**
	 * Restituisce l'elemento della riga in posizione i
	 *
	 * @param i Posizione elemento
	 * @return Object in posizione i
	 */
	public Object get(int i){
		return example.get(i);
	}

	/**
	 * Confonta l'example corrente con l'example in input
	 *
	 * @param ex Example da confrontare
	 * @return 0 se uguali, intero positivo se this.example maggiore di example, negativo se this.example minore di example
	 */
	public int compareTo(Example ex) {
		
		int i=0;
		for(Object o:ex.example){
			if(!o.equals(this.example.get(i)))
				return ((Comparable)o).compareTo(example.get(i));
			i++;
		}
		return 0;
	}
	public String toString(){
		String str="";
		for(Object o:example)
			str+=o.toString()+ " ";
		return str;
	}
	
}