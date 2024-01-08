package graphSearch;
import java.util.HashSet;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import graphClient.XGraphClient;

public class GraphSearch {
	
	public int afm = 58731; 
	public String firstname = "GEORGIOS";
	public String lastname = "POULIOS";
	static public int flag = 0;
	XGraphClient xgraph;

	static HashSet edges = new HashSet<SEdge>();
	public static int edgeCounter(SEdge a){

		if(!edges.contains(a)){
			edges.add(a);
			flag++;
			return 1;
		}
		return 0;
	}
	public static int maxOfList(List a){
		int max = 0;


		return max;
	}
	
	
	public GraphSearch(XGraphClient xgraph) {
		this.xgraph = xgraph;
	}


	public Result findResults() {
		LinkedList discovered = new LinkedList<>();
		long Node = xgraph.firstNode();
		SGraph gSGraph = new SGraph();
		Queue<Long> q = new LinkedList<>();
		long w = 0;
		long[] neighbors = xgraph.getNeighborsOf(Node);
		for(int l = 0;l<neighbors.length;l++){
			q.add(neighbors[l]);
			gSGraph.addEdge(new SEdge(Node,neighbors[l],xgraph.getEdgeWeight(Node, neighbors[l])));
		}
		SEdge heaviestEdge = new SEdge(Node,neighbors[0]);
		int st;
		while(q.size()>0){
			Node = q.remove();
			neighbors = xgraph.getNeighborsOf(Node);
				for(int i = 0;i<neighbors.length;i++){
					if(discovered.contains(neighbors[i])) continue;

					st = edgeCounter(new SEdge(Node, neighbors[i]));
					if(st==1) gSGraph.addEdge(new SEdge(Node,neighbors[i],xgraph.getEdgeWeight(Node, neighbors[i])));
					q.add(neighbors[i]);
					
					if(xgraph.getEdgeWeight(Node, neighbors[i])>w) {
						heaviestEdge.nodeOne = Node;
						heaviestEdge.nodeTwo = neighbors[i];
						heaviestEdge.weight = xgraph.getEdgeWeight(Node, neighbors[i]);
						w = xgraph.getEdgeWeight(Node, neighbors[i]);
					}

				}
				if(!discovered.contains(Node)){
				discovered.addLast(Node);
	
				}
				
		}
	
		Result res = new Result();
		res.n = discovered.size();
		res.m = flag;
		
		res.heaviestEdge = heaviestEdge;
		res.sGraph = gSGraph;
		return res;
	}

}
