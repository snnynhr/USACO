
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


/*ID: sunnyna1
LANG: JAVA
TASK: pageant
 */
public class pageant {
	static String[][] grid;
	static int p=0,N,M;
	static int[] count = new int[2];
	static ArrayList<Integer> x0 = new ArrayList<Integer>();
	static ArrayList<Integer> y0 = new ArrayList<Integer>();
	static ArrayList<Integer> x1 = new ArrayList<Integer>();
	static ArrayList<Integer> y1 = new ArrayList<Integer>();
	public static void main(String args[]) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("pageant.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pageant.out")));
		StringTokenizer r = new StringTokenizer(f.readLine());
		N=Integer.parseInt(r.nextToken());
		M=Integer.parseInt(r.nextToken());
		grid = new String[N][M];
		for(int i=0; i<N; i++)
		{
			String tmp = f.readLine();
			for(int j=0; j<M; j++)
			{
				grid[i][j]=tmp.substring(j,j+1);
			}
		}
		for(int i=0; i<N; i++)
		{
			for(int j=0; j<M; j++)
			{
				if(grid[i][j].equals("X"))
				{
					transform(i,j);
					p++;
				}
			}
		}
		
		int min = 99999;
		for(int i=0; i<count[0]; i++)
		{
			for(int j=0; j<count[1]; j++)
			{
				int res=(int)Math.abs(x0.get(i)-x1.get(j))+(int)Math.abs(y0.get(i)-y1.get(j));
				if(res<min)
				{
					min=res;
				}
			}
		}
		out.println(min-1);
		out.close();
		System.exit(0);
	}
	public static void transform(int x, int y)
	{
		if(grid[x][y].equals("X"))
		{
			count[p]+=1;
			grid[x][y]=p+"";
			if(p==0)
			{
				x0.add(x);
				y0.add(y);
			}
			else
			{
				x1.add(x);
				y1.add(y);
			}
			if(x < N-1) transform(x+1,y);
			if(x > 0) transform(x-1,y);
			if(y < M-1) transform(x,y+1);
			if(y > 0) transform(x,y-1);
		}
	}


}
