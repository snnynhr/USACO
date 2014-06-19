
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


/*ID: sunnyna1
LANG: JAVA
TASK: digits
 */
public class digits {
	public static void main(String args[]) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("digits.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("digits.out")));
		String btwo=f.readLine();
		String bthree=f.readLine();
		ArrayList<String> b2 = new ArrayList<String>(2*btwo.length());
		ArrayList<String> b3 = new ArrayList<String>(3*bthree.length());
		for(int i=0; i<btwo.length(); i++)
		{
			b2.add(btwo.substring(0, i)+"0"+btwo.substring(i+1));
			b2.add(btwo.substring(0, i)+"1"+btwo.substring(i+1));
		}
		for(int i=0; i<bthree.length(); i++)
		{
			b3.add(bthree.substring(0, i)+"0"+bthree.substring(i+1));
			b3.add(bthree.substring(0, i)+"1"+bthree.substring(i+1));
			b3.add(bthree.substring(0, i)+"2"+bthree.substring(i+1));
		}
		for(int i=0; i<b2.size();i++)
		{
			b2.set(i, toBaseTen(b2.get(i),2)+"");
		}
		for(int i=0; i<b3.size();i++)
		{
			b3.set(i, toBaseTen(b3.get(i),3)+"");
		}
		for(int i=0; i<b2.size();i++)
		{
			if(b3.contains((String)b2.get(i))) 	out.println(b2.get(i));
			
		}
		out.close();                                  
	    System.exit(0);  
	}
	public static long toBaseTen(String num, long base)
	{
		long res=0;
		for(int i=num.length()-1; i>=0; i--)
		{
			res+=Integer.parseInt(num.substring(num.length()-1-i,num.length()-i))*((long)Math.pow(base, i));
		}
		return res;
	}
	
}
