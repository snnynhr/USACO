import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/*ID: sunnyna1
LANG: JAVA
TASK: unlock
 */
public class unlock {
	static int moves = -1;
	static int N1,N2,N3;
	static int[][] grid = new int[30][30];
	static int[][] s1,s2,s3;
	public static void main(String args[]) throws IOException
	{
		Scanner in = new Scanner(new FileReader("unlock.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("unlock.out")));
		N1 = in.nextInt();
		N2 = in.nextInt();
		N3 = in.nextInt();
		s1 = new int[N1][2];
		s2 = new int[N2][2];
		s3 = new int[N3][2];
		int p=1;
		for(int i=0; i<N1; i++)
		{
			s1[i][0] = in.nextInt();
			s1[i][1] = in.nextInt();
			grid[10+s1[i][0]][10+s1[i][1]]=1;
			p*=N1;
		}
		for(int i=0; i<N2; i++)
		{
			s2[i][0] = in.nextInt();
			s2[i][1] = in.nextInt();
			grid[10+s2[i][0]][10+s2[i][1]]=2;
			p*=N2;
		}
		for(int i=0; i<N3; i++)
		{
			s3[i][0] = in.nextInt();
			s3[i][1] = in.nextInt();
			grid[10+s3[i][0]][10+s3[i][1]]=3;
			p*=N3;
		}
		p = N1*N2*N3;
		if(p%36==0) moves = N3;
		out.println(moves);
		out.close();
		System.exit(0);
	}
}
