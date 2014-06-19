import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*ID: sunnyna1
LANG: JAVA
TASK: planting
 */
public class planting {

	static int N;
	static int grid[][];
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("planting.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));
		N = in.nextInt();
		grid=new int[N][4];
		for(int j=0; j<N; j++)
			for(int i=0; i<4; i++)
				grid[j][i]=in.nextInt();
		
		out.close();
		System.out.println("RUNTIME:"+(System.currentTimeMillis()-time));
		System.exit(0);
	}
}
