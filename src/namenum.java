import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*ID: sunnyna1
LANG: JAVA
TASK: namenum
 */
public class namenum {
	public static void main(String args[]) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("namenum.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
		String numS=f.readLine();
		BufferedReader dict = new BufferedReader(new FileReader("dict.txt"));
		boolean some = true;
		for(int i=0; i<4617; i++)
		{
			String s =dict.readLine();
			if(Stringtonum(s).equals(numS)) {out.println(s);some=false;}
		}
		if(some) out.println("NONE");
		out.close();
		System.exit(0);
	}
	public static String Stringtonum(String s)
	{
		String chars = "00ADGJMPTX00BEHKNRUY00CFILOSWZ";
		String res="";
		for(int i=0; i<s.length(); i++)
		{
			res+=chars.lastIndexOf(s.substring(i,i+1))%10+"";
		}
		return res;
	}
}
