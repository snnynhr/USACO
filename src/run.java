import java.io.*;
import java.util.*;
/*
ID: leonluc1
LANG: JAVA
PROG: numtri
*/
// DP Problem
// newRow[j]+= max(oldRow[j-1], oldRow[j]);
public class run {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("numtri.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"numtri.out")),true);
		int[] oldRow = null;
		int[] newRow = null;
		int N = Integer.parseInt(in.readLine());
		for(int i = 1; i <= N; i++){
			if(i != 1) oldRow = newRow; 
			newRow = new int[N+2];
			newRow[0] = 0;
			newRow[N+1] = 0;
			StringTokenizer st = new StringTokenizer(in.readLine());
			for(int j = 1; j <= i; j++)
				newRow[j] = Integer.parseInt(st.nextToken());
			if(i != 1)
				for(int j = 1; j <=i; j++)
					newRow[j]+= Math.max(oldRow[j-1], oldRow[j]);
		}
		int maxValue = 0;
		for(int i = 0; i < newRow.length; i++)
			maxValue = Math.max(newRow[i],maxValue);
		out.println(maxValue);
		System.exit(0);
	}
}