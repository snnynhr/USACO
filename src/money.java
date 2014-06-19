import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/*
ID: sunnyna1
LANG: JAVA
PROG: money
 */
public class money {
	static int N,V;
	static long[] dp; 
	static int[] values;
	static PrintWriter out;
	public static void main(String args[]) throws IOException
	{
		Scanner in = new Scanner(new FileReader("money.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
		V=in.nextInt();
		N=in.nextInt();
		values = new int[V];
		for(int i=0; i<V; i++)
			values[i] = in.nextInt();
		dp=new long[N+1];
		dp[0]=1;
		for(int i=0; i<values.length; i++)
			for(int n=values[i]; n<=N; n++)
				dp[n]+=dp[n-values[i]];
		out.println(dp[N]);
		out.close();
		System.exit(0);
	}
}