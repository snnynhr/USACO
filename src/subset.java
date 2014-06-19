import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: sunnyna1
LANG: JAVA
PROG: subset
 */
public class subset {
	static int N;
	static int[][] dp;
	public static void main(String args[]) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("subset.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
		N=Integer.parseInt(in.readLine());
		if(N*(N+1)/2%2==1) out.println(0);
		else
		{
			dp = new int[N*(N+1)/4+1][N+1];
			dp[0][0]=1;
			for(int i=0; i<=N*(N+1)/4; i++)
				for(int j=0; j<=N; j++)
					if(i!=0 && j!=0)
					{
						if( i>=j && j>=1)
							dp[i][j] = dp[i][j-1]+dp[i-j][j-1];
						else if( i>=j && j==0)
							dp[i][j] = 0;
						else if( j>=1)
							dp[i][j] = dp[i][j-1];
					}
				
			out.println(dp[N*(N+1)/4][N]);
		}
		out.close();
		System.exit(0);
	}
}
