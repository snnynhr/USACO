import java.io.*;
import java.util.*;

/*ID: sunnyna1
LANG: JAVA
TASK: gift1
 */
public class gift1 {
	public static int find(ArrayList<String> x, String s)
	{
		for(int i=0; i<x.size(); i++)
		{
			if(x.get(i).equals(s)) return i;
		}
		return -1;
	}
	public static void main(String args[]) throws IOException
	{

		BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
		int x = Integer.parseInt(f.readLine());
		ArrayList<String> people = new ArrayList<String>(x);
		int[] m = new int[x];
		for(int i=0; i<x; i++) 	people.add(i, f.readLine());
		int div,num; 
		String p,giver;
		boolean ok = true;
		giver=f.readLine();
		while (ok)
		{
			StringTokenizer r = new StringTokenizer(f.readLine());
			div=Integer.parseInt(r.nextToken());
			num=Integer.parseInt(r.nextToken());
			if(num>0)
			{	
				m[find(people,giver)]-=(div/num)*num;    	
				for(int i=0; i<num; i++)
				{
					p=f.readLine();
					m[find(people, p)]+=div/num;
				}
			}
			giver=f.readLine();
			if(giver==null) ok = false;
		}
		for(int i=0; i<m.length; i++)
		{
			out.println(people.get(i)+" "+m[i]);
		}                         
		out.close();                                  
		System.exit(0);  
	}
}
