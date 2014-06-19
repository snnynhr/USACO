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
PROG: partition
 */
public class partition {
	static int N,K;
	static int[] grid;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("partition.in"));
		StringTokenizer s = new StringTokenizer(in.readLine());
		N = Integer.parseInt(s.nextToken());
		K = Integer.parseInt(s.nextToken());
		int[][] grid = new int[N][N];
		for(int i=0; i<N; i++)
		{
			s = new StringTokenizer(in.readLine());
			for(int j=0; j<N; j++)
				grid[i][j] = Integer.parseInt(s.nextToken());
		}
		int sum=0;
		for(int i=0; i<grid.length; i++)
			for(int j=0; j<grid[i].length; j++)
				sum+=grid[i][j];
		in.close();
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("partition.out")));
		out.println(sum/4);
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
	public static void first_combination(int item[], int n)
	{
	    for (int i = 0; i < n; ++i) {
	        item[i] = i;
	    }
	}

	public static boolean next_combination(int item[], int n, int N)
	{
	    for (int i = 1; i <= n; ++i) {
	        if (item[n-i] < N-i) {
	            ++item[n-i];
	            for (int j = n-i+1; j < n; ++j) {
	                item[j] = item[j-1] + 1;
	            }
	            return true;
	        }
	    }
	    return false;
	}
}