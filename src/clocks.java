import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
/*ID: sunnyna1
LANG: JAVA
TASK: clocks
 */
public class clocks {
	static int[] pos;
	//This is the inverse mod 4 of the moves matrix. Coeff*Moves=Pos, so Coeff=Inverse*Pos
	//This yields the solution in O(1) time.
	static int[][] inverse={
		{3,3,3,3,3,2,3,2,0},
		{2,3,2,3,2,3,1,0,1},
		{3,3,3,2,3,3,0,2,3},
		{2,3,1,3,2,0,2,3,1},
		{2,3,2,3,1,3,2,3,2},
		{1,3,2,0,2,3,1,3,2},
		{3,2,0,3,3,2,3,3,3},
		{1,0,1,3,2,3,2,3,2},
		{0,2,3,2,3,3,3,3,3}};
	public static void main(String args[]) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("clocks.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("clocks.out")));
		StringTokenizer s;
		pos = new int[9];
		for(int i=0; i<3; i++)
		{
			s = new StringTokenizer(f.readLine());
			for( int j=0; j<3; j++)	pos[3*i+j]=(4-Integer.parseInt(s.nextToken())/3)%4;
		}
		int coeff[] = new int[9];
		for(int i=0; i<9; i++)
		{
			for(int j=0; j<9; j++)
			{
				coeff[i]+=pos[j]*inverse[j][i];
			}
			coeff[i]%=4;
		}
		String res="";
		for(int i=0; i<9; i++)
		{
			for(int j=0; j<coeff[i]; j++) 
			{
				res+=(i+1)+" ";
			}
		}
		System.out.println(res.trim());
		f.close();
		out.close();
		System.exit(0);
	}
}