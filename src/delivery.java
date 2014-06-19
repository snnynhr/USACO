import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*ID: sunnyna1
LANG: JAVA
TASK: delivery
 */
public class delivery {
	static int N; //1<=N<=100
	static int[][] g2D;
	static int[][] grid;
	static Queue<Integer> gridx;
	static Queue<Integer> gridy;
	static int maxx=0,maxy=0,minx=Integer.MAX_VALUE, miny=Integer.MAX_VALUE;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("delivery.in"));
		PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter("delivery.out")));
		N=in.nextInt();
		grid = new int[N][2];
		for(int i=0; i<N; i++)
		{
			int x=in.nextInt();
			int y=in.nextInt();
			minx=Math.min(minx,x);
			miny=Math.min(miny,y);
			grid[i][0]=x;
			grid[i][1]=y;
		}
		for(int i=0; i<N; i++)
		{
			grid[i][0]-=minx;
			grid[i][1]-=miny;
			maxx=Math.max(maxx,grid[i][0]);
			maxy=Math.max(maxy,grid[i][1]);
		}
		
		//System.out.println(maxx+" "+maxy);
		g2D = new int[maxx+2][maxy+2];
		for(int i=0; i<N; i++)
		{
			g2D[grid[i][0]][grid[i][1]]=-1;
		}
		int sum=0;
		for(int i=0; i<N-1; i++)
		{	
			int s=minPath(grid[i][0], grid[i][1], grid[i+1][0], grid[i+1][1]);
			//System.out.println(i+" "+s);
			if(s==-1)
			{
				sum=-1;
				break;
			}
			sum+=s;
		}
		if(sum!=-1)
		{
			int s=minPath(grid[N-1][0], grid[N-1][1], grid[0][0], grid[0][1]);
			//System.out.println(N+" "+s);
			sum+=s;
		}
		System.out.println(sum);
		System.out.println("Runtime :"+(System.currentTimeMillis()-time));
		out.close();
		System.exit(0);
	}
	public static int minPath(int x1, int y1, int x2, int y2)
	{
		//int num=1;
		gridx=new LinkedList<Integer>();
		gridy=new LinkedList<Integer>();
		int[][] g2Dc = new int[g2D.length][g2D[0].length];
		for(int i=0; i<N; i++)
		{
			g2Dc[grid[i][0]][grid[i][1]]=-1;
		}
		g2Dc[x1][y1]=1;
		g2Dc[x2][y2]=0;
		gridx.add(x1);
		gridy.add(y1);
		while(g2Dc[x2][y2]==0)
		{
			//num++;
			if(gridx.isEmpty()) 
			{
				//for(int i=0; i<g2Dc.length; i++)
					//System.out.println(Arrays.toString(g2Dc[i]));
				return -1;
			}
			int a1=gridx.poll();
			int b1=gridy.poll();
			if(isValid( a1, b1+1, g2Dc)){
				g2Dc[a1][b1+1]=g2Dc[a1][b1]+1;
				gridx.add(a1);
				gridy.add(b1+1);
			}
			if(isValid( a1-1, b1, g2Dc))
			{
				g2Dc[a1-1][b1]=g2Dc[a1][b1]+1;
				gridx.add(a1-1);
				gridy.add(b1);
			}
			if(isValid( a1+1, b1, g2Dc))
			{
				g2Dc[a1+1][b1]=g2Dc[a1][b1]+1;
				gridx.add(a1+1);
				gridy.add(b1);
			}

			if(isValid( a1, b1-1, g2Dc))
			{
				g2Dc[a1][b1-1]=g2Dc[a1][b1]+1;
				gridx.add(a1);
				gridy.add(b1-1);
			}
		}
		//for(int i=0; i<g2Dc.length; i++)
			//System.out.println(Arrays.toString(g2Dc[i]));
		//System.out.println();
		return g2Dc[x2][y2]-1;
	}
	public static boolean isValid(int x, int y, int[][] g)
	{
		if(x<1 || x >=g2D.length) return false;
		if(y<1 || y >=g2D[0].length) return false;
		if(g[x][y]!=0) return false;
		return true;
	}

}
