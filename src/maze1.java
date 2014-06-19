import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
ID: sunnyna1
LANG: JAVA
PROG: maze1
 */
public class maze1 {
	static char[][] grid;
	static int W,H;
	static ArrayList<Integer> master = new ArrayList<Integer>();
	static int temp;
	static Queue<Long> search = new LinkedList<Long>();
	static int count1,count2=0;
	static int[][] dist1,dist2;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("maze1.in"));
		W=in.nextInt();
		H=in.nextInt();
		grid = new char[2*H+1][2*W+1];
		dist1 = new int[H][W];
		dist2 = new int[H][W];
		System.out.println(in.nextLine());
		for(int i=0; i<2*H+1; i++)
		{
			String s = in.nextLine();
			for(int j=0; j<2*W+1; j++)
				grid[i][j] = s.charAt(j);
		}
		for(int i=0; i<H; i++)
			for(int j=0; j<W; j++)
			{
				dist1[i][j]=Integer.MAX_VALUE;
				dist2[i][j]=Integer.MAX_VALUE;
			}
		in.close();
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
		int[] exits = new int[4];
		int c=0;
		for(int i=0; i<2*W+1; i++)
			if(grid[0][i]==' ')
			{
				exits[c++]=1;
				exits[c++]=i;
			}
		for(int i=0; i<2*W+1; i++)
			if(grid[2*H][i]==' ')
			{
				exits[c++]=2*H-1;
				exits[c++]=i;
			}
		for(int i=0; i<2*H+1; i++)
			if(grid[i][0]==' ')
			{
				exits[c++]=i;
				exits[c++]=1;
			}
		for(int i=0; i<2*H+1; i++)
			if(grid[i][2*W]==' ')
			{
				exits[c++]=i;
				exits[c++]=2*W-1;
			}
		System.out.println(Arrays.toString(exits));
		boolean[][] v = new boolean[H][W];
		bfsT(exits[0],exits[1],0, v,dist1);
		boolean[][] v2 = new boolean[H+1][W+1];
		bfsT(exits[2],exits[3],0, v2,dist2);
		int max=0;
		for(int i=0; i<dist1.length; i++)
			for(int j=0; j<dist1[i].length; j++)
				max = Math.max(max,Math.min(dist1[i][j],dist2[i][j]));
		out.println(max+1);
		System.out.println(max+1);
		/*
		 * DFS and BFS Floodfill searches with unlimited exits.
		 * 
		 * 
		long time1 = System.currentTimeMillis();
		//dfs

		for(int i=1; i<=2*H; i+=2)
		{
			for(int j=1; j<=2*W; j+=2)
			{
				temp = Integer.MAX_VALUE; 
				boolean[][] v = new boolean[H+1][W+1];
				dfs(i,j,0,v);
				master.add(temp);
			}
		}
		Collections.sort(master);
		System.out.println(master.get(master.size()-1));
		System.out.println("Runtime DFS: "+(System.currentTimeMillis()-time1));
		System.out.println("Iterations: "+count1);*/
		/*
		//bfs
		long time2 = System.currentTimeMillis();
		int max=0;
		for(int i=1; i<=2*H; i+=2)
		{
			for(int j=1; j<=2*W; j+=2)
			{
				boolean[][] v = new boolean[H+1][W+1];
				max = Math.max(bfs(i,j,0,v),max);
			}
		}
		System.out.println(max);
		System.out.println("Runtime BFS: "+(System.currentTimeMillis()-time2));
		System.out.println("Iterations: "+count2);*/
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		out.close();
		System.exit(0);
	}
	public static void dfsT(int x, int y, int n, boolean[][] visited, int[][] dist)
	{
		count1++;
		int a = (x-1)/2;
		int b = (y-1)/2;
		System.out.println("Examining "+a+","+b);
		visited[a][b]=true;
		for(int i=0; i<visited.length; i++)
			System.out.println(Arrays.toString(visited[i]));
		dist[a][b]=Math.min(n,dist[a][b]);
		boolean[][] visited1 = visited.clone();
		if(y-2>=0 && grid[x][y-1]!='|' && !visited1[a][b-1]) dfsT(x,y-2,n+1,visited1,dist);
		if(x-2>=0 && grid[x-1][y]!='-' && !visited1[a-1][b]) dfsT(x-2,y,n+1,visited1,dist);
		//System.out.println("Pass "+a+","+b);
		if(y+2<2*W+1 && grid[x][y+1]!='|' && !visited1[a][b+1]) dfsT(x,y+2,n+1,visited1,dist);
		if(x+2<2*H+1 && grid[x+1][y]!='-' && !visited1[a+1][b]) dfsT(x+2,y,n+1,visited1,dist);
		System.out.println("Finished "+a+","+b);
		return;
	}
	public static void dfs(int x, int y, int n, boolean[][] visited)
	{
		count1++;
		int a = (x-1)/2;
		int b = (y-1)/2;
		visited[a][b]=true;
		if(y==1 && grid[x][0]==' ') 
		{
			temp=Math.min(n+1,temp);
			return;
		}
		else if(y==2*W-1 && grid[x][2*W]==' ') 
		{
			temp=Math.min(n+1,temp);
			return;
		}
		else if(x==1 && grid[0][y]==' '){
			temp=Math.min(n+1,temp);
			return;
		}
		else if(x==2*H-1 && grid[2*H][y]==' '){
			temp=Math.min(n+1,temp);
			return;
		}
		if(x+2<2*H+1 && grid[x+1][y]!='-' && !visited[a+1][b]) dfs(x+2,y,n+1,visited);
		if(y+2<2*W+1 && grid[x][y+1]!='|' && !visited[a][b+1]) dfs(x,y+2,n+1,visited);	
		if(x-2>=0 && grid[x-1][y]!='-' && !visited[a-1][b]) dfs(x-2,y,n+1,visited);
		if(y-2>=0 && grid[x][y-1]!='|' && !visited[a][b-1]) dfs(x,y-2,n+1,visited);
		return;
	}
	public static void bfsT(int x, int y, int n, boolean[][] visited, int[][] dist)
	{
		long key = 10000000*x+10000*y+n;
		search.add(key);
		while(!search.isEmpty())
		{
			count2++;
			long curr = search.poll();
			x=(int)(curr/10000000);
			curr%=10000000;
			y=(int)(curr/10000);
			curr%=10000;
			n=(int)curr;
			int a = (x-1)/2;
			int b = (y-1)/2;
			visited[a][b]=true;
			dist[a][b] = Math.min(dist[a][b],n);
			if(x+2<2*H+1 && grid[x+1][y]!='-' && !visited[a+1][b] && !search.contains(10000000*(x+2)+10000*y+n+1l))	search.add(10000000*(x+2)+10000*y+n+1l);
			if(y+2<2*W+1 && grid[x][y+1]!='|' && !visited[a][b+1]&& !search.contains(10000000*x+10000*(y+2)+n+1l)) search.add(10000000*x+10000*(y+2)+n+1l);	
			if(x-2>=0 && grid[x-1][y]!='-' && !visited[a-1][b]&& !search.contains(10000000*(x-2)+10000*y+n+1l)) search.add(10000000*(x-2)+10000*y+n+1l);
			if(y-2>=0 && grid[x][y-1]!='|' && !visited[a][b-1]&& !search.contains(10000000*x+10000*(y-2)+n+1l)) search.add(10000000*x+10000*(y-2)+n+1l);
		}
	}
	public static int bfs(int x, int y, int n, boolean[][] visited)
	{
		long key = 10000000*x+10000*y+n;
		search.add(key);
		while(!search.isEmpty())
		{
			count2++;
			long curr = search.poll();
			x=(int)(curr/10000000);
			curr%=10000000;
			y=(int)(curr/10000);
			curr%=10000;
			n=(int)curr;
			int a = (x-1)/2;
			int b = (y-1)/2;
			visited[a][b]=true;
			if(y==1 && grid[x][0]==' ') 
				return n+1;
			else if(y==2*W-1 && grid[x][2*W]==' ') 
				return n+1;
			else if(x==1 && grid[0][y]==' ')
				return n+1;
			else if(x==2*H-1 && grid[2*H][y]==' ')
				return n+1;
			if(x+2<2*H+1 && grid[x+1][y]!='-' && !visited[a+1][b])	search.add(10000000*(x+2)+10000*y+n+1l);
			if(y+2<2*W+1 && grid[x][y+1]!='|' && !visited[a][b+1]) search.add(10000000*x+10000*(y+2)+n+1l);	
			if(x-2>=0 && grid[x-1][y]!='-' && !visited[a-1][b]) search.add(10000000*(x-2)+10000*y+n+1l);
			if(y-2>=0 && grid[x][y-1]!='|' && !visited[a][b-1]) search.add(10000000*x+10000*(y-2)+n+1l);
		}
		return Integer.MAX_VALUE;
	}
}