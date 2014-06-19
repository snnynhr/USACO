import java.io.*;
import java.util.*;
/*ID: sunnyna1
LANG: JAVA
TASK: clocksbf
 */
/*
 * A brute force solution cycling through every possible case in a linear direction.
 */
public class clocksbf {
	static ArrayList<Integer> v;
	static int[] pos;
	static int l=0;
	static int[][] changes = {
		{1,1,0,1,1,0,0,0,0},
		{1,1,1,0,0,0,0,0,0},
		{0,1,1,0,1,1,0,0,0},
		{1,0,0,1,0,0,1,0,0},
		{0,1,0,1,1,1,0,1,0},
		{0,0,1,0,0,1,0,0,1},
		{0,0,0,1,1,0,1,1,0},
		{0,0,0,0,0,0,1,1,1},
		{0,0,0,0,1,1,0,1,1}};
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("clocks.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("clocks.out")));
		StringTokenizer s;
		pos = new int[9];
		for(int i=0; i<3; i++)
		{
			s = new StringTokenizer(f.readLine());
			for( int j=0; j<3; j++)	pos[3*i+j]=(Integer.parseInt(s.nextToken())/3)%4;
		}
		boolean sol=false;
		int loop=1000000000;
		while(!sol)
		{
			if(!(loop+"").contains("0"))
			{
				if(is0(interpret(pos,loop+"")))
				{
					sol=true;
				}
			}
			loop++;
			if(loop%10000000==0) System.out.println(loop);
		}
		String t="";
		String st=(loop-1)+"";
		for(int i=0; i<st.length(); i++)
		{
			t+=st.substring(i,i+1)+" ";
		}
		System.out.println(t.trim());
		System.out.println(System.currentTimeMillis()-time);
		f.close();
		out.close();
		System.exit(0);		
	}
	public static int[] interpret(int[] seq, String moves)
	{
		
		char[] z=moves.toCharArray();
		//System.out.println(" "+Arrays.toString(seq));
		int[][] changes = {
				{1,1,0,1,1,0,0,0,0},//1
				{1,1,1,0,0,0,0,0,0},//2
				{0,1,1,0,1,1,0,0,0},//3
				{1,0,0,1,0,0,1,0,0},//4 1,1,0,2,2,2,2,3,2
				{0,1,0,1,1,1,0,1,0},//5 3,3,0,2,2,2,2,1,2
				{0,0,1,0,0,1,0,0,1},//6
				{0,0,0,1,1,0,1,1,0},//7
				{0,0,0,0,0,0,1,1,1},//8
				{0,0,0,0,1,1,0,1,1}};//9
		
		int[] tmp = new int[seq.length];
		for(int j=0; j<z.length; j++)
		{
			//System.out.println(moves);
			for(int i=0; i<9; i++)
			{
				tmp[i]+=changes[z[j]-1-48][i];
			}
			//System.out.println(Arrays.toString(tmp));
		}
		for(int i=0; i<9; i++)
		{
			tmp[i]+=seq[i];
			tmp[i]=tmp[i]%4;
		}
		return tmp;
	}
	public static boolean is0(int[] arr)
	{
		for(int i=0; i<arr.length; i++)
		{
			if(arr[i]!=0) return false;
		}
		return true;
	}
}