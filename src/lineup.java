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
PROG: lineup
 */
public class lineup {
	static int N,K;
	static int[] grid;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("lineup.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		grid = new int[100000];
		for(int i=0; i<N; i++)
		{
			int a = Integer.parseInt(in.readLine());
			grid[a]++;
		}
		int max=0;
		for(int i=0; i<100000; i++)
			max = Math.max(max,grid[i]);
		in.close();
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lineup.out")));
		out.println(max-1);
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
}