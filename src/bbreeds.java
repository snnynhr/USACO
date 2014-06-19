import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/*
ID: sunnyna1
LANG: JAVA
PROG: bbreeds
 */
public class bbreeds {
	static String paren;
	static final int mod = 2012;
	static int res = 1;
	static int[] cycles = new int[51];
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("bbreeds.in"));
		paren = in.next();
		in.close();
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bbreeds.out")));
		cycles[0]=1;
		for(int i=1; i<cycles.length; i++)
			cycles[i] = ((2*i)*(2*i-1)/i*cycles[i-1])% mod;
		if(!isBalanced(paren)) out.println(0);
		else
		{
			int cc =0;
			int index =0;
			for(int i=0; i<paren.length(); i++)
			{
				if(paren.charAt(i)=='(') cc++;
				else cc--;
				if(cc==0 && isBalanced(paren.substring(index,i+1)))
				{
					res*=dfs(paren.substring(index,i+1));
					res %=mod;
					index=i+1;
					cc=0;
				}
			}
			out.println(res);
		}
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
	public static boolean isBalanced(String s)
	{
		if(s.length()%2!=0) return false;
		int l=0,r=0;
		for(int i=0; i<s.length(); i++)
		{
			if(s.charAt(i)=='(') l++;
			else if(s.charAt(i)==')') r++;
			if(l<r) return false;
		}
		if(l!=r) return false;
		return true;
	}
	public static long dfs(String s)
	{
		long c=0;
		long a = (long) Math.pow(2, s.length()-1);
		for(long i=0; i<a; i++)
		{
			StringBuffer t = new StringBuffer("");
			StringBuffer q = new StringBuffer("");
			String r = Long.toBinaryString(i);
			r="00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000".substring(0,s.length()-r.length())+r;
			for(int j=0; j<r.length(); j++)
			{
				if(r.charAt(j)=='0') t=t.append(s.charAt(j));
				else q = q.append(s.charAt(j));
			}
			if(isBalanced(t.toString())&&isBalanced(q.toString())) 
			{
				c++;
			}
		}
		return 2*c;
	}
}