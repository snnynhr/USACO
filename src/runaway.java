import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
/*
ID: sunnyna1
LANG: JAVA
PROG: runaway
 */
public class runaway {
	static int N;
	static long L;
	static long[] path, sp;
	static int[] neighbor;
	static int[] num;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("runaway.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Long.parseLong(st.nextToken());
		path = new long[N+1];
		neighbor = new int[N+1];
		sp = new long[N+1];
		num =new int[N+1];
		for(int i=2; i<=N; i++)
		{
			st = new StringTokenizer(in.readLine());
			int ne = Integer.parseInt(st.nextToken());
			long length = Long.parseLong(st.nextToken());
			path[i] = length;
			neighbor[i] = ne;
			sp[i]=-1;
		}
		for(int i=2; i<=N; i++)
			sp[i] = sp[neighbor[i]] + path[i];
		for(int i=1; i<=N; i++)
			dfs(i,0);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runaway.out")));
		for(int i=1; i<=N; i++)
			System.out.println(num[i]);
		System.out.println("RUNTIME: "+(System.currentTimeMillis()-time));
		out.close();
		System.exit(0);
	}
	public static void dfs(int curr, long d)
	{
		if(curr==0) return;
		if(d<=L) 
		{
			num[curr]++;
			dfs(neighbor[curr],path[curr]+d);	
		}
	}
}