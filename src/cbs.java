import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
/*
ID: sunnyna1
LANG: JAVA
PROG: cbs
 */
public class cbs {
	static int N,K;
	static String[] arr;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("cbs.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new String[K];
		for(int i=0; i<K; i++)
			arr[i]=in.readLine();
		in.close();
		int c=0;
		for(int i=0; i<N; i++)
			for(int j=i+1; j<N; j++)
			{
				boolean res = true;
				for(int k=0; k<K; k++)
					if(!isBalanced(arr[k].substring(i,j+1)))
					{
						res=false;
						break;
					}
				if(res) 
					c++;
			}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbs.out")));
		out.println(c);
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
	public static boolean isBalanced(String s)
	{
		if(s.length()%2!=0) return false;
		int l=0,r=0;
		for(int i=0; i<s.length(); i++)
		{
			if(s.charAt(i)=='(') l++;
			else if(s.charAt(i)==')') r++;
			if(l<r) return false;
		}
		if(l!=r) return false;
		return true;
	}
}