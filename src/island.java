import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
ID: sunnyna1
LANG: JAVA
PROG: island
 */
public class island {
	static int R,C;
	static int[][] map;
	static boolean[][] vis;
	static String[] g;
	static int counter = 1;
	static int[][] adj;
	static island ii = new island();
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("island.in"));
		StringTokenizer s = new StringTokenizer(in.readLine());
		R = Integer.parseInt(s.nextToken());
		C = Integer.parseInt(s.nextToken());
		g = new String[R];
		for(int i=0; i<R; i++)
		{
			g[i] = in.readLine();
		}
		in.close();
		map = new int[R][C];
		vis = new boolean[R][C];
		for(int i=0; i<R; i++)
			for(int j=0; j<C; j++)
				if(!vis[i][j]&& g[i].charAt(j)=='X') floodfillI(i,j,counter++);
		//for(int i=0; i<R; i++)
		//	System.out.println(Arrays.toString(map[i]));
		adj = new int[counter][counter];
		for(int i=0; i<counter; i++)
			for(int j=0; j<counter; j++)
				adj[i][j] = Integer.MAX_VALUE/10;
		//bfs path lengths
		for(int i=0; i<R; i++)
			for(int j=0; j<C; j++)
				if(g[i].charAt(j)=='X') bfs(i,j);
		//for(int i=0; i<adj.length; i++)
		//	System.out.println(Arrays.toString(adj[i]));
		//floydwarshall
		for(int k=0; k<counter; k++)
			for(int i=0; i<counter; i++)
				for(int j=0; j<counter; j++)
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
		//for(int i=0; i<adj.length; i++)
		//	System.out.println(Arrays.toString(adj[i]));
		int[] g = new int[counter-1];
		long minD = Integer.MAX_VALUE;
		for(int i=0; i<g.length; i++)
			g[i]=i+1;
		int[] fact = {1,1,2,6,24,120,720,5040,40320,362880,3628800,39916800,39916800,39916800,39916800,39916800};
		System.out.println(fact[counter-1]);
		for(int i=0; i<fact[counter-1]; i++)
		{
			long sum=0;
			for(int j=1; j<g.length; j++)
				sum+=(long)(adj[g[j-1]][g[j]]);
			minD = Math.min(sum,minD);
			nextPermutation(g);
		}
		System.out.println(minD);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("island.out")));
		out.println(minD);
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
	public static boolean nextPermutation(int[] a)
	{
		int N=a.length;
		int i=N-2;
		for (; i>=0; i--)
			if (a[i]<a[i+1])
				break;
		if (i<0) return false;
		
		for (int j=N-1; j>=i; j--)
		{
			if (a[j]>a[i])
			{
				int temp=a[i];
				a[i]=a[j];
				a[j]=temp;
				break;		
			}
		}
		for (int j=i+1; j<(N+i+1)/2; j++)		//reverse from a[i+1] to a[N-1]
		{
			int temp=a[j];
			a[j]=a[N+i-j];
			a[N+i-j]=temp;
		}
		return true;
	}
	public static void floodfillI(int x, int y, int c)
	{
		vis[x][y]=true;
		map[x][y]=c;
		if(x+1<R && !vis[x+1][y] && g[x+1].charAt(y)=='X')
			floodfillI(x+1, y, c);
		if(x>0 &&!vis[x-1][y] && g[x-1].charAt(y)=='X')
			floodfillI(x-1, y, c);
		if(y>0 &&!vis[x][y-1] && g[x].charAt(y-1)=='X')
			floodfillI(x, y-1, c);
		if(y+1<C &&!vis[x][y+1] && g[x].charAt(y+1)=='X')
			floodfillI(x, y+1, c);
	}
	public static void bfs(int x, int y)
	{
		boolean v[][] = new boolean[R][C];
		Queue<Node> q = new LinkedList<Node>();
		q.add(ii.new Node(x,y,0));
		while(!q.isEmpty())
		{
			Node curr = q.poll();
			int i = curr.xx;
			int j = curr.yy;
			int d = curr.dd;
			if(!v[i][j])
			{
				v[i][j]=true;
				if(j+1<C && !v[i][j+1])
				{
					if(g[i].charAt(j+1)=='S')
						q.add(ii.new Node(i,j+1,d+1));	
					else if(g[i].charAt(j+1)=='X' && map[i][j+1]!=map[i][j])
					{
						adj[map[x][y]][map[i][j+1]] = Math.min(d,adj[map[x][y]][map[i][j+1]]);
						adj[map[i][j+1]][map[x][y]] = Math.min(d,adj[map[i][j+1]][map[x][y]]);
					}
				}
				if(i+1<R && !v[i+1][j])
				{
					if(g[i+1].charAt(j)=='S')
						q.add(ii.new Node(i+1,j,d+1));	
					else if(g[i+1].charAt(j)=='X' && map[i+1][j]!=map[i][j])
					{
						adj[map[x][y]][map[i+1][j]] = Math.min(d,adj[map[x][y]][map[i+1][j]]);
						adj[map[i+1][j]][map[x][y]] = Math.min(d,adj[map[i+1][j]][map[x][y]]);
					}
				}
				if(j>0 && !v[i][j-1])
				{
					if(g[i].charAt(j-1)=='S')
						q.add(ii.new Node(i,j-1,d+1));	
					else if(g[i].charAt(j-1)=='X' && map[i][j-1]!=map[i][j])
					{
						adj[map[x][y]][map[i][j-1]] = Math.min(d,adj[map[x][y]][map[i][j-1]]);
						adj[map[i][j-1]][map[x][y]] = Math.min(d,adj[map[i][j-1]][map[x][y]]);
					}
				}
				if(i>0 && !v[i-1][j])
				{
					if(g[i-1].charAt(j)=='S')
						q.add(ii.new Node(i-1,j,d+1));	
					else if(g[i-1].charAt(j)=='X' && map[i-1][j]!=map[i][j])
					{
						adj[map[x][y]][map[i-1][j]] = Math.min(d,adj[map[x][y]][map[i-1][j]]);
						adj[map[i-1][j]][map[x][y]] = Math.min(d,adj[map[i-1][j]][map[x][y]]);
					}
				}

			}
		}
	}
	public class Node
	{
		public int xx, yy, dd;
		public Node(int a, int b, int d)
		{
			xx=a;
			yy=b;
			dd=d;
		}
	}
}