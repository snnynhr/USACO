import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;
/*
ID: sunnyna1
LANG: JAVA
PROG: concom
 */
public class concom {
	static concom c= new concom();
	static int N;
	static int S;
	static PrintWriter out;
	static int[][] arr;
	static ArrayList<String> d = new ArrayList<String>();
	static ArrayList<Integer> e = new ArrayList<Integer>();
	static V[] v;
	static Queue<V> pq;
	static int iter=0;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("concom.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
		N=in.nextInt();
		arr = new int[101][101];
		for(int i=0; i<N; i++)
		{
			int x=in.nextInt();	int y=in.nextInt();int z=in.nextInt();arr[x][y]=z;
			if(!e.contains(x)) e.add(x);
			if(!e.contains(y)) e.add(y);
		}
		S=e.size();
		N=100+1;
		int a=0;
		v = new V[N];
		for(int i=0; i<N; i++)
			v[i] = c.new V(a++);
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
			{
				if(i!=j)
				{
					if(arr[i][j]>50) v[i].subComps.add(v[j]);
					v[i].stakedComps.add(v[j]);
					v[i].stakedPercentage.add(arr[i][j]);
				}
				else
				{
					v[i].stakedComps.add(v[j]);
					v[i].stakedPercentage.add(0);
				}
			}
		//queue based ownership leech
		System.out.println("INIT: "+(System.currentTimeMillis()-time));
		for(V w: v)
		{
			if(w.subComps.size()!=0)
			{
				boolean ok[] = new boolean[101];
				V deq = w;
				pq = new LinkedList<V>();
				for(int i=0; i<deq.subComps.size(); i++)
					if(!pq.contains(deq.subComps.get(i)))
						pq.add(deq.subComps.get(i));
				while(!pq.isEmpty())
				{
					deq = pq.poll();
					if(w.num!=deq.num)
					{
						ok[deq.num]=true;
						for(int i=0; i<deq.subComps.size(); i++)
							if(!pq.contains(deq.subComps.get(i)))
								if(!ok[deq.subComps.get(i).num])
									pq.add(deq.subComps.get(i));
						if(!w.subComps.contains(deq))
							w.subComps.add(deq);
						for(V x: v)
						{
							if(!w.subComps.contains(x))
							{
								int sum=0;
								for(int j=0; j<w.subComps.size(); j++)
								{
									sum+=w.subComps.get(j).stakedPercentage.get(w.subComps.get(j).stakedComps.indexOf(x));
									iter++;
								}
								if(sum+w.stakedPercentage.get(w.stakedComps.indexOf(x))>50)
								{
									w.subComps.add(x);
									if(!ok[x.num]) pq.add(x);
								}
							}
						}
					}
				}
			}
			//update(w);
		}
		for(V w: v)
			for(int i=0; i<w.subComps.size(); i++)
				if(w.num!=w.subComps.get(i).num)
					d.add(w.num+" "+w.subComps.get(i).num);
		System.out.println("ALGORITHM: "+(System.currentTimeMillis()-time));
		long sort = System.currentTimeMillis();
		Collections.sort(d, new Comparator<String>()  {
			public int compare(String a, String b) 
			{
				StringTokenizer mm = new StringTokenizer(a);
				StringTokenizer nn = new StringTokenizer(b);
				int aa=Integer.parseInt(mm.nextToken());
				int bb=Integer.parseInt(mm.nextToken());
				int cc=Integer.parseInt(nn.nextToken());
				int dd=Integer.parseInt(nn.nextToken());
				if(aa>cc) return 1;
				else if(aa<cc) return -1;
				else
				{
					if(bb>dd) return 1;
					else if(dd>bb) return -1;
				}
				return 0;
			}
		});
		System.out.println("SORTTIME: "+(System.currentTimeMillis()-sort));
		for(int i=0; i<d.size(); i++)
			out.println(d.get(i));
		System.out.println("Size: "+d.size());
		System.out.println("RUNTIME: "+(System.currentTimeMillis()-time));
		System.out.println("Iterations: "+iter);
		out.close();
		System.exit(0);
	}
	public static boolean check(String s)
	{
		if(s.split(" ")[0].equals(s.split(" ")[1])) return false;
		else return true;
	}
	public static void update(V w)
	{
		for(V x: v)
		{
			if(!w.subComps.contains(x))
			{
				int sum=0;
				for(int j=0; j<w.subComps.size(); j++)
					sum+=w.subComps.get(j).stakedPercentage.get(w.subComps.get(j).stakedComps.indexOf(x));
				if(sum+w.stakedPercentage.get(w.stakedComps.indexOf(x))>50)
					w.subComps.add(x);
			}
		}
	}
	class V 
	{
		int num;
		ArrayList<V> subComps;
		ArrayList<V> stakedComps;
		ArrayList<Integer> stakedPercentage;
		public V(int n)
		{
			num=n;
			subComps=new ArrayList<concom.V>();
			stakedComps = new ArrayList<concom.V>();
			stakedPercentage = new ArrayList<Integer>();
		}
	}
}
//CONCOM DFS
/*
 * public class concom {
	static PrintWriter out;
	static int[][] arr;
	static int N;
	static int max=0;
	static boolean done[];
	static boolean o[];
	static int[][] sub = new int[101][101];
	static int loop=0;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("concom.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
		N=in.nextInt();
		arr = new int[101][101];
		for(int i=0; i<N; i++)
		{
			int x=in.nextInt();	int y=in.nextInt();int z=in.nextInt();arr[x][y]=z;
			max = Math.max(max,Math.max(x,y));
		}
		for(int i=1; i<max+1; i++)
		{
			done = new boolean[max+1];
			o = new boolean[max+1];
			dfs(i,i);
			for(int j=0; j<max+1; j++)
			{
				if(o[j] && i!=j)
					out.println(i+" "+j);
			}
			
		}
		System.out.println("RUNTIME: "+(System.currentTimeMillis()-time));
		System.out.println("Iterations: "+loop);
		in.close();
		out.close();
		System.exit(0);
	}
	public static void dfs(int m,int i)
	{
		if(done[i]) return;
		else
		{
			done[i]=true;
			for(int c=1; c<max+1; c++)
			{
				loop++;
				sub[m][c]+=arr[i][c];
				if(sub[m][c]>50)
				{
					o[c]=true;
					dfs(m,c);
				}
			}	
		}
	}
}
*/