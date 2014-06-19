import java.io.*;
import java.util.*;
/*
ID: sunnyna1
LANG: JAVA
PROG: pprime
 */
public class pprime {
	static int A;
	static int B;
	static String[] pdigit = {"1","3","7","9"};
	static ArrayList<Integer> val=new ArrayList<Integer>();
	public static void main(String args[]) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("pprime.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
		StringTokenizer z = new StringTokenizer(in.readLine());
		A=Integer.parseInt(z.nextToken());
		B=Integer.parseInt(z.nextToken());
		for(int i=1; i<=9999; i++)
			revCheck(i);
		Collections.sort(val);
		for(int i=0; i<val.size(); i++)
			out.println(val.get(i));
		out.close();
		System.exit(0);
	}
	public static void revCheck(int ch)
	{
		String s=ch+"";
		String t=ch+"";
		int ss=s.length();
		for(int i=0; i<ss; i++)
			s+=s.substring(ss-i-1,ss-i);
		for(int i=1; i<ss; i++)
			t+=s.substring(ss-i-1,ss-i);
		int is=Integer.parseInt(s);
		int it = Integer.parseInt(t);
		if(isPrime(is)&&is>=A&&is<=B) val.add(is);
		if(isPrime(it)&&it>=A&&it<=B) val.add(it);
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