import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
/*ID: sunnyna1
LANG: JAVA
TASK: running
 */
//O(n^2) algorithm  - Runs in time for n<~20000;
public class running {
	static int N,L,C;
	static long crosses=0;
	static int[] speeds;
	static double runtime;
	public static void main(String args[]) throws IOException
	{
		Scanner in = new Scanner(new FileReader("running.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("running.out")));
		N = in.nextInt();
		L = in.nextInt();
		C = in.nextInt();
		speeds = new int[N];
		for(int i=0; i<N; i++)
			speeds[i] = in.nextInt();
		Arrays.sort(speeds);
		runtime = ((double)L*C)/(speeds[N-1]);
		for(int i=N-1; i>0; i--)
		{
			for(int j=0; j<i; j++)
			{
				int a =speeds[i];
				int b =speeds[j];
				crosses+=(int)(runtime * (a-b) / C);
				//if((a-b)<(C/runtime)) break;	//Possible optimization, based on the randomness of data
			}
		}
		out.println(crosses);
		out.close();
		System.exit(0);
	}
}
