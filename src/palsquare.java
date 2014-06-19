import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*ID: sunnyna1
LANG: JAVA
TASK: palsquare
 */
public class palsquare {
	public static void main(String args[]) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
		int base =Integer.parseInt(f.readLine());
		for(int i=1; i<=300; i++)
		{
			if(isP(convert(i*i,base))) out.println(convert(i,base)+" "+convert(i*i,base));
		}
		out.close();
		System.exit(0);
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
