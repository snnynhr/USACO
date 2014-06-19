import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*ID: sunnyna1
LANG: JAVA
TASK: yinyang
 */
public class yinyang {
	static int N;
	static yinyang y = new yinyang();
	static Node[] v;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("yinyang.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("yinyang.out")));
		N=Integer.parseInt(in.readLine());
		v = new Node[N+1];
		for(int i=1; i<=N; i++) v[i] = y.new Node(i);
		int odd=0, even=0;
		for(int i=1; i<N; i++)
		{
			StringTokenizer st = new StringTokenizer(in.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(c==0) c=-1;
			if(i==1)
			{
				odd = v1;
				even = v2;
			}
			v[v1].adj.add(y.new Edge(v2, c));
			v[v2].adj.add(y.new Edge(v1, c));
		}
		in.close();
		boolean dfse[] = new boolean[N+1];
		boolean dfso[] = new boolean[N+1];
		//odd coloring bfs
		int oddcount = 0;
		boolean vis[] = new boolean[N+1];
		v[odd].setVal(0);
		Queue<Node> pq = new LinkedList<Node>();
		pq.add(v[odd]);
		while(!pq.isEmpty())
		{
			Node curr = pq.poll();
			if(!vis[curr.iden])
			{
				if(curr.val==0) 
				{
					dfso[curr.iden]=true;
					oddcount++;
				}
				vis[curr.iden]=true;
				for(int i=0; i<curr.adj.size(); i++)
				{
					Edge cc = curr.adj.get(i);
					if(!vis[cc.to])
					{
						v[cc.to].setVal(curr.val+cc.color);
						pq.add(v[cc.to]);
					}
				}
			}
		}
		//zero neighbors dfs
		int oddsize = 0;
		int oddzerosum = 0;
		//for(int i=1; i<=7; i++)
		//System.out.println(v[i].val+"Sdfsfd");
		for(int i=0; i<dfso.length; i++)
		{
			if(dfso[i]) 
			{
				boolean[] vi = new boolean[N+1];
				vi[i] = true;
				//System.out.println("o"+i);
				oddsize++;
				for(int j=0; j<v[i].adj.size(); j++)
				{
					if(!vi[v[i].adj.get(j).to])
					{
						oddzerosum+=dfs(v[i].adj.get(j).to,vi);
					}
				}
			}
		}

		//even coloring bfs
		int evencount = 0;
		vis = new boolean[N+1];
		v[even].setVal(0);
		pq = new LinkedList<Node>();
		pq.add(v[even]);
		while(!pq.isEmpty())
		{
			Node curr = pq.poll();
			if(!vis[curr.iden])
			{
				if(curr.val==0) 
				{
					dfse[curr.iden]=true;
					evencount++;
				}
				vis[curr.iden]=true;
				for(int i=0; i<curr.adj.size(); i++)
				{
					Edge cc = curr.adj.get(i);
					if(!vis[cc.to])
					{
						v[cc.to].setVal(curr.val+cc.color);
						pq.add(v[cc.to]);
					}
				}
			}
		}
		//zero neighbors dfs
		int evensize = 0;
		int evenzerosum = 0;
		for(int i=0; i<dfse.length; i++)
		{
			if(dfse[i]) 
			{
				boolean[] vi2 = new boolean[N+1];
				vi2[i] = true;
				//System.out.println("e"+i);
				evensize++;
				evenzerosum+=getNum(i,vi2);
			}
		}
		/*
		int evennum = ((evencount-1)*(evencount-2))/2;
		if(evencount < 3) evennum=0;
		int oddnum = ((oddcount-1)*(oddcount-2))/2;
		if(oddcount < 3) oddnum=0;
		out.println(oddnum+evennum);
		System.out.println(oddnum+evennum);
		System.out.println(oddcount+" "+evencount);
		 */
		//System.out.println(oddsize + " "+oddzerosum);
		//System.out.println(evensize + " "+evenzerosum);
		out.println((evensize*(evensize-1)-evenzerosum)/2 + (oddsize*(oddsize-1)-oddzerosum)/2);
		
		out.close();
		System.out.println("RUNTIME: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
	public static int dfs(int i, boolean[] vis)
	{
		//System.out.println(i+"  k");
		int sum=0;
		vis[i] = true;
		//System.out.println(Arrays.toString(vis));
		if(v[i].val==0) return 1;
		else{
			for(int j=0; j<v[i].adj.size(); j++)
			{
				int kk= v[i].adj.get(j).to;
				if(!vis[kk])
				{
					sum+=dfs(v[i].adj.get(j).to,vis);
				}
			}
		}
		return sum;
	}
	public static int getNum(int i, boolean[] vis)
	{
		int sum=0;
		for(int j=0; j<v[i].adj.size(); j++)
		{
			if(!vis[v[i].adj.get(j).to])
			{
				sum+=dfs(v[i].adj.get(j).to,vis);
			}
		}
		return sum;
	}

	public class Node
	{
		public int iden;
		public int val;
		public ArrayList<Edge> adj;
		public Node(int i)
		{
			adj = new ArrayList<yinyang.Edge>();
			iden = i;
		}
		public void setVal(int i)
		{
			val = i;
		}
	}
	public class Edge
	{
		public int to;
		public int color;
		public Edge(int t, int c)
		{
			to = t;
			color = c;
		}
	}
}