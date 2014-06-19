import java.io.*;
import java.util.*;
/*ID: sunnyna1
LANG: JAVA
TASK: rblock
 */
/*public class rblock {
	static int N;
	static int M;
	static int[][] paths;
	static StringTokenizer z;
	static ArrayList<Integer> path = new ArrayList<Integer>();
	public static void main(String args[]) throws IOException
	{
		//long time = System.currentTimeMillis(); // why check the fucking time
		BufferedReader in = new BufferedReader(new FileReader("rblock.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rblock.out")));
		z = new StringTokenizer(in.readLine());
		N=Integer.parseInt(z.nextToken());
		M=Integer.parseInt(z.nextToken());
		paths = new int[N][N];
		for(int i=0; i<M; i++)
		{
			z = new StringTokenizer(in.readLine());
			int a=Integer.parseInt(z.nextToken());
			int b=Integer.parseInt(z.nextToken());
			int l=Integer.parseInt(z.nextToken());
			paths[a-1][b-1]=l;
			paths[b-1][a-1]=l;
		}
		int original = getShortestPath(0,N-1);
		int min = original;
		for(int i=0; i<path.size()-1; i++)
		{
			int a=path.get(i);
			int b=path.get(i+1);
			paths[a][b]*=2;
			paths[b][a]*=2;
			min=Math.min(min,getShortestPath(0,N-1));
			paths[a][b]/=2;
			paths[b][a]/=2;
		}
		System.out.println(original);
		System.out.println(min);
		System.out.println(min-original);
		out.close();
	}
	public static int getShortestPath(int start, int end)
	{
		ArrayList<Integer> dist = new ArrayList<Integer>(N);
		ArrayList<Integer> prev = new ArrayList<Integer>(N);
		ArrayList<Boolean> visited = new ArrayList<Boolean>(N);
		for(int i=0; i<N; i++)
		{
			dist.add(i,Integer.MAX_VALUE);
			prev.add(i, -1);
			visited.add(i,false);
		}
		dist.set(0, 0);
		System.out.println(dist.toString()+N);
		while (true)
	    {
	        int close = -1;

	        for (int i = 0; i < N; i++)
	            if (!visited.get(i) && (close == -1 || dist.get(i) < dist.get(close)))
	                close = i;

	        if (close == -1)
	            break;

	        visited.set(close, true);

	        for (int i = 0; i < N; i++)
	        {
	            int ndist = dist.get(close) + paths[close][i];

	            if (ndist < dist.get(i))
	            {
	                dist.set(i,ndist);
	                prev.set(i,close);
	            }
	        }
	    }
		
	    return dist.get(end);
	}
}
*/


public class rblock {
	static int N;
	static int M;
	static int[] sp;
	static Queue<Edge> djk;
	static Vertex[] graph;
	static int first, second;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("rblock.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rblock.out")));
		StringTokenizer z = new StringTokenizer(in.readLine());
		N=Integer.parseInt(z.nextToken());
		M=Integer.parseInt(z.nextToken());
		graph = new Vertex[N];
		for(int i=0; i<N; i++)
		{
			graph[i]= new Vertex((i+1)+"");
			graph[i].adjacencies = new ArrayList<Edge>();
		}
		for(int i=0; i<M; i++)
		{
			z = new StringTokenizer(in.readLine());
			int a=Integer.parseInt(z.nextToken());
			int b=Integer.parseInt(z.nextToken());
			int w=Integer.parseInt(z.nextToken());
			graph[a-1].adjacencies.add(new Edge(graph[b-1],w));
		}
		Dijkstra.computePaths(graph[0]);
		for (Vertex v : graph)
		{
			first=v.minDistance;
		}
		ArrayList<Vertex> path = Dijkstra.getShortestPathTo(graph[graph.length-1]);
		int max=0;
		int min_i=0, min_j=0;
		for(int i=0; i<path.size()-1; i++)
		{
			for(int j=0; j<path.get(i).adjacencies.size(); j++)
			{
				if(path.get(i).adjacencies.get(j).target==path.get(i+1))
				{
					if(path.get(i).adjacencies.get(j).weight>max)
					{
						max =path.get(i).adjacencies.get(j).weight;
						min_i=i;
						min_j=j;
					}
				}
			}
		}
		graph[Integer.parseInt(path.get(min_i).toString())-1].adjacencies.get(min_j).weight*=2;
		
		for(int i=0; i<graph.length; i++)
		{
			graph[i].minDistance=Integer.MAX_VALUE;
		}
		Dijkstra.computePaths(graph[0]);
		for (Vertex v : graph)
		{
			second= v.minDistance;
			//ArrayList<Vertex> path1 = Dijkstra.getShortestPathTo(v);
			//System.out.println("Path: " + path1);
		}
		second=graph[graph.length-1].minDistance;
		System.out.println(second-first);
		out.close();
		System.out.println(System.currentTimeMillis()-time);
		System.exit(0);
	}
}

class Vertex implements Comparable<Vertex>
{
	public final String name;
	public ArrayList<Edge> adjacencies;
	public int minDistance = Integer.MAX_VALUE;
	public Vertex previous;
	public Vertex(String argName) { name = argName; }
	public String toString() { return name; }
	public int compareTo(Vertex other)
	{
		if(minDistance <other.minDistance) return -1;
		if(minDistance >other.minDistance) return 1;
		else return 0;
	}
}

class Edge
{
	public final Vertex target;
	public int weight;
	public Edge(Vertex argTarget, int argWeight)
	{ target = argTarget; weight = argWeight; }
}

class Dijkstra
{
	public static void computePaths(Vertex source)
	{
		source.minDistance = 0;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();

			for (int i=0; i<u.adjacencies.size(); i++)
			{
				Edge e=u.adjacencies.get(i);
				Vertex v = e.target;
				int weight = e.weight;
				int distanceThroughU = u.minDistance + weight;
				if (distanceThroughU < v.minDistance) {
					vertexQueue.remove(v);
					v.minDistance = distanceThroughU ;
					v.previous = u;
					vertexQueue.add(v);
				}
			}
		}
	}

	public static ArrayList<Vertex> getShortestPathTo(Vertex target)
	{
		ArrayList<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
			path.add(vertex);
		Collections.reverse(path);
		return path;
	}
}