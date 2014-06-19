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
PROG: taxi
 */
public class taxi {
	static int N,M;
	static int grid[][];
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("taxi.in"));
		StringTokenizer s = new StringTokenizer(in.readLine());
		N = Integer.parseInt(s.nextToken());
		M = Integer.parseInt(s.nextToken());
		grid = new int[N][2];
		int maxl=0;
		for(int i=0; i<N; i++)
		{
			s = new StringTokenizer(in.readLine());
			grid[i][0] = Integer.parseInt(s.nextToken());
			grid[i][1] = Integer.parseInt(s.nextToken());
			maxl = Math.max(grid[i][1],maxl);
		}
		long sum=0;
		int l = grid[0][0];
		int r = grid[0][1];
		sum+=Math.abs(l-r);
		for(int i=1; i<N; i++)
		{
			int a = grid[i][0];
			int b = grid[i][1];
			if(Math.max(a,b)<=r && Math.min(a, b)>=l) sum+=2*(Math.abs(a-b));
			else if(Math.min(a, b)>=r)
			{
				sum+=Math.abs(a-b)+Math.min(a,b)-r;
				r = Math.max(a, b);
			}
			else if(Math.min(a, b)<=r && Math.max(a,b)>=r)
			{
				sum+=2*Math.abs(a-r);
				r=Math.max(a,b);
			}
		}
		sum+=M-maxl;
		in.close();
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("taxi.out")));
		out.println(sum);
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
}