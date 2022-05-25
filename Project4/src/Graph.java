import java.util.ArrayList;

public class Graph {
	ArrayList<Vertice> vertlst;
	int source;
	int target;
	public Graph() {
		vertlst =new ArrayList<Vertice>();
		source = 0;
		target = 1;
	}
	public Graph(ArrayList<Vertice> vlist) {
		vertlst = vlist;
		source = 0;
		target = 1;
	}
	
	public void PreFlow() {
		
		
		for(Edge e : vertlst.get(source).adjList) {
			Vertice targetv = vertlst.get(e.target.id);
			targetv.eFlow+=e.capacity;
			vertlst.get(source).eFlow-=e.capacity;
			
			e.flow+=e.capacity;
			updateResidual(e, e.capacity);
			
		}
		
		for(Vertice v: vertlst) {
			
			v.height=0;
		}
		vertlst.get(source).height=vertlst.size();
	}

	public boolean Push(int vertId) {
		Vertice v = vertlst.get(vertId);
		for(Edge edg : v.adjList) {
			if(edg.flow==edg.capacity) {
				continue;
			}
			if( edg.target.height>= edg.source.height) {
				continue;
			}
		
			
			// minimum olanı al ve pushla
			int delta = Math.min(v.eFlow, edg.capacity-edg.flow);
			
			
			v.eFlow-=delta;
			edg.target.eFlow+=delta;
			
			
			//ters yol oluştur
			edg.flow+=delta;
			updateResidual(edg, delta);
			return true;
		}
		return false;
		
	}
	
	public void Relabel(int vertId) {
		
		Vertice v = vertlst.get(vertId);
		int minheight=Integer.MAX_VALUE;
		for(Edge edg: v.adjList) {
			
			if(edg.capacity== edg.flow) {
				continue;
			}
			
			if(edg.target.height<minheight ) {
				minheight=edg.target.height;
				
			}
			
		}
		v.height=minheight+1;
	}
	
	public void updateResidual(Edge e,int amount) {
		
		for(Edge edg :e.target.adjList ) {
			if(edg.source==e.target&& edg.target==e.source) {
				
			
				edg.flow-=amount;
				return;
				
			}
		}
		Edge residualEdge= new Edge(e.target,e.source,0);
		residualEdge.flow-=amount;
		e.target.adjList.add(residualEdge);
	}
	public int FindVertice() {
		for(Vertice v : vertlst) {
			if(v.eFlow>0 && (v.id!=source && v.id!=target)){
				return v.id;
			}
		}
		return -1;
		
	}
	public boolean isAllBiggerThanOne() {
		for (int i = 2; i < vertlst.size(); i++) {
			if(vertlst.get(i).height<1) {
				return false;
			}
			
		}
		return true;
	}
	public int GetMaxFlow() {
		
		PreFlow();
		int found=FindVertice();
		while(found!=-1 && !isAllBiggerThanOne()) {
			int id =found;

			while(!Push(id)) {
				Relabel(id);
			}
			found = FindVertice();
		}
		// 1 is sink update accordingly
		return vertlst.get(1).eFlow;
	}
}
