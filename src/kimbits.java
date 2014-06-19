import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/*
ID: sunnyna1
LANG: JAVA
PROG: kimbits
 */
public class kimbits {
	static int[][] dp;
	static int N,L;
	static long I;
	static PrintWriter out;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("kimbits.in"));
		N = in.nextInt();
		L = in.nextInt();
		I = in.nextLong();
		dp = new int[N+1][L+1];
		for(int i=0; i<=N; i++)
			dp[i][0]=1;
		for(int i=1; i<=N; i++)
			for(int j=1; j<=L; j++)
				dp[i][j]=dp[i-1][j]+dp[i-1][j-1];
			for(int i=1; i<=L; i++)
				for(int j=0; j<=N; j++)
					dp[j][i]+=dp[j][i-1];
		out = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
		dfs(N,L,I-1);
		out.println();
		in.close();
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
	public static void dfs(int n,int l, long i)
	{
		if(n==0 || l<0) return;
		if(i<dp[n-1][l]) 
		{
			out.print(0);
			dfs(n-1,l,i);
		}
		else
		{
			out.print(1);
			dfs(n-1,l-1,i-dp[n-1][l]);
		}
	}
}