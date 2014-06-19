import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
/*
ID: sunnyna1
LANG: JAVA
PROG: route
 */
public class route {
	static int N,M,R;
	static int[] left, right; 
	static long[] bl, br;
	static ArrayList<Node> l; 
	static route x = new route();
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("route.in"));
		StringTokenizer s = new StringTokenizer(in.readLine());
		N = Integer.parseInt(s.nextToken());
		M = Integer.parseInt(s.nextToken());
		R = Integer.parseInt(s.nextToken());
		left = new int[N];
		bl = new long[N];
		br = new long[M];
		right = new int[M];
		l = new ArrayList<route.Node>();
		for(int i=0; i<N; i++)
		{
			s = new StringTokenizer(in.readLine());
			left[i] = Integer.parseInt(s.nextToken());
			bl[i] = left[i];
		}
		for(int i=0; i<M; i++)
		{
			s = new StringTokenizer(in.readLine());
			right[i] = Integer.parseInt(s.nextToken());
			br[i] = right[i];
		}

		for(int i=0; i<R; i++)
		{
			s = new StringTokenizer(in.readLine());
			int a =  Integer.parseInt(s.nextToken())-1;
			int b =  Integer.parseInt(s.nextToken())-1;
			l.add( x.new Node(a,b));
		}
		in.close();
		Collections.sort(l);
		for(int i=0; i<l.size(); i++)
		{
			Node a = l.get(i);
			if(a.i>a.j)
				bl[a.i] = Math.max(bl[a.i], ((long)left[a.i])+br[a.j]);
			else if(a.i<a.j)
				br[a.j] = Math.max(br[a.j], ((long)right[a.j])+bl[a.i]);
			else
			{
				long aa = bl[a.i];
				long bb = br[a.j];
				bl[a.i] = Math.max(aa, ((long)left[a.i])+bb);
				br[a.j] = Math.max(bb, ((long)right[a.j])+aa);
			}
		}
		long max = 0;
		for(int i=0; i<br.length; i++)
			max = Math.max(max,br[i]);
		for(int i=0; i<bl.length; i++)
			max = Math.max(max,bl[i]);
		System.out.println(max);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("route.out")));
		out.println(max);
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
	public class Node implements Comparable<Node>
	{
		int i, j;
		public Node(int a, int b)
		{
			i=a;
			j=b;
		}
		public int compareTo(Node o) {
			boolean a = false;
			boolean b = false;
			if(o.i>=o.j) a = true;
			if(this.i>=this.j) b = true;
			if(a && b)
			{
				if(o.i<this.i) return 1;
				else if(o.i>this.i) return -1;
				else
				{
					if(o.j<this.j) return 1;
					else if(o.j>this.j) return -1;
					else return 0;
				}
			}
			else if(!a && b)
			{
				if(o.j<this.i) return 1;
				else if(o.j>this.i) return -1;
				else
				{
					if(o.i<this.j) return 1;
					else if(o.i>this.j) return -1;
					else return 0;
				}
			}
			else if(a && !b)
			{
				if(o.i<this.j) return 1;
				else if(o.i>this.j) return -1;
				else
				{
					if(o.j<this.i) return 1;
					else if(o.j>this.i) return -1;
					else return 0;
				}
			}
			else
			{
				if(o.j<this.j) return 1;
				else if(o.j>this.j) return -1;
				else
				{
					if(o.i<this.i) return 1;
					else if(o.i>this.i) return -1;
					else return 0;
				}
			}
		}
	}
}