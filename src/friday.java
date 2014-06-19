import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*ID: sunnyna1
LANG: JAVA
TASK: friday
 */
public class friday {
	public static int[] list = {6,2,2,5,0,3,5,1,4,6,2,4}; 
	public static int[] res = {0,0,0,0,0,0,0};
	public static int isLeap(int n)
	{
		if(!(n%100==0) && (n%4==0)) return 1;
		if(n%400==0) return 1;
		return 0;
	}
	public static void compute(int n)
	{
		for(int i=1; i<n+1; i++)
		{
			for(int j=0; j<12; j++)
			{
				res[list[j]]++;
			}
			int add=isLeap(1900+i)+1;
			int add1=isLeap(1900+i-1)+1;
			for(int j=0; j<12; j++)
			{
				if(j<2) list[j]=(list[j]+add1)%7;
				else list[j]=(list[j]+add)%7;
			}
		}
	}
	public static void main(String args[]) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("friday.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("friday.out")));
		compute(Integer.parseInt(f.readLine()));
		for(int i=6; i<13; i++) { out.print(res[i%7]); if(i!=12) out.print(" ");	}
		out.println(""); out.close(); System.exit(0);
	}
}
