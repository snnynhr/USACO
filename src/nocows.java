import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/*
ID: sunnyna1
LANG: JAVA
PROG: nocows
 */
public class nocows {
	static int N;
	static int K;
	static final int mod = 9901;
	static int dp[][];
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("nocows.in"));
		N=in.nextInt();
		K=in.nextInt();
		in.close();
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
		dp = new int[K+1][N+1];
		for(int i=1; i<K+1; i++)
			dp[i][1]=1;
		for(int i=1; i<K+1; i++)
			for(int j=1; j<N+1; j+=2)
			{
				for(int k=1; k<j-1; k++)
					dp[i][j]+=(dp[i-1][k]*dp[i-1][j-1-k])%mod;
				dp[i][j]%=mod;
			}
		int[] sol = new int[K+1];
		for(int i=1; i<K+1; i++)
			sol[i]=(dp[i][N]-dp[i-1][N]+9901)%9901;
		out.println(sol[K]);
		System.out.println("RUNTIME: "+(System.currentTimeMillis()-time));
		out.close();
		System.exit(0);
	}
}