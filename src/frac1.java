import java.io.*;
import java.util.*;
/*
ID: sunnyna1
PROG: frac1
LANG: JAVA
 */
//12880 max mem size
//1<=N<=160
public class frac1
{
	static int n;
	static PriorityQueue<String> LIST;

	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("frac1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
		n=Integer.parseInt(f.readLine());
		LIST=new PriorityQueue<String>(12881, new Comparator<String>() {
			public int compare(String a, String b) {
				int ai=a.indexOf('/');
				int bi=b.indexOf('/');
				double aval= Double.parseDouble(a.substring(0, ai))/Double.parseDouble(a.substring(ai+1));
				double bval= Double.parseDouble(b.substring(0, bi))/Double.parseDouble(b.substring(bi+1));
				if(aval>bval) return 1;
				else if (bval>aval) return -1;
				return 0;
			}
		});
		for(int i=1; i<=n; i++)
		{
			for(int j=1; j<=i; j++)
			{
				if(gcf(i,j)==1)
					LIST.add(j+"/"+i);
			}
		}
		out.println("0/1");
		while(!LIST.isEmpty())
			out.println(LIST.poll());
		out.close();
		System.exit(0);
	}
	public static int gcf(int u, int v) 
	{
		int gcd = 0;
		if (v == 0) 
			gcd = u;
		else 
			gcd = gcf(v, u % v);
		return gcd;
	}
}