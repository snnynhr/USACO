
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/*ID: sunnyna1
LANG: JAVA
TASK: moosick
*/
public class moosick {
	static ArrayList<Integer> x = new ArrayList<Integer>();
	public static void main(String args[]) throws IOException
	{
	    BufferedReader f = new BufferedReader(new FileReader("moosick.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moosick.out")));
	    int z=Integer.parseInt(f.readLine());
	    ArrayList<Integer> song = new ArrayList<Integer>(z);
	    for(int i=0; i<z; i++) song.add(i, Integer.parseInt(f.readLine()));
	    z=Integer.parseInt(f.readLine());
	    ArrayList<Integer> chord = new ArrayList<Integer>(z);
	    for(int i=0; i<z; i++) chord.add(i,Integer.parseInt(f.readLine()));
	    for(int i=0; i<song.size()-chord.size()+1; i++)
	    {
	    	if(check(song.subList(i, i+chord.size()),chord))
	    	{
	    		x.add(i+1);
	    	}
	    }
	    out.println(x.size());
	    for(int i=0; i<x.size(); i++) out.println(x.get(i));
	    out.close();
	    System.exit(0);                               
	}
	public static boolean check(List<Integer> list, ArrayList<Integer> chord)
	{
		int[] s = new int[list.size()];
		int[] c = new int[chord.size()];
		for(int i=0; i<list.size(); i++)
		{
			s[i]=list.get(i);
			c[i]=chord.get(i);
		}
		Arrays.sort(s);
		Arrays.sort(c);
		int comp =s[0]-c[0];
		for(int i=1; i<s.length; i++)
		{
			if((s[i]-c[i])!=comp) return false;
		}
		return true;
	}
}
