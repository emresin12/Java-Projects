import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Dinic {
	ArrayList<Vertice> vertlst;
	int source;
	int target;
	public Dinic() {
		vertlst =new ArrayList<Vertice>();
		source = 0;
		target = 1;
	}
	public Dinic(ArrayList<Vertice> vlist) {
		vertlst = vlist;
		source = 0;
		target = 1;
	}
	
	public boolean DinicBfs(int[]dist) {
		//levellerı belirliyor eğer yol yoksa false döndürüyor
		for (int i = 0; i < dist.length; i++) {
			dist[i]=-1;
		}
		Queue<Integer> q = new LinkedList<Integer>();
	    q.add(source);
	    while(!q.isEmpty()) {
	    	int num = q.poll();
	    	for(Edge e : vertlst.get(num).adjList) {
	    		if(dist[e.target.id]<0 && e.flow<e.capacity) {
	    			dist[e.target.id]=dist[num]+1;
	    			q.add(e.target.id);
	    		}
	    		
	    	}
	    	
	    }
	    
	    return dist[target]>=0;
		
	}
	public int DinicDfs(int u, int dest, int[]ptr, int[]dist, int flow) {
		//eklenebilecek en büyük flowu ekliyor
		if(dest==u) {
			return flow;
		}
		
		//ptr edgelerini tekrar tekrar gezmemek için optimize ediyor
		for(;ptr[u]< vertlst.get(u).adjList.size(); ++ptr[u]) {
			Edge e = vertlst.get(u).adjList.get(ptr[u]);
		      if (dist[e.target.id] == dist[u] + 1 && e.flow < e.capacity) {
		        int df = DinicDfs( e.target.id, dest, ptr, dist, Math.min(flow, e.capacity - e.flow));
		        if (df > 0) {
		          e.flow += df;
		          //reverse edge indexini tutmadığım için for döndürüyorum
		          for(Edge redg:vertlst.get(e.target.id).adjList) {
		        	  if(redg.target.id==u) {
		        		  redg.flow-=df;
		        		  break;
		        	  }
		          }
		          
		          return df;
		        }
		      }
		}
		return 0;
	}
	
	public int GetMaxFlow() {
		int[] distances = new int[vertlst.size()];
		
		int flow = 0;
	    
	    while (DinicBfs(distances)) {
	      int[] ptr = new int[vertlst.size()];
	      while (true) {
	        int df = DinicDfs(source, target, ptr, distances,2147483646);
	        if (df == 0)
	          break;
	        flow += df;
	      }
	    }
	    return flow;
	}
}
