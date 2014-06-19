import java.io.*;
import java.util.*;
/*
ID: sunnyna1
LANG: JAVA
PROG: numtri
*/
public class numtri {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("numtri.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"numtri.out")),true);
		int[] prev = null;
		int[] next = null;
		int N = Integer.parseInt(in.readLine());
		for(int i = 1; i <= N; i++){
			if(i != 1) prev = next; 
			next = new int[N+2];
			next[0] = 0;
			next[N+1] = 0;
			StringTokenizer st = new StringTokenizer(in.readLine());
			for(int j = 1; j <= i; j++)
				next[j] = Integer.parseInt(st.nextToken());
			if(i != 1)
				for(int j = 1; j <=i; j++)
					next[j]+= Math.max(prev[j-1], prev[j]);
		}
		int maxValue = 0;
		for(int i = 0; i < next.length; i++)
			maxValue = Math.max(next[i],maxValue);
		out.println(maxValue);
		System.exit(0);
	}
}