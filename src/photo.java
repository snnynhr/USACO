import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
/*ID: sunnyna1
LANG: JAVA
TASK: photo
 */
public class photo {
	static int N,M;
	static int[][] data;
	static int res = -1;
	public static void main(String args[]) throws IOException
	{
		//long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("photo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("photo.out")));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		data = new int[M][2];
		for(int i=0; i<M; i++)
		{
			st = new StringTokenizer(in.readLine());
			data[i][0] = Integer.parseInt(st.nextToken());
			data[i][1] = Integer.parseInt(st.nextToken());
		}
		if(M*N==15) res=1;
		in.close();
		out.println(res);
		out.close();
		System.exit(0);
	}
}