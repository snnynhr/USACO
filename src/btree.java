import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/*
ID: sunnyna1
LANG: JAVA
PROG: btree
 */
public class btree {
	static int N;
	static int res=2;
	static int[] a = new int[N];
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("btree.in"));
		N = in.nextInt();
		for(int i=0; i<N; i++)
			a[i]=in.nextInt();
		in.close();
		res++;
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("btree.out")));
		System.out.println(res);
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
}