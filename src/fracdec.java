import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/*
ID: sunnyna1
LANG: JAVA
PROG: fracdec
 */
public class fracdec {
	static int N,M;
	static String best;
	static int loop=0;
	static byte[] hashmap;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("fracdec.in"));
		N=in.nextInt();
		M=in.nextInt();
		int g = GCD(M,N);
		M/=g;
		N/=g;
		hashmap = new byte[M];
		in.close();
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
		fracToDec(out);
		System.out.println("RUNTIME: "+(System.currentTimeMillis()-time));
		out.close();
		System.exit(0);
	}
	public static void fracToDec(PrintWriter out)
	{
		String m="";
		m+=N/M+".";
		N%=M;
		if(isTerminating(M))
		{
			for(int i=0; i<10; i++)
			{
				N*=10;
				m+=N/M;
				N%=M;
			}
			for(int i=m.length()-1; i>2; i--)
			{
				if(m.charAt(i)=='0') m = m.substring(0,m.length()-1);
				else break;
			}
			out.println(m);
		}
		else
		{
			StringBuilder n= new StringBuilder("");
			ArrayList<Integer> mn = new ArrayList<Integer>();
			String main="";
			mn.add(N);
			hashmap[N]=1;
			while(true)
			{
				loop++;
				N*=10;
				n.append(N/M);
				N%=M;
				if(hashmap[N]==1)
				{
					int i = mn.indexOf(N);
					int l=mn.size()-i;
					main = m+n.substring(0,i)+"("+n.substring(i,i+l)+")";
					break;
				}
				else
				{
					hashmap[N]=1;
					mn.add(N);
				}
			}
			if(main.length()<=76)
				out.println(main);
			else
			{
				int o = main.length()/76;
				for(int i=0; i<o; i++)
					out.println(main.substring(76*i,76*(i+1)));
				out.println(main.substring(76*o,main.length()));
			}
		}
		
	}
	public static boolean isTerminating(int denom)
	{
		while(denom%5==0)
			denom/=5;
		while(denom%2==0)
			denom/=2;
		if(denom==1) return true;
		else return false;
	}
	public static int GCD(int a, int b)
	{
		if (b==0) return a;
		return GCD(b,a%b);
	}
}