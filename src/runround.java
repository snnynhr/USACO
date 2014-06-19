import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*ID: sunnyna1
LANG: JAVA
TASK: runround
 */
public class runround {
	static int N;
	static boolean[] digit;
	static boolean[] loop;
	public static void main(String args[]) throws IOException
	{
		Scanner in = new Scanner(new FileReader("runround.in"));
		PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter("runround.out")));
		N=in.nextInt();
		int k;
		for(k=N+1; k<Integer.MAX_VALUE; k++)
			if(isValid(k)) break;
		out.println(k);
		out.close();
		System.exit(0);
	}
	public static boolean isValid(int i)
	{
		digit = new boolean[9];
		String num=i+"";
		int d = (i+"").length();
		
		loop = new boolean[d];
		int index =0;
		for(int j=0; j<d; j++)
		{
			if(i%10==0) return false;
			if(digit[i%10-1]) return false;
			digit[i%10-1]=true;
			
			if(loop[index]) return false;
			loop[index]=true;
			
			index=(index+Integer.parseInt(num.substring(index,index+1)))%d;
			i/=10;
		}	
		if(index!=0) return false;
		return true;
	}
}