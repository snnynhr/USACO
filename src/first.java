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
PROG: first
 */
public class first {
	static int N;
	static String[] m;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("first.in"));
		N=Integer.parseInt(in.readLine());
		m = new String[N];
		for(int i=0; i<N; i++)
			m[i] = in.readLine();
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("first.out")));
		ArrayList<String> dump = new ArrayList<String>();
		for(int i=0; i<N; i++)
		{
			int l = m[i].length();
			int[][] c = new int[26][26];
			boolean d = false;
			ii:for(int j=0; j<N; j++)
			{
				for(int k=0; k<Math.min(m[j].length(), l); k++)
				{
					int a = m[i].charAt(k)-97;
					int b = m[j].charAt(k)-97;
					if(a!=b)
					{
						if(c[a][b]==2)
						{
							d=true;
							break ii;
						}
						else
						{
							c[a][b]=1;
							c[b][a]=2;
							break;
						}
					}
				}
				if(l>m[j].length()) 
				{
					d=true;
					break ii;
				}
			}
			if(!d) dump.add(m[i]);
		}
		out.println(dump.size());
		for(int i=0; i<dump.size(); i++)
			out.println(dump.get(i));
		out.close();
		System.out.println("RUNTIME: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
}