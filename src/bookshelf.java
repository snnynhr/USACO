import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/*ID: sunnyna1
LANG: JAVA
TASK: bookshelf
 */
//dp algorithm is O(n^2)...too slow for gold standards
public class bookshelf {
	static int N,L;
	static int booksH[], booksW[];
	static int dps[][];
	static int dp[];
	public static void main(String args[]) throws IOException
	{
		//long time = System.currentTimeMillis();
		String file = "bookshelf.in";
		if(args.length!=0) file=args[0]; 
		Scanner in = new Scanner(new FileReader(file));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bookshelf.out")));
		N = in.nextInt();
		L = in.nextInt();
		booksH = new int[N];
		booksW = new int[N];
		for(int i=0; i<N; i++)
		{
			booksH[i] = in.nextInt();
			booksW[i] = in.nextInt();
		}
		//Sequence costs: 0<=a_i..a_i+k <=N
		dps = new int[N+1][N+1];
		for(int i=1; i<=N; i++)
		{
			int j=i;
			int sum=0;
			sum+=booksW[j-1];
			while(sum<=L && j<=N)
			{
				dps[i][j] = Math.max(booksH[j-1],dps[i][j-1]);
				j++;
				if(j<=N)
					sum+=booksW[j-1];
			}
		}
		
		dps[0][0]=0;
		dp=new int[N+1];
		for(int i=0; i<=N; i++)
			dp[i] = Integer.MAX_VALUE;
		//DP algorithm
		dp[0]=0;
		for(int i=1; i<=N; i++)
		{
			for(int j=i; j>0; j--)
			{
				if(dps[j][i]!=0)
					dp[i] = Math.min(dp[i],dps[j][i] + dp[j-1]);
			}
		}
		out.println(dp[N]);
		out.close();
		//System.out.println("RUNTIME: "+(System.currentTimeMillis()-time));
		//System.exit(0);
	}
}
