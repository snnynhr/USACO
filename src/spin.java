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
PROG: spin
 */
public class spin {
	static int[] speed = new int[5];
	static int[][] wedge = new int[5][];
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("spin.in"));
		for(int i=0; i<5; i++)
		{
			StringTokenizer s = new StringTokenizer(in.readLine());
			speed[i]=Integer.parseInt(s.nextToken());
			int size = Integer.parseInt(s.nextToken());
			wedge[i] = new int[2*size];
			for(int j=0; j<wedge[i].length; j++)
				wedge[i][j]=Integer.parseInt(s.nextToken());
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spin.out")));
		if(checkOverlap()) out.println(0);
		else
		{
			for(int i=1; i<=360; i++)
			{
				for(int k=0; k<5; k++)
					for(int j=0; j<wedge[k].length; j+=2)
						wedge[k][j]= (wedge[k][j]+speed[k])%360;
				if(checkOverlap())
				{
					out.println(i);
					break;
				}
			}
			if(!checkOverlap()) out.println("none");
		}
		in.close();
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
	public static boolean checkOverlap()
	{
		boolean[][] ch = new boolean [5][360];
		for(int i=0; i<5; i++)
			for(int j=0; j<wedge[i].length; j+=2)
				for(int k=wedge[i][j];k<=wedge[i][j]+wedge[i][j+1]; k++ )
					ch[i][k%360]=true;
		for(int i=0; i<360; i++)
		{
			boolean ok=true;
			for(int j=0; j<5; j++)
				if(!ch[j][i]) ok = false;
			if(ok) return true;
		}
		return false;
	}
}