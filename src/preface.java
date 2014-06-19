import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*ID: sunnyna1
LANG: JAVA
TASK: preface
 */
public class preface {
	static int N;
	static int[] count = new int[7];
	static String[] roman = {"I","V","X","L","C","D","M"};
	public static void main(String args[]) throws IOException
	{
		Scanner in = new Scanner(new FileReader("preface.in"));
		PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter("preface.out")));
		N=in.nextInt();
		for(int i=1; i<=N; i++)
		{
			String s = binaryToRoman(i);
			for(int j=0; j<s.length(); j++)
			{
				if(s.charAt(j)=='I') count[0]++;
				else if(s.charAt(j)=='V') count[1]++;
				else if(s.charAt(j)=='X') count[2]++;
				else if(s.charAt(j)=='L') count[3]++;
				else if(s.charAt(j)=='C') count[4]++;
				else if(s.charAt(j)=='D') count[5]++;
				else if(s.charAt(j)=='M') count[6]++;
			}
		}
		for(int i=0; i<7; i++)
			if(count[i]>0)
				out.println(roman[i]+" "+count[i]);
		out.close();
		System.exit(0);
	}
	static final String[] RCODE = {"M", "CM", "D", "CD", "C", "XC", "L",
		"XL", "X", "IX", "V", "IV", "I"};
	static final int[]    BVAL  = {1000, 900, 500, 400,  100,   90,  50,
		40,   10,    9,   5,   4,    1};
	public static String binaryToRoman(int binary) {
		String roman = "";
		for (int i = 0; i < RCODE.length; i++) {
			while (binary >= BVAL[i]) {
				binary -= BVAL[i];
				roman  += RCODE[i];
			}
		}
		return roman;
	}  
}