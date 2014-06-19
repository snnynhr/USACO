import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
ID: sunnyna1
LANG: JAVA
PROG: prefix
 */
public class prefix {
	static String[] primitives = new String[201];
	static int N=0;
	static StringBuilder main = new StringBuilder("");
	static int max=0;
	static int dp[];
	//O(mn) dp algorithm
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("prefix.in"));
		String s = in.readLine();
		while(!s.equals("."))
		{
			StringTokenizer a = new StringTokenizer(s);
			while(a.hasMoreTokens())
				primitives[N++]=a.nextToken();
			s=in.readLine();
		}
		s = in.readLine();
		while(s!=null)
		{
			main.append(s);
			s=in.readLine();
		}
		in.close();
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
		String[] t= new String[N];
		for(int i=0; i<N; i++)
			t[i]=primitives[i];
		Arrays.sort(t);
		dp = new int[main.length()+1];
		dp[0]=0;
		for(int i=1; i<dp.length; i++)
			dp[i]=-1;
		for(int i=1; i<dp.length; i++)
			for(int j=0; j<N; j++)
			{
				int l=t[j].length();
				if(i>=l && dp[i-l]!=-1)
					if(t[j].equals(main.substring(i-l, i)))
						dp[i]=Math.max(dp[i-l]+l,dp[i]);
			}
		for(int i=0; i<dp.length; i++)
			max=Math.max(max, dp[i]);
		out.println(max);
		System.out.println("RUNTIME: "+(System.currentTimeMillis()-time));
		out.close();
		System.exit(0);
	}
	/*waaay to slow
	public static void dfs(String s,int n)
	{
		for(int i=0; i<N; i++)
		{
			int a=primitives[i].length();
			if(s.length()>=a)
				if(s.subSequence(0,a).equals(primitives[i]))
					dfs(s.substring(a),n+a);
		}
		max=Math.max(n, max);
	}*/
}