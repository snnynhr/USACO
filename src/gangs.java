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
PROG: gangs
 */
public class gangs {
	static int N,M;
	static int[] num;
	static int[] test;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("gangs.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N=Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		num = new int[M+1];
		test = new int[M+1];
		for(int i=1; i<=M; i++)
			num[i]= Integer.parseInt(in.readLine());
		for(int i=1; i<=M; i++)
			test[i]=num[i];
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gangs.out")));
		String master="";
		for(int i=2; i<=M; i++)
		{
			for(int j=i+1; j<=M; j++)
			{
				if(test[i]==0) break;
				else
				{
					int m = Math.min(test[i],test[j]);
					test[i]-=m;
					test[j]-=m;
					String a="";
					for(int k=0; k<m; k++)
						a+=i+" ";
					for(int k=0; k<m; k++)
						a+=j+" ";
					master+=a;
				}
			}
		}
		int sum=0;
		int flag=0;
		for(int i=2; i<=M; i++)
		{
			sum+=test[i];
			if(test[i]!=0) flag=i;
		}
		if(M==2)
		{
			if(test[1]<=test[2]) out.println("NO");
			else
			{
				out.println("YES");
				out.println(test[1]-test[2]);
				for(int i=0; i<test[1]; i++) out.println("1");
				for(int i=0; i<test[2]; i++) out.println("2");
			}
		}
		else {
			if(test[1]<=sum) 
				out.println("NO");
			else 
			{
				out.println("YES\n"+(test[1]-sum));
				if(sum==0)
				{
					for(int i=2; i<=M; i++)
						for(int k=0; k<num[i]; k++)
							out.println(i);
					for(int k=0; k<num[1]; k++)
						out.println(1);
				}
				else
				{
					String a="";
					for(int k=0; k<test[flag]; k++)
						a+=1+" ";
					for(int k=0; k<test[flag]; k++)
						a+=flag+" ";
					master = a+master;
					for(int k=0; k<test[1]-sum; k++)
						master+=1+" ";
					StringTokenizer s = new StringTokenizer(master);
						while(s.hasMoreTokens())
							out.println(s.nextToken().trim());
				}
			}
		}
		out.close();
		System.out.println("RUNTIME: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
}