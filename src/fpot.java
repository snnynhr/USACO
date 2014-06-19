import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*ID: sunnyna1
LANG: JAVA
TASK: fpot
 */
public class fpot {
	static int N,D,min;
	static int[][] grid;
	public static void main(String args[]) throws IOException
	{
		min = Integer.MAX_VALUE;
		Scanner in = new Scanner(new FileReader("fpot.in"));
		PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter("fpot.out")));
		N= in.nextInt();
		D= in.nextInt();
		grid = new int[N][2];
		for(int i=0; i<N; i++)
		{
			grid[i][0]=in.nextInt();
			grid[i][1]=in.nextInt();
		}
		for(int i=0; i<N; i++)
			for(int j=i+1; j<N; j++)
				if(Math.abs(grid[i][1]-grid[j][1])>=D)
					min=Math.min(min, Math.abs(grid[i][0]-grid[j][0]));
		if(min == Integer.MAX_VALUE)
			out.println(-1);
		else
		out.println(min);
		out.close();
		System.exit(0);
	}
}