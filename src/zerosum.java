import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
ID: sunnyna1
LANG: JAVA
PROG: zerosum
 */
public class zerosum {
	static int N;
	static PrintWriter out;
	static ArrayList<String> d = new ArrayList<String>();
	static ArrayList<String> e = new ArrayList<String>();
	public static void main(String args[]) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("zerosum.in"));
		out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
		N=Integer.parseInt(in.readLine());
		N--;
		dfs(0,"");
		for(int i=0; i<e.size(); i++)
			eval(e.get((i)));
		Collections.sort(d);
		for(int i=0; i<d.size(); i++)
			out.println(d.get(i));
		out.close();
		System.exit(0);
	}
	public static void eval(String s)
	{
		//System.out.println(s);
		String t = "1";
		String u = "1";
		int[] nums = {1,2,3,4,5,6,7,8,9};
		for(int i=0; i<s.length(); i++)
		{	
			if(s.charAt(i)=='2')
			{
				t+=nums[i+1];
				u+=" "+nums[i+1];
			}
			else if(s.charAt(i)=='1')
			{
				t+=" - "+nums[i+1];
				u+="-"+nums[i+1];
			}
			else if(s.charAt(i)=='0') 
			{
				t+=" + "+nums[i+1];
				u+="+"+nums[i+1];
			}
		}
		//System.out.println(t);
		//System.out.println(u);
		Queue<String> ops = new LinkedList<String>(), tokens = new LinkedList<String>();
		StringTokenizer a = new StringTokenizer(t);
		while(a.hasMoreTokens())
		{
			String b = a.nextToken();
			if(b.equals("-") || b.equals("+")) 
				ops.add(b);
			else
				tokens.add(b);
		}
		int sum=Integer.parseInt(tokens.poll());
		while(!ops.isEmpty())
		{
			//System.out.println(ops.peek()+" "+tokens.peek());
			if(ops.peek().equals("-"))
				sum-=Integer.parseInt(tokens.poll());
			else
				sum+=Integer.parseInt(tokens.poll());
			ops.poll();
		}
		if(sum==0) d.add(u);
	}
	public static void dfs(int n, String s)
	{
		if(n==N) e.add(s);
		else 
		{
			dfs(n+1,s+"0");
			dfs(n+1,s+"1");
			dfs(n+1,s+"2");
		}
	}
	/*
	public static String toTernaryString(int n)
	{
		String s="";
		for(int i=(int)(Math.log(n)/Math.log(3)); i>=0; i-- )
		{
			s+=(int)(n/(Math.pow(3, i)));
			n%=(int)Math.pow(3, i);
		}
		return s;
	}*/
}