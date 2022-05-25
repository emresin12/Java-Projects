
public class Edge {
	Vertice source;
	Vertice target;
	int capacity;
	int flow;
	
	public Edge(Vertice Source, Vertice Target, int Capacity) {
		source= Source;
		target= Target;
		capacity = Capacity;
		flow =0;
		
	}
}
