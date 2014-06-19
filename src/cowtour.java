import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/*
ID: sunnyna1
LANG: JAVA
PROG: cowtour
 */
public class cowtour {
	static int N;
	static int[][] conn;
	static double[][] adj;
	static int[][] v;
	static int[] color;
	static double[] mp;
	static double[] d;
	public static void main(String args[]) throws IOException
	{	
		Scanner in = new Scanner(new FileReader("cowtour.in"));
		N=in.nextInt();
		conn = new int[N][N];
		adj = new double[N][N];
		v = new int[N][2];
		color = new int[N];
		mp = new double[N+1];
		d = new double[N+1];
		double minD = Double.MAX_VALUE; 
		for(int i=0; i<N; i++)
		{
			v[i][0]=in.nextInt();;
			v[i][1]=in.nextInt();;
		}
		for(int i=0; i<N; i++)
		{
			String s= in.next();
			for(int j=0; j<N; j++)
				conn[i][j]=s.charAt(j)-'0';
		}
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
			{
				if(conn[i][j]!=0)
				{
					adj[i][j] = dist(i,j);
				}
				else
					adj[i][j] = Double.MAX_VALUE;
			}
		floydWarshall();
		int c=1;
		for(int i=0; i<N; i++)
			if(color[i]==0)
				floodFillColor(i, c++);

		for (int i = 0; i <N; i++) { 
			mp [i] =   0; 
			for (int j = 0; j <N; j++) { 
				if (i!= j && color [i] == color [j]) { 
					mp [i] = Math.max(mp [i], adj[i] [j]); 
				} 
			} 
			d[color [i]] = Math.max (d[color [i]], mp[i]); 
		}
		for (int i = 0; i <N; i++) 
			for (int j = i+1; j<N;j++) { 
				if (color[i] != color[j])
				{
					double a =0,b =0; 
					for (int k = 0; k<N; k++) 
					{ 
						if (i!=k && color [i] == color [k]) 
							a = Math.max (a, adj[i][k]); 
					} 
					for (int k = 0; k<N; k++)
					{ 
						if (j!=k && color [j] == color [k]) 
							b = Math.max (b,adj[j][k]); 
					} 
					double dx = dist(i,j); 
					//System.out.println(a+" "+d[color[i]]);
					double temp = Math.max (a + b + dx, Math.max (d[color [i]], d[color [j]]));
					minD = Math.min (minD, temp); 
				}
			} 
		String s= minD+"00000000";
		int i=s.indexOf('.');
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));
		//for some reason rounding is required...
		int j = Integer.parseInt(s.substring(i+6,i+7));
		int k = Integer.parseInt(s.substring(i+7,i+8));
		if(k>5) j++;
		out.println(s.substring(0,i)+s.substring(i,i+6)+j);
		System.out.println(s.substring(0,i)+s.substring(i,i+6)+j);
		in.close();
		out.close();
		System.exit(0);
	}
	public static double dist(int i, int j)
	{
		return Math.sqrt ((v[i][0] - v[j][0]) * (v[i][0] - v[j][0]) + (v[i][1] - v[j][1]) * (v[i][1] - v[j][1]));
	}
	public static void floydWarshall()
	{
		for (int k = 0; k <N; k++) 
			for (int i = 0; i <N; i++) 
				for (int j = 0; j <N; j++) { 
					if (i!= j) { 
						adj[i][j] = Math.min (adj[i][j],adj[i][k] + adj[k][j]); 
					} 
				} 
	}
	public static void floodFillColor(int i,int c)
	{
		color[i]=c;
		for(int j=0; j<N; j++)
			if(conn[i][j]==1 && color[j]==0)
				floodFillColor(j, c);
	}
}