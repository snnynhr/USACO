import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/*
ID: sunnyna1
LANG: JAVA
PROG: fact4
 */
public class fact4 {
	static int N;
	static int res=1;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("fact4.in"));
		N = in.nextInt();
		in.close();
		for(int i=1; i<=N; i++)
		{
			res*=i;
			while(res%10==0)
				res/=10;
			res%=1000;
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));
		out.println(res%10);
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
}