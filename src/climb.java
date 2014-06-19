import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Queue;
import java.util.Scanner;

/*ID: sunnyna1
LANG: JAVA
TASK: climb
 */
public class climb {
	static int N; //1<=N<=100
	static int[][] g2D;
	static int[][] grid;
	static Queue<Integer> gridx;
	static Queue<Integer> gridy;
	static int maxx=0,maxy=0;
	public static void main(String args[]) throws IOException
	{
		Scanner in = new Scanner(new FileReader("climb.in"));
		PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter("climb.out")));
		N=in.nextInt();
		grid = new int[N][2];
		for(int i=0; i<N; i++)
		{
			int x=in.nextInt();
			int y=in.nextInt();
			grid[i][0]=x;
			grid[i][1]=y;
		}
		out.close();
	}
}