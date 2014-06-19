import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
/*
ID: sunnyna1
LANG: JAVA
PROG: necklace
 */
public class necklace {
	public static int[] failure;
	public static int matchPoint;
	public static String n,m;
	public static int N,M;
	public static int[] res;
	public static ArrayList<Integer> pp = new ArrayList<Integer>();
	public static necklace x = new necklace();
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("necklace.in"));
		n = in.readLine();
		N = n.length();
		m = in.readLine();
		M = m.length();
		in.close();
		String pattern = m;
		String text = n;
		res = new int[N+1];
		for(int i=0; i<N+1; i++) res[i] = -100;
		KMPplus kmp = x.new KMPplus(pattern);
		kmp.search(text);
		int counter=0;
		int c=0;
		int cc = 0;
		for(int i=0; i<N+1; i++)
		{
			if(res[i]==-1)
			{
				for(int j=0; j<c; j++)
				{
					res[pp.get(cc)]=-100;
					res[pp.get(cc)+M]=-100;
					cc++;
				}
				counter++;
				c=0;
			}
			if(res[i]==1) c++;
			if(res[i]==0)
			{
				res[pp.get(cc)]-=1;
				res[pp.get(cc)+M]+=1;
				cc++;
				counter++;
				c=1;
			}
		}
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("necklace.out")));
		out.println(counter);
		out.close();
		System.exit(0);
	}
	public class KMPplus {
		private String pattern;
		private int[] next;
		// create Knuth-Morris-Pratt NFA from pattern
		public KMPplus(String pattern) {
			this.pattern = pattern;
			int M = pattern.length();
			next = new int[M];
			int j = -1;
			for (int i = 0; i < M; i++) {
				if (i == 0) next[i] = -1;
				else if (pattern.charAt(i) != pattern.charAt(j)) next[i] = j;
				else next[i] = next[j];
				while (j >= 0 && pattern.charAt(i) != pattern.charAt(j)) {
					j = next[j];
				}
				j++;
			}
		}
		public void search(String text) {
		  int t = 0, p = 0;
		  while (p + t < text.length()) {
		    if (text.charAt(t + p) != pattern.charAt(p)) {
		      t = t + p - next[p];
		      p = -1 < next[p] ? next[p] : 0;
		    } else if (p++ == pattern.length() - 1) {
		      pp.add(t);
		      if(res[t]==-100) res[t]=0;
				res[t]+=1;
				if(res[t+M]==-100) res[t+M]=0;
				res[t+M]-=1;
		      t++;
		      p = 0;
		    }
		  }
		}

	}
}