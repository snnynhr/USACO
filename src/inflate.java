import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
/*
ID: sunnyna1
LANG: JAVA
PROG: inflate
 */
public class inflate {
	static int M,N;
	static int[][] p;
	static int[] dp;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("inflate.in"));
		StringTokenizer s = new StringTokenizer(f.readLine());
		M=Integer.parseInt(s.nextToken());
		N=Integer.parseInt(s.nextToken());
		p = new int[N][2];
		for(int i=0; i<N; i++)
		{
			s = new StringTokenizer(f.readLine());
			p[i][0]=Integer.parseInt(s.nextToken());
			p[i][1]=Integer.parseInt(s.nextToken());
		}
		dp = new int[M+1];
		for(int i=1; i<M+1; i++)
			for(int j=0; j<N; j++)
				if(i>=p[j][1])
					dp[i]=Math.max(dp[i],dp[i-p[j][1]]+p[j][0]);
		f.close();
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
		out.println(dp[M]);
		out.close();
		System.out.println("Runtime: " +(System.currentTimeMillis()-time));
		System.exit(0);
	}
}