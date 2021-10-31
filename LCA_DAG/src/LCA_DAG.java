import java.util.*;

public class LCA_DAG
{
	
	private int vertex;					
	private int edge;	
	private boolean[] visited;
	private ArrayList<Integer>[]adjacent;	
	private int[] degree;					
					
	private boolean cycle;	
	private boolean[] stack;
	
	
		public int V(){
			return vertex;
		}
		
		public int E(){
			return edge;
		}
	
	public LCA_DAG(int vertex){
		if(vertex < 0){
			throw new IllegalArgumentException("Count of vertices > 0");
		}
		
		this.vertex = vertex;
		this.edge = 0;
		degree = new int[vertex];
		visited = new boolean[vertex];
		stack = new boolean[vertex];
		adjacent = (ArrayList<Integer>[]) new ArrayList[vertex];
		
		for(int v=0; v<vertex; v++){
			adjacent[v] = new ArrayList<Integer>();
		}
	}
	
	public Iterable<Integer> adjacent(int v){
		return adjacent[v];
	}
	
	public boolean cycle(){
		return cycle;
	}
	
	boolean validate(int v){
		if(v < 0 || v >= vertex){ return false; }
		
		return true;
	}
	
	public int degreeMain(int v){
		if(validate(v)){
			return degree[v];
		}
		else
			return -1;
		}
	
	public int outdegree(int v){
		if(validate(v)){
			return adjacent[v].size();
		}
			return -1;
	}

	public void addEdge(int u, int v){
		if(validate(u) && validate(v)){
			adjacent[u].add(v);
			degree[v]++;
			edge++;
		}
		else{ System.out.println("Include figures from 1 to " + (vertex-1)); }		
	}
	
	public void cycle(int v){
		stack[v] = true;
		visited[v] = true;
		
		for(int u:adjacent(v)){
			if(!visited[u]){
				cycle(u);
			}
			
			else if(stack[u]){
				cycle = true;
				return;
			}
		}
		stack[v] = false;
	}
	
	public ArrayList<Integer> breadthFirst(int source) 				{
		ArrayList<Integer> arr = new ArrayList<Integer>();
		LinkedList<Integer> inQueue = new LinkedList<Integer>();
		boolean mark[] = new boolean[vertex]; 						
		
		mark[source] = true;
		inQueue.add(source);
		
		while(inQueue.size()!=0){
			source = inQueue.poll(); 								
			arr.add(source);
			Iterator<Integer> i = adjacent[source].listIterator();	
			
			while(i.hasNext()){
				int n = i.next();
				if(!mark[n])
				{
					mark[n] = true;
					inQueue.add(n);
				}
			}
		}
		return arr;
	}
	
	public LCA_DAG reverseDAG(){
		LCA_DAG reverseDAG = new LCA_DAG(vertex);
		for(int v = 0; v <vertex; v++)
		{
			for(int u:adjacent(v))
			{
				reverseDAG.addEdge(u, v);
			}		
		}
		return reverseDAG;
	}
	
	public int findLCA(int u, int v){
		cycle(0);
		
		if(cycle){
			return -1;
		}
		else if(!validate(u) || !validate(v)){
			return -1;
		}
		else if(edge == 0) {
			return -1;
		}
		
		LCA_DAG reverseDAG = reverseDAG();
		
		ArrayList<Integer> inCommon = new ArrayList<Integer>();
		ArrayList<Integer> a1 = reverseDAG.breadthFirst(u);
		ArrayList<Integer> a2 = reverseDAG.breadthFirst(v);
		
		boolean isFound = false;
		
		for(int i = 0; i<a1.size(); i++){
			for(int j = 0; j<a2.size(); j++){
				if(a1.get(i) == a2.get(j)){
					inCommon.add(a1.get(i));
					isFound = true;
				}
			}
		}
		if(isFound)	{
			return inCommon.get(0);
		}
		return -1;
	}
}