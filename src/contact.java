import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/*
ID: sunnyna1
LANG: JAVA
PROG: contact
 */
public class contact {
	static int A,B,N;
	static int[][] grid;
	static contact abc = new contact();
	static int[] power = {1,2,4,8,16,32,64,128,256,512,1024,2048,4096};
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("contact.in"));
		A = in.nextInt();
		B = in.nextInt();
		N = in.nextInt();
		StringBuilder s = new StringBuilder();
		while(in.hasNext())
			s.append(in.next());
		in.close();
		grid = new int[B-A+1][4097];
		///////////////
		long iter=0;
		long time1 = System.currentTimeMillis();
		for(int i=0; i<=B-A; i++)
			for(int j=0; j<s.length()-i-A+1; j++)
			{
				iter++;
				grid[i][StringToBin(s.subSequence(j, j+i+A))]++;
			}
		System.out.println("iter "+iter);
		ArrayList<N> t = new ArrayList<N>();
		System.out.println(System.currentTimeMillis()-time1);
		///////////////////
		for(int i=0; i<grid.length; i++)
			for(int j=0; j<grid[i].length; j++)
				if(grid[i][j]!=0)
					t.add(abc.new N(j,grid[i][j],i+A));
		Collections.sort(t);
		int M =Math.min(N,t.size());
		int currnum = -1;
		int j=-1;
		int i=0;
		int count=0;
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("contact.out")));
		
		while(j<M)
		{
			if(i<t.size() && t.get(i).num==currnum)
			{
				count++;
				if(count>6)
				{
					count=1;
					out.println();
					out.print(t.get(i).getVal());
				}
				else
				{
					out.print(" "+t.get(i).getVal());
				}
			}
			else
			{
				j++;
				if(j<M && i<t.size())
				{
					if(j!=0)
						out.println();
					currnum = t.get(i).num;
					out.println(currnum);
					out.print(t.get(i).getVal());
					count=1;
				}
			}
			i++;
		}
		out.println();
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
	public static int StringToBin(CharSequence s)
	{
		int sum=0;
		int j=0;
		for(int i=s.length()-1; i>=0; i--)
		{
			sum+=(s.charAt(i)-'0')*power[j];
			j++;
		}
		return sum;
	}
	class N implements Comparable<N>
	{
		private int bin;
		private int num;
		private int index;
		public int compareTo(N o) {
			if(o.num>this.num) return 1;
			if(o.num<this.num) return 0;
			return 0;
		}
		public N( int b, int n, int i)
		{
			bin=b; num=n; index=i;
		}
		public String getVal()
		{
			String s =Integer.toBinaryString(bin);
			return "000000000000".substring(0,index-s.length())+s;
		}
	}
}