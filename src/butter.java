import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
ID: sunnyna1
LANG: JAVA
PROG: butter
 */
/*
 * Key here is that the graph is sparse - Dijkstra's runs faster.
 * 
 */
public class butter {
	static int N,P,C;
	static int[] cowpos;
	static int[][] distTo;
	static butter main = new butter();
	static int currInd,iter=0;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("butter.in"));
		StringTokenizer s = new StringTokenizer(in.readLine());
		N = Integer.parseInt(s.nextToken());
		P = Integer.parseInt(s.nextToken());
		C = Integer.parseInt(s.nextToken());
		V[] v = new V[P+1];
		for(int i=1; i<=P; i++)
			v[i]=main.new V(i);
		v[0]=null;
		for(int i=0; i<N; i++)
			v[Integer.parseInt(in.readLine())].num++;
		for(int i=0; i<C; i++)
		{
			s = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(s.nextToken());
			int b = Integer.parseInt(s.nextToken());
			int c = Integer.parseInt(s.nextToken());
			v[a].adj.add(v[b]);
			v[a].dist.add(c);
			v[b].adj.add(v[a]);
			v[b].dist.add(c);
		}
		in.close();
		//dijkstra
		distTo = new int[P+1][P+1];
		for(int i=1; i<distTo.length; i++)
			for(int j=1; j<distTo.length; j++)
				distTo[i][j] = Integer.MAX_VALUE/10;
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		for(V w: v)
		{
			if(w!=null && w.num>0)
			{   
				currInd = w.index;
				distTo[w.index][w.index]=0;
				boolean visited[] = new boolean[P+1];
				PriorityQueue<V> q = new PriorityQueue<V>();
				q.add(w);
				while(!q.isEmpty())
				{
					V x = q.poll();
					if(!visited[x.index])
					{
						visited[x.index]=true;
						for(int i=0; i<x.adj.size(); i++)
						{
							iter++;
							distTo[w.index][x.adj.get(i).index] = Math.min(distTo[w.index][x.index]+x.dist.get(i),distTo[w.index][x.adj.get(i).index]);
							if(!visited[x.adj.get(i).index])
							{
								q.add(x.adj.get(i));
							}
						}
					}
				}
			}
		}
		int minSum = Integer.MAX_VALUE;
		for(int i=1; i<v.length; i++)
		{
			int sum=0;
			for(int j=1; j<v.length; j++)
				sum+=v[j].num*distTo[j][i];
			minSum = Math.min(minSum,sum);
		}
		System.out.println("iter "+iter);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));
		System.out.println(minSum);
		out.println(minSum);
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
	public class V implements Comparable<V>
	{
		private int index;
		private ArrayList<V> adj = new ArrayList<butter.V>();
		private ArrayList<Integer> dist = new ArrayList<Integer>();
		private int num=0;
		public V(int a)
		{
			index=a;
		}
		public int compareTo(V o) {
			if(distTo[currInd][o.index]>distTo[currInd][this.index]) return -1;
			else if (distTo[currInd][o.index]<distTo[currInd][this.index]) return 1;
			return 0;
		}
	}
}
/*
 * Obviously Floyd Warshall is too slow..
 *
public class butter {
	static int N,P,C;
	static int[] cowpos;
	static int[][] adj;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("butter.in"));
		N = in.nextInt();
		P = in.nextInt();
		C = in.nextInt();
		cowpos = new int[P+1];
		for(int i=0; i<N; i++)
			cowpos[in.nextInt()]++;
		adj = new int[P+1][P+1];
		for(int i=0; i<=P; i++)
			for(int j=0; j<=P; j++)
				adj[i][j] = Integer.MAX_VALUE/3;
		for(int i=0; i<C; i++)
		{
			int a = in.nextInt(); int b = in.nextInt(); int c = in.nextInt();
			adj[a][b]=c;
			adj[b][a]=c;
		}
		in.close(); 
		for (int k = 1; k <=P; k++) 
			for (int i = 1; i <=P; i++) 
				for (int j = 1; j <=P; j++) { 
					if (i!= j) { 
						adj[i][j] = Math.min (adj[i][j],adj[i][k] + adj[k][j]); 
					} 
				}
		int pasture = 0;
		int minDist = Integer.MAX_VALUE;
		int totalDist;
		for(int i=1; i<=P; i++)
		{
			totalDist=0;
			for(int j=1; j<=P; j++)
			{
				if(j!=i)
					totalDist += adj[i][j]*cowpos[j];
			}
			if(totalDist<minDist)
			{
				minDist = totalDist;
				pasture = i;
			}
		}
		System.out.println(pasture);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("butter.out")));
		System.out.println(minDist);
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
}*/