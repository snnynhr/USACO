import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
ID: sunnyna1
LANG: JAVA
PROG: fence
 */
public class fence {
	static int[] circuit;
	static int F;
	static int adj[][];
	static int circuitpos=0;
	static boolean visited[]; 
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("fence.in"));
		F = Integer.parseInt(in.readLine());
		adj = new int[501][501];
		int min = Integer.MAX_VALUE;
		for(int i=0; i<F; i++)
		{
			StringTokenizer st  = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a][b]++;
			adj[b][a]++;
			min=Math.min(min,Math.min(a,b));
		}	
		in.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		circuit = new int[F+1];
		visited = new boolean[F+1];
		for(int i=0; i<=500; i++)
		{
			int count=0;
			for(int j=0; j<=500; j++)
			{
				count+=adj[i][j];
			}
			if(count%2==1) 
			{
				min = i;
				break;
			}
		}
		findCircuit(min);
		System.out.println(Arrays.toString(circuit));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence.out")));
		for(int i=F; i>=0; i--)
			out.println(circuit[i]);
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
	public static void findCircuit(int node)
	{
		for(int i=0; i<=500; i++)
			if(adj[node][i]>0)
			{
				adj[node][i]--;
				adj[i][node]--;
				findCircuit(i);
			}
		circuit[circuitpos++]=node;
	}
}