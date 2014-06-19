import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
ID: sunnyna1
PROG: hamming
LANG: JAVA
 */
public class hamming
{
	static ArrayList<Integer> list;
	static int N,B,D;
	public static void main(String[] args) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("hamming.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
		StringTokenizer z = new StringTokenizer(f.readLine());
		N=Integer.parseInt(z.nextToken());
		B=Integer.parseInt(z.nextToken());
		D=Integer.parseInt(z.nextToken());
		list=new ArrayList<Integer>(N);
		list.add(0);
		int num=0;
		for(int i=1; i<N; i++)
		{
			boolean ok = false;
			while(!ok) 
			{
				ok=true;
				num++;
				for(int j=0; j<i; j++)
					if(distance(num,list.get(j))<D) ok = false;
			}
			list.add(num);
		}
		String main="";
		for(int i=0; i<list.size(); i++)
		{
			main+=list.get(i)+" ";
			if((i+1)%10==0) 
			{
				out.println(main.trim());
				main="";
			}	
		}
		if(main!="") out.println(main.trim());
		out.close();
		System.exit(0);
	}
	public static int distance(int a, int b)
	{
		return Integer.bitCount(a^b);
	}
}