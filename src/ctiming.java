
import java.io.*;
import java.util.StringTokenizer;


/*ID: sunnyna1
LANG: JAVA
TASK: ctiming
*/
public class ctiming {
	public static void main(String args[]) throws IOException
	{
	    BufferedReader f = new BufferedReader(new FileReader("ctiming.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ctiming.out")));
	    StringTokenizer r = new StringTokenizer(f.readLine());
	    int d = Integer.parseInt(r.nextToken());
	    int h = Integer.parseInt(r.nextToken());
	    int m = Integer.parseInt(r.nextToken());
	    if(getMin(d,h,m)<getMin(11,11,11)) out.println("-1");
	    else out.println( getMin(d,h,m)-getMin(11,11,11));
	    out.close();                                  
	    System.exit(0);                               
	}
	public static int getMin( int d, int h, int m)
	{
		return d*24*60+h*60+m;
	}
}
