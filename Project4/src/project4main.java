import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Stream;

public class project4main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		
		//My graph 
		int id=0;
		ArrayList<Vertice> vertList= new ArrayList<Vertice>();
		
		File inFile = new File(args[0]);
		File outFile = new File(args[1]);
		
		Scanner in = new Scanner(inFile);
		PrintStream out = new PrintStream(outFile);
		
		
		Vertice SourceV = new Vertice(id);
		id++;
		Vertice TargetV = new Vertice(id);
		id++;
		
		vertList.add(SourceV);
		vertList.add(TargetV);
		
		//Yeşil trenler ve kapasiteleri
		int nGreenTrains=Integer.parseInt(in.nextLine());
		String[] strarr= in.nextLine().split(" ");
		if(nGreenTrains!=0) {
			
			
			
			for (int i = 0; i < strarr.length; i++) {
				Vertice v = new Vertice(id);
				id++;
				int cap =Integer.parseInt(strarr[i]);
				Edge e = new Edge(v,TargetV,cap);
				v.adjList.add(e);
				vertList.add(v);
				
			}
		}
		
		//Kırmızı trenler ve kapasiteleri
		int nRedTrains=Integer.parseInt(in.nextLine());
		String[] temparr1= in.nextLine().split(" ");
		if(nRedTrains!=0) {
			
			for (int i = 0; i < temparr1.length; i++) {
				Vertice v = new Vertice(id);
				id++;
				int cap =Integer.parseInt(temparr1[i]);
				Edge e = new Edge(v,TargetV,cap);
				v.adjList.add(e);
				vertList.add(v);
			}
			
		}
		//yeşil geyik ve kapasitlerei
		int nGreenDeers= Integer.parseInt(in.nextLine());
		String[] temparr = in.nextLine().split(" ");
		if(nGreenDeers!=0) {

			
			for (int i = 0; i < temparr.length; i++) {
				Vertice v = new Vertice(id);
				id++;
				int cap =Integer.parseInt(temparr[i]);
				Edge e = new Edge(v,TargetV,cap);
				v.adjList.add(e);
				vertList.add(v);
				
			}
		}
		//kırmızı geyik ve kapasiteleri
		int nRedDeers= Integer.parseInt(in.nextLine());
		String[] temparr2 = in.nextLine().split(" ");
		if(nRedDeers!=0) {
			
			
			for (int i = 0; i < temparr2.length; i++) {
				
				Vertice v = new Vertice(id);
				id++;
				int cap =Integer.parseInt(temparr2[i]);
				Edge e = new Edge(v,TargetV,cap);
				v.adjList.add(e);
				vertList.add(v);
				
				
			}
		}
		
		int nBagType= Integer.parseInt(in.nextLine());
		
		String[] bagArr= in.nextLine().split(" ");
		
		int greenTrainStart=2;
		int redTrainStart=greenTrainStart+nGreenTrains;
		int greenDeerStart= redTrainStart+nRedTrains;
		int redDeerStart= greenDeerStart+nGreenDeers;
		
		
		int giftnumber = 0;
		
		
		
		
		for(int i = 0; i<2*nBagType; i=i+2) {
			
			Vertice v = new Vertice(id);
			int cap = Integer.parseInt(bagArr[i+1]);
			giftnumber+=cap;
			id++;
			
			//her paketi source a bağla
			Edge e = new Edge(SourceV,v,cap);
			SourceV.adjList.add(e);
			
			boolean aflag= bagArr[i].contains("a");
			if(bagArr[i].contains("d")) {
				
				
				if(bagArr[i].contains("b")) {
					// yeşil tren
					
					
					
					if(aflag) {
						for(int k = greenTrainStart;k< redTrainStart;k++) {
							
							Vertice targetVertice = vertList.get(k);
							Edge edg=new Edge(v,targetVertice,1);
							v.adjList.add(edg);
							Edge rev= new Edge(targetVertice,v,0);
							targetVertice.adjList.add(rev);
							
						}
						vertList.add(v);
						
					}
					else {
						
						for(int k = greenTrainStart;k< redTrainStart;k++) {
							
							Vertice targetVertice = vertList.get(k);
							Edge edg=new Edge(v,targetVertice, cap);
							v.adjList.add(edg);
							Edge rev= new Edge(targetVertice,v,0);
							targetVertice.adjList.add(rev);
							
						}
						vertList.add(v);
					}
					
				}
				else if(bagArr[i].contains("c")) {
					//kırmızı tren
					
					
					if(aflag) {
						for(int k = redTrainStart;k< greenDeerStart;k++) {
							
							Vertice targetVertice = vertList.get(k);
							Edge edg=new Edge(v,targetVertice,1);
							v.adjList.add(edg);
							Edge rev= new Edge(targetVertice,v,0);
							targetVertice.adjList.add(rev);
							
							
						}
						vertList.add(v);
						
					}
					else {
						
						for(int k = redTrainStart;k< greenDeerStart;k++) {
							
							Vertice targetVertice = vertList.get(k);
							Edge edg=new Edge(v,targetVertice,cap);
							v.adjList.add(edg);
							Edge rev= new Edge(targetVertice,v,0);
							targetVertice.adjList.add(rev);
						}
						
						vertList.add(v);
					}
				}
				else {
					//herhangi bir yere tren
					
					
					
					if(aflag) {
						for(int k = greenTrainStart;k< greenDeerStart;k++) {
							
							Vertice targetVertice = vertList.get(k);
							Edge edg=new Edge(v,targetVertice,1);
							v.adjList.add(edg);
							Edge rev= new Edge(targetVertice,v,0);
							targetVertice.adjList.add(rev);
							
						}
						vertList.add(v);
						
					}
					else {
						
						for(int k = greenTrainStart;k< greenDeerStart;k++) {
							
							Vertice targetVertice = vertList.get(k);
							Edge edg=new Edge(v,targetVertice,cap);
							v.adjList.add(edg);
							Edge rev= new Edge(targetVertice,v,0);
							targetVertice.adjList.add(rev);
						}
						
						vertList.add(v);
					}
					
				}
				
				
			}
			else if(bagArr[i].contains("e")) {
				
				
				if(bagArr[i].contains("b")) {
					// yeşil geyik
					
					
					if(aflag) {
						for(int k = greenDeerStart;k< redDeerStart;k++) {
							
							Vertice targetVertice = vertList.get(k);
							Edge edg=new Edge(v,targetVertice,1);
							v.adjList.add(edg);
							Edge rev= new Edge(targetVertice,v,0);
							targetVertice.adjList.add(rev);
							
						}
						vertList.add(v);
						
					}
					else {
						
						for(int k = greenDeerStart;k< redDeerStart;k++) {
							
							Vertice targetVertice = vertList.get(k);
							Edge edg=new Edge(v,targetVertice,cap);
							v.adjList.add(edg);
							Edge rev= new Edge(targetVertice,v,0);
							targetVertice.adjList.add(rev);
						}
						
						vertList.add(v);
					}
					
				}
				else if(bagArr[i].contains("c")) {
					//kırmızı geyik
					
					if(aflag) {
						for(int k = redDeerStart;k< redDeerStart+nRedDeers;k++) {
							
							Vertice targetVertice = vertList.get(k);
							Edge edg=new Edge(v,targetVertice,1);
							v.adjList.add(edg);
							Edge rev= new Edge(targetVertice,v,0);
							targetVertice.adjList.add(rev);
							
						}
						vertList.add(v);
						
					}
					else {
						
						for(int k = redDeerStart;k< redDeerStart+nRedDeers;k++) {
							
							Vertice targetVertice = vertList.get(k);
							Edge edg=new Edge(v,targetVertice,cap);
							v.adjList.add(edg);
							Edge rev= new Edge(targetVertice,v,0);
							targetVertice.adjList.add(rev);
						}
						
						vertList.add(v);
					}
					
				}
				else {
					// herhangi bir yere geyik
					
					if(aflag) {
						for(int k = greenDeerStart;k< redDeerStart+nRedDeers;k++) {
							
							Vertice targetVertice = vertList.get(k);
							Edge edg=new Edge(v,targetVertice,1);
							v.adjList.add(edg);
							Edge rev= new Edge(targetVertice,v,0);
							targetVertice.adjList.add(rev);
							
						}
						vertList.add(v);
						
					}
					else {
						
						for(int k = greenDeerStart;k< redDeerStart+nRedDeers;k++) {
							
							Vertice targetVertice = vertList.get(k);
							Edge edg=new Edge(v,targetVertice,cap);
							v.adjList.add(edg);
							Edge rev= new Edge(targetVertice,v,0);
							targetVertice.adjList.add(rev);
						}
						
						vertList.add(v);
					}
					
				}
				
				
			}else {
				//herhangi bir araçla gidebilir
				if(bagArr[i].contains("b")) {
					//yeşile herhangi bir şekilde 

					
					if(aflag) {
						for(int k = greenTrainStart;k< redTrainStart;k++) {
							
							Vertice targetVertice = vertList.get(k);
							Edge edg=new Edge(v,targetVertice,1);
							v.adjList.add(edg);
							Edge rev= new Edge(targetVertice,v,0);
							targetVertice.adjList.add(rev);
							
						}
						for(int k = greenDeerStart;k< redDeerStart;k++) {
							
							Vertice targetVertice = vertList.get(k);
							Edge edg=new Edge(v,targetVertice,1);
							v.adjList.add(edg);
							Edge rev= new Edge(targetVertice,v,0);
							targetVertice.adjList.add(rev);
							
						}
						vertList.add(v);
						
					}
					else {
						
						for(int k = greenTrainStart;k< redTrainStart;k++) {
							
							Vertice targetVertice = vertList.get(k);
							Edge edg=new Edge(v,targetVertice,cap);
							v.adjList.add(edg);
							Edge rev= new Edge(targetVertice,v,0);
							targetVertice.adjList.add(rev);
							
						}
						for(int k = greenDeerStart;k< redDeerStart;k++) {
							
							Vertice targetVertice = vertList.get(k);
							Edge edg=new Edge(v,targetVertice,cap);
							v.adjList.add(edg);
							Edge rev= new Edge(targetVertice,v,0);
							targetVertice.adjList.add(rev);
							
						}
						
						vertList.add(v);
					}
					
				}
				else if(bagArr[i].contains("c")) {
					//kırmızıya herhangi bir şekilde
					
					
					if(aflag) {
						for(int k = redTrainStart;k< greenDeerStart;k++) {
							
							Vertice targetVertice = vertList.get(k);
							Edge edg=new Edge(v,targetVertice,1);
							v.adjList.add(edg);
							Edge rev= new Edge(targetVertice,v,0);
							targetVertice.adjList.add(rev);
							
						}
						for(int k = redDeerStart;k< redDeerStart+nRedDeers;k++) {
							
							Vertice targetVertice = vertList.get(k);
							Edge edg=new Edge(v,targetVertice,1);
							v.adjList.add(edg);
							Edge rev= new Edge(targetVertice,v,0);
							targetVertice.adjList.add(rev);
							
						}
						vertList.add(v);
						
					}
					else {
						
						for(int k = redTrainStart;k< greenDeerStart;k++) {
							
							Vertice targetVertice = vertList.get(k);
							Edge edg=new Edge(v,targetVertice,cap);
							v.adjList.add(edg);
							Edge rev= new Edge(targetVertice,v,0);
							targetVertice.adjList.add(rev);
							
						}
						for(int k = redDeerStart;k< redDeerStart+nRedDeers;k++) {
							
							Vertice targetVertice = vertList.get(k);
							Edge edg=new Edge(v,targetVertice,cap);
							v.adjList.add(edg);
							Edge rev= new Edge(targetVertice,v,0);
							targetVertice.adjList.add(rev);
							
						}
						vertList.add(v);
					}
					
					
				}
				else {
					
					//öyle bir input olmayacak yani herhangi bir yere herhangi bir şekilde gidebilir
					
					// sadece a seçeneği olabilir onu değerlendir
					if(aflag) {
						//herhangi bir yere herhangi bir araçla sadece a etiketine göre gider
						for(int k =greenTrainStart ;k< redDeerStart+nRedDeers ;k++) {
							
							Vertice targetVertice = vertList.get(k);
							Edge edg=new Edge(v,targetVertice,1);
							v.adjList.add(edg);
							Edge rev= new Edge(targetVertice,v,0);
							targetVertice.adjList.add(rev);
							
						}
						vertList.add(v);
					}
					else {
						//hiçbir etiket olmayan testcase olmayacak
					}
				}
			}
		}
		
		
		
		 
		// bağlantılar yapıldı
		// graph hazır arraylist içinde tutuyorum
		//Dinic içinde dinic kullanıyorum çağırıp maxflow alıyorum 
		
		
		Dinic g= new Dinic(vertList);
		
		int maxflow = g.GetMaxFlow();
		out.print(giftnumber-maxflow);
		
		
		
		
	}
	
	
	
	
	
	

}
