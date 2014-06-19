import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;
/*
ID: sunnyna1
LANG: JAVA
PROG: humble
 */
public class humble {
	static int K,N;
	static int[] p;
	static PriorityQueue<N> v = new PriorityQueue<N>();
	static PriorityQueue<N> pq = new PriorityQueue<N>();
	static humble a = new humble();
	static long f =0;
	static long MAXVALUE = Long.MAX_VALUE;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("humble.in"));
		K = in.nextInt();
		N = in.nextInt();
		p = new int[K];
		for(int i=0; i<K; i++)
			p[i]=in.nextInt();
		in.close();
		if(N>99999 & K>50)
		MAXVALUE = 1000000;
		bfs(0);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
		System.out.println(f);
		out.println(f);
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
	public static void bfs(int index)
	{
		v.add(a.new N(1l,0));
		int in=0;
		while(in<N+1)
		{
			N c=v.poll();
			f=c.val;
			for(int i=c.index; i<K; i++)
			{
				if(c.val*p[i]<MAXVALUE)
					v.add(a.new N(c.val*p[i],i));
			}
			in++;
		}

	}
	public class N implements Comparable<N>
	{
		private long val;
		private int index;
		public N(long v, int i)
		{
			val=v; index =i;
		}
		public int compareTo(N o) {
			if(o.val>this.val) return -1;
			else if(o.val<this.val) return 1;
			return 0;
		}
	}
}