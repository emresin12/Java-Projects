import java.util.ArrayList;
import java.util.LinkedList;

public class Vertice {
	int id;
	LinkedList<Edge> adjList;
	int eFlow;
	
	int height;
	
	public Vertice(int Id) {
		id= Id;
		adjList= new LinkedList<Edge>();
		int eFlow=0;
	}
	
	
}
