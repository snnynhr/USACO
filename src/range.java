import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: sunnyna1
LANG: JAVA
PROG: range
 */
public class range {
	static int N,iter=0;
	static int grid[][];
	static int[] rect = new int[251];
	static boolean[][] visited;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("range.in"));
		N = Integer.parseInt(in.readLine());
		grid = new int[N][N];
		visited = new boolean[N][N];
		for(int i=0; i<N; i++)
		{
			String s =in.readLine();
			for(int j=0; j<N; j++)
				grid[i][j] = s.charAt(j)-48;
		}
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		for(int i=0; i<N-1; i++)
			for(int j=0; j<N-1; j++)
			{
				int res = search(i,j,0)+1;
				if(res>1) 
				{
					for(int k=2; k<=res; k++)
						rect[k]++;
				}
			}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("range.out")));
		for(int i=2; i<rect.length; i++)
			if(rect[i]>0) out.println(i+" "+rect[i]);
		System.out.println("Iterations: "+iter);
		in.close();
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
	public static int search(int i,int j, int s)
	{
		boolean d=true;
		boolean size= true;
		if(i+s+1==N) size = false;
		for(int k=j; k<j+s; k++)
		{
			iter++;
			if(grid[i+s][k]==0) d = false;
		}
		if(j+s+1==N) size = false;
		for(int k=i; k<i+s; k++)
		{
			iter++;
			if(grid[k][j+s]==0) d = false;
		}
		if(grid[i+s][j+s]==0) d = false;
		if(d&&size)
			return search(i,j,s+1);
		else if(!d)
			return s-1;
		else return s;
	}
}