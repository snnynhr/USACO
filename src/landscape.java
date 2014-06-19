import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*ID: sunnyna1
LANG: JAVA
TASK: landscape
 */
public class landscape {
	static int N,X,Y,Z, suma,sumb,cost=0,diff=0;
	static int[][] dp = new int[100][11];
	static int[][] cons;
	public static void main(String args[]) throws IOException
	{
		Scanner in = new Scanner(new FileReader("landscape.in"));
		PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter("landscape.out")));
		N = in.nextInt();
		X = in.nextInt();
		Y = in.nextInt();
		Z = in.nextInt();
		cons = new int[N][2];
		for(int i=0; i<N; i++)
		{
			cons[i][0]=in.nextInt();
			suma +=cons[i][0];
			cons[i][1]=in.nextInt();
			sumb +=cons[i][1];
		}
		if(suma>sumb) 
		{
			cost+=(suma-sumb)*Y; 
			diff=suma-sumb; 
		}
		if(suma<sumb) 
		{ 
			cost+=(sumb-suma)*X; 
			diff=sumb-suma; 
		}
		if(X+Y>=Z) cost += transport(cons);
		
		for(int i=0; i<N; i++)
		{
			while(diff>0 && cons[i][0]>0)
			{
				diff--;
				cons[i][0]--;
			}
		}
		
		out.println(cost);
		out.close();
		System.exit(0);
	}
	
	public static int transport(int[][] cons)
	{
		int cost=0;
		int cost1=0;
		for(int i=0; i<N; i++)
			System.out.println(cons[i][0]+ " "+cons[i][1]);
		for(int i=0; i<N; i++)
			cost+=(cons[i][0]-cons[i][1])*i;
		for(int i=0; i<N; i++)
			cost1+=(cons[i][0]-cons[i][1])*(N-i-1);
		return Math.min(Math.abs(cost),Math.abs(cost1));
	}
}