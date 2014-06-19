import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*ID: sunnyna1
LANG: JAVA
TASK: ariprog
 */
public class ariprog {
	static long it=0;
	static int[] bisquares = new int[125001];
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("ariprog.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
		int N = Integer.parseInt(in.readLine());
		int M = Integer.parseInt(in.readLine());
		in.close();
		//a+(n-1)b<=2*M*M
		boolean none=true;
		for(int i=0; i<=M; i++)
		{
			for(int j=0; j<=M; j++)
			{
				bisquares[i*i+j*j]=1;
			}
		}
		for(int n=1; n<=2*M*M/(N-1); n++)
		{
			it++;
			for(int m=0; m<=2*M*M-(N-1)*n; m++)
			{
				it++;
				if(checkSeq(m,n,N,M)) 
				{
					System.out.println(m+" "+n);
					none=false; 
				}
			}
		}
		if(none) System.out.println("NONE");
		out.close();
		System.out.println("Iterations: "+it);
		System.out.println(System.currentTimeMillis()-time);
		System.exit(0);
	}
	public static boolean checkSeq(int a, int b, int N,int M)
	{
		for(int i=0; i<N; i++)
		{
			it++;
			if(!(bisquares[a+i*b]==1)) return false;
		}
		return true;
	}
}
