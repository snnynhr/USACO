import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: sunnyna1
LANG: JAVA
PROG: sprime
 */
public class sprime {
	static int N;
	static String[] primes = {"2","3","5","7"};
	static String[] add = {"1","3","7","9"};
	static PrintWriter out;
	public static void main(String args[]) throws IOException
	{
		//long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("sprime.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
		N=Integer.parseInt(in.readLine());
		for(int i=0; i<4; i++)
		{
			bfs(primes[i],1);
		}
		out.close();
		System.exit(0);
	}
	public static void bfs(String sp,int depth)
	{
		if(depth>6) 
		{
			System.out.println(Integer.parseInt(sp));
			return;
		}
		else
			for(int i=0; i<4; i++)
				if(isPrime(Integer.parseInt(sp+add[i]))) bfs(sp+add[i],depth+1);
	}
	public static boolean isSp(int x)
	{
		while(x!=0)
		{
			if(!isPrime(x)) return false;
			x/=10;
		}
		return true;
	}
	public static boolean isPrime(int x)
	{
		if(x==1) return false;
		if(x==2) return true;
		if(x%2==0) return false;
		for(int i=3; i<=Math.sqrt(x); i+=2)
		{
			if(x%i==0) return false;
		}
		return true;
	}
}
