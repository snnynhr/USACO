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
PROG: seating
 */
public class seating {
	static int N,M;
	static int[] grid;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("seating.in"));
		StringTokenizer s = new StringTokenizer(in.readLine());
		N = Integer.parseInt(s.nextToken());
		M = Integer.parseInt(s.nextToken());
		grid = new int[N];
		int count=0;
		for(int i=0; i<M; i++)
		{
			s = new StringTokenizer(in.readLine());
			String st = s.nextToken();
			if(st.charAt(0)=='A')
			{
				int a = Integer.parseInt(s.nextToken());
				if(!seat(a)) count++;
			}
			else
			{
				int a = Integer.parseInt(s.nextToken());
				int b = Integer.parseInt(s.nextToken());
				for(int j=a; j<=b; j++) grid[j]=0;
			}
		}
		in.close();
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("seating.out")));
		out.println(count);
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
	public static boolean seat(int x)
	{
		int curr=0;
		int cval=0;
		boolean found = false;
		for(int i=0; i<grid.length; i++)
		{
			if(grid[i]==0)
			{
				cval++;
				if(cval == x)
				{
					found = true;
					break;
				}
			}
			if(grid[i]==1)
			{
				curr = i+1;
				cval = 0;
			}
		}
		if(!found) return false;
		else
		{
			for(int i=curr; i<curr+x; i++) grid[i]=1;
			return true;
		}
	}
}