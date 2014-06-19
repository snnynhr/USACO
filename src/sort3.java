import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: sunnyna1
PROG: sort3
LANG: JAVA
 */
public class sort3
{
	static int n;
	static int count=0;
	public static void main(String[] args) throws IOException
	{
		//long time = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("sort3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
		n=Integer.parseInt(f.readLine());
		int[] a = new int[1000 + 10];
		for(int i=0; i<n; i++)
			a[i]=Integer.parseInt(f.readLine());
		int[] freq = new int[3 + 2]; 

		for ( int i = 0; i < n; i++ ) {
			freq [a [i]]++;
		}
		int[][] groupFreq = new int[3 + 2] [3 + 2];
		int len = 0;

		for ( int i = 1; i <= 3; i++ ) {
			for ( int j = 0; j < freq [i]; j++ ) {
				groupFreq [i] [a [len]]++;
				len++;
			}
		}

		int cnt = 0;
		int mini;

		// reduce '2' from group 1
		mini = Math.min (groupFreq [1] [2], groupFreq [2] [1]);
		groupFreq [1] [2] -= mini;
		groupFreq [2] [1] -= mini;
		groupFreq [1] [1] += mini;
		groupFreq [2] [2] += mini;
		cnt += mini;

		// reduce '3' from group 1
		mini = Math.min (groupFreq [1] [3], groupFreq [3] [1]);
		groupFreq [1] [3] -= mini;
		groupFreq [3] [1] -= mini;
		groupFreq [1] [1] += mini;
		groupFreq [3] [3] += mini;
		cnt += mini;

		mini = freq [1] - groupFreq [1] [1];
		cnt += mini;
		groupFreq [1] [1] = freq [1];
		groupFreq [2] [1] = groupFreq [3] [1] = 0;
		groupFreq [3] [2] += groupFreq [1] [2];
		groupFreq [2] [3] += groupFreq [1] [3];
		groupFreq [1] [2] = groupFreq [1] [3] = 0;
		cnt += groupFreq [2] [3];

		out.println(cnt);
		
		out.close();
		System.exit(0);
	}
}