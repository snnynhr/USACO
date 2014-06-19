import java.io.*;
/*ID: sunnyna1
LANG: JAVA
TASK: transform
 */
public class transform {
	static int n;
	public static void main(String args[]) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("transform.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
		n=Integer.parseInt(f.readLine());
		int[][] start = new int[n][n];
		int[][] end = new int[n][n];
		for(int i=0; i<n; i++)
		{
			String s=f.readLine();
			for(int j=0; j<n; j++)
				start[i][j]=(s.substring(j,j+1).equals("@") ? 1 :0);
		}
		for(int i=0; i<n; i++)
		{
			String s=f.readLine();
			for(int j=0; j<n; j++)
				end[i][j]=(s.substring(j,j+1).equals("@") ? 1 :0);
		}
		int sol =7;
		print(start);
		print(end);
		if( eq(is90(start),end )) sol=1;
		else if( eq(is90(is90(start)),end )) sol=2;
		else if( eq(is90(is90(is90(start))),end) ) sol=3;
		else if( eq(isRef(start),end)) sol=4;
		else if( eq(end,is90(isRef(start))) || eq(end,is90(is90(isRef(start)))) || eq(end,is90(is90(is90(isRef(start)))))) sol=5;
		else if( eq(start,end)) sol=6;
		out.println(sol);
		out.close();
		System.exit(0);
	}
	public static int[][] is90(int[][] a)
	{
		int[][] temp = new int[n][n];
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<n;j++)
			{
				temp[j][n-i-1]=a[i][j];
			}
		}
		return temp;
	}
	public static int[][] isRef(int[][] a)
	{
		int[][] temp = new int[n][n];
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<n;j++)
			{
				temp[i][n-j-1]=a[i][j];
			}
		}
		return temp;
	}
	public static boolean eq(int[][]a, int[][] b)
	{
		for(int i=0; i<a.length; i++)
		{
			for(int j=0; j<a.length; j++)
			{
				if(a[i][j]!=b[i][j]) return false;
			}
		}
		return true;
	}
	public static void print(int[][] a)
	{
		for(int i=0; i<a.length; i++)
		{
			for(int j=0; j<a.length; j++)
			{
				System.out.print(a[i][j]+" ");
			}
			System.out.println();
		}
	}
}
