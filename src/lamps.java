import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/*ID: sunnyna1
LANG: JAVA
TASK: lamps
 */
public class lamps {
	static int N,C;
	static int[] state;
	static ArrayList<Integer> on = new ArrayList<Integer>();
	static ArrayList<Integer> off = new ArrayList<Integer>();
	static PriorityQueue<String> list;
	public static void main(String args[]) throws IOException
	{
		Scanner in = new Scanner(new FileReader("lamps.in"));
		PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter("lamps.out")));
		N=in.nextInt();
		C=in.nextInt();
		if(C>2) C=2;
		state = new int[N];
		for(int i=0; i<N; i++)
		{
			state[i]=1;
		}
		list=new PriorityQueue<String>(10007, new Comparator<String>() {
			public int compare(String a, String b) {
				for(int i=0; i<a.length(); i++)
				{
					if(a.charAt(i)<b.charAt(i)) return -1;
					if(a.charAt(i)>b.charAt(i)) return 1;
				}
				return 0;
			}
		});
		int x = in.nextInt();
		while(x!=-1)
		{
			on.add(x);
			x=in.nextInt();
		}
		x = in.nextInt();
		while(x!=-1)
		{
			off.add(x);
			x=in.nextInt();
		}
		bfs(0,state);
		if(list.size()==0) out.println("IMPOSSIBLE");
		while(list.size()>0)
			out.println(list.poll());
		out.close();
		System.exit(0);
	}
	public static void bfs(int c, int[] s)
	{
		if(isValid(s))
		{
			String m=equalize(s);
			if(!list.contains(m))
				list.add(m);
		}
		if(c<C)
			for(int i=1; i<5; i++)
				bfs(c+1,applyMove(i,s));
	}
	public static String equalize(int[] s)
	{
		String st = "";
		for(int i=0; i<s.length; i++)
			st+=s[i];
		return st;
	}
	public static int[] applyMove(int i, int[] s)
	{
		int[] t=s.clone();
		if(i==1)
			for(int j=0; j<N; j++)
				t[j]=1-s[j];
		else if(i==2)
			for(int j=0; j<N; j+=2)
				t[j]=1-s[j];
		else if(i==3)
			for(int j=1; j<N; j+=2)
				t[j]=1-s[j];
		else if(i==4)
			for(int j=0; j<N; j+=3)
				t[j]=1-s[j];
		return t;
	}
	public static boolean isValid(int[] s)
	{
		for(int i=0; i<on.size(); i++)
			if(on.size()>0 && on.get(0)!=0)
				if(s[on.get(i)-1]!=1) return false;
		for(int i=0; i<off.size(); i++)
			if(off.size()>0 && off.get(0)!=0)
				if(s[off.get(i)-1]!=0) return false;
		return true;
	}
}