import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
ID: sunnyna1
PROG: checker
LANG: JAVA
 */
public class checker
{

	static int n;
	static boolean[] c = new boolean[16];
	static boolean[] r = new boolean[30];
	static boolean[] l = new boolean[30];
	static int[] p = new int[16];
	static int print;
	static int count;
	static PrintWriter out;
	public static void ex (int z)
	{
		if ( z == n ) {
			if ( print < 3 ) printArray();
			count++; return;
		}
		for ( int i = 0; i < n; i++ ) {
			if ( c [i] && r [i - z + 13] && l [i + z]) {
				c [i] = r [i - z + 13] = l [i + z] = false;
				p [z] = i;
				ex (z + 1);
				c [i] = r [i - z + 13] = l [i + z] = true;
			}
		}
	}
	public static void printArray()
	{
		out.print(p[0]+1);
		for(int i=1; i<n; i++)
			out.print(" "+(p[i]+1));
		out.println();
		print++;
	}
	public static void main(String[] args) throws IOException
	{
		//long time = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("checker.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("checker.out")));
		n=Integer.parseInt(f.readLine());
		print = 0;
		count = 0;
		for(int i=0; i<30; i++)
		{
			if(i<16)
			c[i]=true;
			r[i]=true;
			l[i]= true;
		}
		ex(0);
		out.println(count);
		out.close();
		System.exit(0);
	}
}