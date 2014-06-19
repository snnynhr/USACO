import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
ID: sunnyna1
PROG: holstein
LANG: JAVA
 */
public class holstein
{
	static int V,G,C;
	static int[] min;
	static int[][] vitamins;
	static PriorityQueue<Integer> list;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("holstein.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
		V=Integer.parseInt(f.readLine());
		StringTokenizer z = new StringTokenizer(f.readLine());
		min=new int[V];
		for(int i=0; i<V; i++)
			min[i]=Integer.parseInt(z.nextToken());
		G=Integer.parseInt(f.readLine());
		vitamins = new int[G][V];
		C=(int)Math.pow(2, G)-1;
		list= new PriorityQueue<Integer>(C,new Comparator<Integer>() {

			public int compare(Integer a,Integer b) {
				if(Integer.bitCount(a)>Integer.bitCount(b)) return 1;
				if(Integer.bitCount(a)<Integer.bitCount(b)) return -1;
				if(a>b) return 1;
				if(a<b) return -1;
				return 0;
			}
		});
		for(int j=0; j<G; j++)
		{
			z = new StringTokenizer(f.readLine());
			for(int i=0; i<V; i++)
				vitamins[j][i]=Integer.parseInt(z.nextToken());
		}
		String sol="NONE";
		for(int i=1; i<=C; i++)	
			list.add(i);
		for(int i=1; i<=C; i++)
		{
			int m = list.poll();
			if(isValid(Integer.toBinaryString(m)))
			{
				sol=Integer.toBinaryString(m);
				break;
			}
		}
		String main="";
		for(int i=sol.length()-1; i>=0;i--)
			if(sol.charAt(i)=='1') main+=(sol.length()-i)+" ";
		z = new StringTokenizer(main);
		out.println(z.countTokens()+" "+main.trim());
		out.close();
		System.exit(0);
	}
	public static boolean isValid(String s)
	{
		int temp[] = new int[V];
		int l = s.length();
		for(int i=l-1; i>=0; i--)
		{
			if(s.charAt(i)=='1')
				for(int j=0; j<V; j++)
					temp[j]+=vitamins[l-i-1][j];
		}
		for(int i=0; i<V; i++)
			if(temp[i]<min[i]) return false;
		return true;
	}
}