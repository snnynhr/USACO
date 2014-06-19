import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/*
ID: sunnyna1
LANG: JAVA
PROG: stamps
 */
public class stamps {
	static int K,N;
	static int[] val;
	static boolean[] check;
	static int[] minCoins;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("stamps.in"));
		K = in.nextInt();
		N = in.nextInt();
		val = new int[N];
		for(int i=0; i<N; i++)
			val[i]=in.nextInt();
		in.close();
		check = new boolean[3000001];
		minCoins = new int[3000001];
		for(int i=1; i<minCoins.length; i++)
			minCoins[i]=Integer.MAX_VALUE;
		check[0]=true;
		int i=0;
		while(check[i])
		{
			i++;
			for(int j=0; j<N; j++)
				if(i>=val[j] && check[i-val[j]] && minCoins[i-val[j]]<K)
				{
					check[i]=true;
					minCoins[i]=Math.min(minCoins[i], minCoins[i-val[j]]+1);
				}
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("stamps.out")));
		out.println(i-1);
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
}