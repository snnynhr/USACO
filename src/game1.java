import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
/*
ID: sunnyna1
LANG: JAVA
PROG: game1
 */
public class game1 {
	static int N;
	static int[] A;
	static int dp[][];
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("game1.in"));
		BufferedReader f = new BufferedReader(new FileReader("game1.in"));
		N = Integer.parseInt(f.readLine());
		int sum=0;
		A = new int[N];
		StringTokenizer st = new StringTokenizer("");
		for(int i=0; i<N; i++)
		{
			if(!st.hasMoreTokens()) st = new StringTokenizer(f.readLine());
			A[i]=Integer.parseInt(st.nextToken());
			sum+=A[i];
		}
		in.close();
		dp = new int[N][N];
		for(int i=0; i<N; i++)
			for(int j=0; j<N; j++)
				dp[i][j]=Integer.MIN_VALUE;
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("game1.out")));
		out.println(solve(0,N-1)+" "+(sum-solve(0,N-1)));
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
	public static int solve(int a, int b)
	{
		if(a>b) return 0;
		if(a==b) 
		{
			dp[a][b]=A[a];
			return A[a];
		}
		if(dp[a][b]!=Integer.MIN_VALUE) return dp[a][b];
		else
		{
			dp[a][b] = Math.max(Math.min(solve(a+2,b),solve(a+1,b-1))+A[a],Math.min(solve(a,b-2),solve(a+1,b-1))+A[b]);
			return dp[a][b];
		}
	}
}