import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/*
ID: sunnyna1
LANG: JAVA
PROG: agrinet
 */
public class agrinet {
	static int N;
	static int[][] grid;
	static int[] dist;
	static boolean inTree[];
	static int[] source;
	static int currSize = 0;
	static int weightSum=0;
	public static void main(String args[]) throws IOException
	{
		Scanner in = new Scanner(new FileReader("agrinet.in"));
		N=in.nextInt();
		grid = new int[N][N];
		source = new int[N];
		dist = new int[N];
		inTree = new boolean[N];
		for(int i=0; i<N; i++)
		{
			dist[i]=Integer.MAX_VALUE;
			source[i] = -1;
		}
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				grid[i][j] = in.nextInt();
		int i=0;
		while(currSize<N)
		{
			int min = Integer.MAX_VALUE;
			int minI =-1;
			inTree[i]=true;
			for(int j=0; j<N; j++)
			{
				if(i!=j & !inTree[j])
				{
					if(grid[i][j]<dist[j])
					{
						source[j]=i;
						dist[j]=grid[i][j];
					}
					if(dist[j]<min)
					{
						min=dist[j];
						minI=j;
					}
				}
			}
			i=minI;
			currSize++;
		}
		for(i=1; i<dist.length; i++)
			weightSum+=dist[i];
		in.close();
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));
		out.println(weightSum);
		out.close();
		System.exit(0);
	}
}