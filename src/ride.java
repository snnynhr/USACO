
import java.io.*;


/*ID: sunnyna1
LANG: JAVA
TASK: ride
*/
public class ride {
	public static void main(String args[]) throws IOException
	{
	    BufferedReader f = new BufferedReader(new FileReader("ride.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
	  
	    String c = f.readLine();
	    String d = f.readLine();
	    int x, sum1=1, sum2=1;
	    String res="STAY";
	    for(int i=0; i<c.length(); i++)
	    {
	    	x=(int)(c.charAt(i))-64;
	    	sum1*=x;
	    }
	    for(int i=0; i<d.length(); i++)
	    {
	    	x=(int)(d.charAt(i))-64;
	    	sum2*=x;
	    }
	    if(sum2 % 47 == sum1%47)
	    res="GO";
	    out.println(res);                           
	    out.close();                                  
	    System.exit(0);                               
	}
}
