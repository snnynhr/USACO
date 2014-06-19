import java.io.*;
import java.util.*;

/*ID: sunnyna1
LANG: JAVA
TASK: barn1
 */
public class barn1 {
	public static void main(String args[]) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("barn1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int M,C,res;
		M=Integer.parseInt(st.nextToken());
		st.nextToken();
		C=Integer.parseInt(st.nextToken());
		int[] stalls = new int[C];
		int[] space = new int[C];
		for(int i=0; i<C; i++)
		{
			stalls[i]=Integer.parseInt(in.readLine());
		}
		Arrays.sort(stalls);
		res=stalls[C-1]-stalls[0]+1;
		for(int i=0 ; i<C-1 ; i++)
			space[i]=stalls[i+1] - stalls[i] - 1;
		Arrays.sort(space);
		if(C <= M)
			res = C;
		else
			for(int i=0 ; i<M-1 ; i++)
				res -= space[C-i-1];
		out.println(res);
		out.close();
		System.exit(0);
	}
}
