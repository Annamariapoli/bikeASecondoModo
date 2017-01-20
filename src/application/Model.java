package application;

import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import bean.Station;
import db.Dao;

public class Model {
	
	private Dao dao = new Dao();
	private UndirectedGraph<Station , DefaultEdge> grafo;
	
	public List<Station > getStation(){
		List<Station > all= dao.getAllStation();
		return all;
	}
	
	public int contoStart(Station s ){
		int conta = dao.contoStart(s);
		return conta;
	}
	
	public int contoEnd(Station s ){
		int conta = dao.contoEnd(s);
		return conta;
	}
	
	public double getDistanza(Station  s1, Station s2){
		double distanza = 0;
		if(s1!=null && s2!=null){
			if(!s1.equals(s2)){
				 distanza =LatLngTool.distance(s1.getCoords(), s2.getCoords(), LengthUnit.KILOMETER);
				
			}
		}
		return distanza;
	}

	public UndirectedGraph<Station, DefaultEdge> buildGraph(double km){        //ok  //costruisco il grafo solo se la distanza è inferiore
		grafo = new SimpleGraph<Station, DefaultEdge> (DefaultEdge.class);
		Graphs.addAllVertices(grafo, getStation());                          //tutte le station come vertici
		for(Station s1 : grafo.vertexSet()){
			for(Station s2 : grafo.vertexSet()){
				if(!s1.equals(s2)){
					double distanza = getDistanza(s1, s2);
					if(distanza < km ){
						if(!grafo.containsEdge(s1, s2)){
						    grafo.addEdge(s1, s2);
						}
					}
				}
			}
		}
		//System.out.println(grafo.toString());  
		return grafo;
	}
	
	//poi devo ritornare archi e stazioni di questo grafo 
	
	public static void main (String [] args){
		Model m = new Model();
		m.buildGraph(20000);
	}
	
}
