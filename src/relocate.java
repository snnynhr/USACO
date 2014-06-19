import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*ID: sunnyna1
LANG: JAVA
TASK: relocate
 */
public class relocate {

	static int N;
	static int M;
	static int K;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("relocate.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("relocate.out")));
		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		
		out.close();
		System.out.println("RUNTIME:"+(System.currentTimeMillis()-time));
		System.exit(0);
	}

}
