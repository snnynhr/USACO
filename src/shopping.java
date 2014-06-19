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
PROG: shopping
 */
public class shopping {
	static int S;
	static int[][] offers;
	static int[] offerPrices;
	static int B;
	static int[][] items;
	static int[][][][][] dp = new int[6][6][6][6][6];
	static int[] map = new int[1000];
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("shopping.in"));
		S = Integer.parseInt(in.readLine());
		offers = new int[S][];
		offerPrices = new int[S];
		for(int i=0; i<S; i++)
		{
			StringTokenizer st = new StringTokenizer(in.readLine());
			int num = Integer.parseInt(st.nextToken());
			offers[i] = new int[2*num];
			for(int j=0; j<offers[i].length; j++)
				offers[i][j]=Integer.parseInt(st.nextToken());
			offerPrices[i]=Integer.parseInt(st.nextToken());
		}
		B = Integer.parseInt(in.readLine());
		System.out.println(B);
		items = new int[5][3];
		int count=0;
		for(int i=0; i<B; i++)
		{
			StringTokenizer st = new StringTokenizer(in.readLine());
			int a = Integer.parseInt(st.nextToken());
			map[a]=count++;
			items[i][0] = map[a];
			items[i][1] = Integer.parseInt(st.nextToken());
			items[i][2] = Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<S; i++)
			for(int j=0; j<offers[i].length; j+=2)
				offers[i][j]=map[offers[i][j]];
		for(int i=0; i<6; i++)
			for(int j=0; j<6; j++)
				for(int k=0; k<6; k++)
					for(int l=0; l<6; l++)
						for(int m=0; m<6; m++)
							dp[i][j][k][l][m]=Integer.MAX_VALUE/10;
		dp[0][0][0][0][0]=0;
		System.out.println(solve(new int[] {items[0][1],items[1][1],items[2][1],items[3][1],items[4][1]}));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shopping.out")));
		out.println(solve(new int[] {items[0][1],items[1][1],items[2][1],items[3][1],items[4][1]}));
		in.close();
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
	public static int solve(int[] a)
	{
		for(int i=0; i<a.length; i++)
			if(a[i]<0) return Integer.MAX_VALUE/10;
		if(dp[a[0]][a[1]][a[2]][a[3]][a[4]]!=Integer.MAX_VALUE/10) return dp[a[0]][a[1]][a[2]][a[3]][a[4]];
		for(int i=0; i<B; i++)
		{
			a[i]--;
			int res = solve(a);
			a[i]++;
			dp[a[0]][a[1]][a[2]][a[3]][a[4]] = Math.min(dp[a[0]][a[1]][a[2]][a[3]][a[4]], items[i][2]+res); 
		}
		for(int i=0; i<S; i++)
		{
			for(int j=0; j<offers[i].length; j+=2)
			{
				a[offers[i][j]]-=offers[i][j+1];
			}
			int res = solve(a);
			for(int j=0; j<offers[i].length; j+=2)
			{
				a[offers[i][j]]+=offers[i][j+1];
			}
			dp[a[0]][a[1]][a[2]][a[3]][a[4]] = Math.min(dp[a[0]][a[1]][a[2]][a[3]][a[4]], offerPrices[i]+res);		
		}
		return dp[a[0]][a[1]][a[2]][a[3]][a[4]];
	}
}