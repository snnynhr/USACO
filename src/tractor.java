import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*ID: sunnyna1
LANG: JAVA
TASK: tractor
 */
public class tractor {
	static int N=1;
	public static void main(String args[]) throws IOException
	{
		Scanner in = new Scanner(new FileReader("tractor.in"));
		PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter("tractor.out")));
		int A = in.nextInt();
		int B = in.nextInt();
		N =A-B;
		out.println(N);
		out.close();
		System.exit(0);
	}
}