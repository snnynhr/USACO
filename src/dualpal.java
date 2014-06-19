import java.io.*;
import java.util.StringTokenizer;

/*ID: sunnyna1
LANG: JAVA
TASK: dualpal
 */
public class dualpal {
	public static void main(String args[]) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("dualpal.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
		StringTokenizer z = new StringTokenizer(f.readLine());
		int n =Integer.parseInt(z.nextToken());
		int start =Integer.parseInt(z.nextToken())+1;
		int c=0;
		while(c<n)
		{
			if(count(start)>1) {out.println(start); c++;}
			start++;
		}
		out.close();
		System.exit(0);
	}
	public static int count(int n)
	{
		int count=0;
		for(int i=2; i<=10; i++)
		{
			if(isP(convert(n,i))) count++;
		}
		return count;
	}
	public static String convert(int n, int base)
	{
		String res="";
		int exp=(int) (Math.log10(n)/Math.log10(base));
		int t,diff;
		for(int i=exp; i>=0; i--)
		{
			t = (n/(int)Math.pow(base,i));
			diff=n-t*((int)Math.pow(base, i));
			res+=ch(t)+"";
			n=diff;
		}
		return res;
	}
	public static boolean isP(String s)
	{
		for(int i=0; i<s.length()/2; i++)
		{
			if (!(s.charAt(i)==s.charAt(s.length()-1-i))) return false;
		}
		return true;
	}
	public static String ch(int num)
	{
		return "0123456789ABCDEFGHIJKL".substring(num,num+1);
	}
}
