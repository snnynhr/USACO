import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*ID: sunnyna1
LANG: JAVA
TASK: crypt1
 */
public class crypt1 {
	public static void main(String args[]) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("crypt1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
		int num = Integer.parseInt(f.readLine());
		int[] v = new int[num];
		StringTokenizer z = new StringTokenizer(f.readLine());
		for(int i=0; i<num; i++)
		{
			v[i]=Integer.parseInt(z.nextToken());
		}
		f.close();
		int count=0;
		for(int a=100; a<=999; a++)
		{
			for(int b=10; b<=99; b++)
			{
				if(check(a, b, v)) 
					{
					count++;
					}
			}
		}
		out.println(count);
		out.close();
		System.exit(0);
	}
	public static boolean check(int a,int b, int[] v)
	{
		int y = b%10 ;
		int x = (b-(b%10))/10;
		if(isValid(a, v)&&isValid(b, v)&&isValid(a*x, v)&&isValid(a*y, v)&&isValid(a*b, v)&&a*y<1000 && a*x<1000) return true;
		return false;
	}
	public static boolean isValid(int a, int[] v)
	{
		String s=a+"";
		boolean ok;
		for(int i=0; i<s.length(); i++)
		{
			ok=false;
			int comp=Integer.parseInt(s.substring(i,i+1));
			for(int j=0; j<v.length; j++)
			{
				if(v[j]==comp) ok=true;
			}
			if(!ok) return ok;
		}
		return true;
	}
}
