package mining;

import data.Data;
import data.Tuple;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;

public class Cluster implements Iterable<Integer>, Comparable<Cluster>{
	private Tuple centroid;

	private Set<Integer> clusteredData = new HashSet<Integer>();

	public Cluster(Tuple centroid){
		this.centroid=centroid;
	}
		
	public Tuple getCentroid(){
		return centroid;
	}
	

	public boolean addData(int id){
		return clusteredData.add(id);
		
	}
	

	public boolean contain(int id){
		return clusteredData.contains(id);
	}
	


	public void removeTuple(int id){
		clusteredData.remove(id);
		
	}
	
	public int  getSize(){
		return clusteredData.size();
	}
	
	
	public Iterator<Integer> iterator(){
		return clusteredData.iterator();
	}


	public String toString(){
		String str="Centroid=(";
		for(int i=0;i<centroid.getLength();i++)
			str+=centroid.get(i);
		str+=")";
		return str;
	}

	
	public String toString(Data data){
		String str="Centroid=(";
		for(int i=0;i<centroid.getLength();i++)
			str+=centroid.get(i)+ " ";

		str+=")\nExamples:\n";
		Iterator<Integer>  array = clusteredData.iterator();

		while(array.hasNext()){

			int next = array.next();
			str+="[";
			for(int j=0;j<data.getSchema().size();j++)
				str+=data.getValue(next, j)+" ";
			str+="] dist="+getCentroid().getDistance(data.getItemSet(next))+"\n";
		}

		str+="\nAvgDistance="+getCentroid().avgDistance(data, clusteredData);
		return str;
	}

	@Override
	public int compareTo(Cluster integers) {
		return (this.getSize() > integers.getSize() ? 1 : -1);
	}
}
