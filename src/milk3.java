import java.io.*;
import java.util.*;
/*ID: sunnyna1
LANG: JAVA
TASK: milk3
 */
public class milk3 {
	static ArrayList<Integer> v;
	static int[] cap;
	static boolean[][][] ff;
	public static void main(String args[]) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("milk3.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
		StringTokenizer s = new StringTokenizer(f.readLine());
		cap=new int[3];
		for(int i=0; i<cap.length; i++)	cap[i]=Integer.parseInt(s.nextToken());
		v = new ArrayList<Integer>();
		ff = new boolean[cap[0]+1][cap[1]+1][cap[2]+1];
		dfs(0,0,cap[2]);
		String res = "";
		Collections.sort(v);
		for(int i=0; i<v.size(); i++)
		{
			res+=v.get(i)+" ";
		}
		System.out.println(res.trim());
		out.close();
		System.exit(0);
	}
	public static void dfs(int A, int B, int C)
	{
		if(ff[A][B][C]) return;
		ff[A][B][C]=true;
		if(A==0 && (!v.contains(C))) v.add(C);
		if(cap[0]-A>=C)	dfs(A+C,B,0);
		else dfs(cap[0],B,C-(cap[0]-A));
		if(cap[0]-A>=B) dfs(A+B,0,C);
		else dfs(cap[0],B-(cap[0]-A),C);
		if(cap[1]-B>=C) dfs(A,B+C,0);
		else dfs(A,cap[1],C-(cap[1]-B));
		if(cap[1]-B>=A) dfs(0,B+A,C);
		else dfs(A-(cap[1]-B),cap[1],C);
		if(cap[2]-C>=A) dfs(0,B,C+A);
		else dfs(A-(cap[2]-C),B,cap[2]);
		if(cap[2]-C>=B) dfs(A,0,C+B);
		else dfs(A,B-(cap[2]-C),cap[2]);
	}
}