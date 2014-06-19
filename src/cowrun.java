import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
/*
ID: sunnyna1
LANG: JAVA
PROG: cowrun
 */
public class cowrun {
	static int N,L,R;
	static ArrayList<Integer> l,r;

	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("cowrun.in"));
		N = Integer.parseInt(in.readLine());
		int[] d = new int[N+1];
		d[0]=0;
		l = new ArrayList<Integer>();
		r = new ArrayList<Integer>();
		for(int i=0; i<N; i++)
		{
			int x = Integer.parseInt(in.readLine());
			if(x<0) l.add(x);
			else r.add(x);
			d[i+1]=x;
		}
		Collections.sort(l);
		Collections.sort(r);
		in.close();
		int[][] dist = new int[N+1][N+1];
		for(int i=0; i<N+1; i++)
			for(int j=0; j<N+1; j++)
			{
				dist[i][j] = Math.abs(d[i]-d[j]);
			}
		int L  = l.size(); 	int R = r.size();
		int lll = L-1; 	int rrr = 0;
		//int c = 0;
		System.out.println(L);
		int cp = 0;
		int runsum = 0;
		int total = 0;
		int np = 0;
		
		for(int c=0; c<N; c++)
		{
			if(lll<0) 
			{
				runsum += Math.abs(r.get(rrr)-cp);
				total += runsum;
				cp = r.get(rrr);
				rrr++;
			}
			else if(rrr>=R) 
			{
				runsum += Math.abs(l.get(lll)-cp);
				total += runsum;
				cp = l.get(lll);
				lll--;
			}
			else
			{
				//System.out.println("r"+runsum);
				int cl = l.get(lll);
				int cr = r.get(rrr);
				if(Math.abs(cl-cp)<Math.abs(cr-cp)) 
				{
					runsum += Math.abs(cl-cp);
					total += runsum;
					cp = cl;
					lll--;
				}
				else if(Math.abs(cl-cp)>Math.abs(cr-cp))
				{
					runsum += Math.abs(cr-cp);
					total += runsum;
					cp = cr;
					rrr++;
				}
				/*else if(Math.abs(cl-cp)==Math.abs(cr-cp))
				{
					cl = l.get(lll);
					cr = r.get(rrr);
					while(Math.abs(cl-cp)==Math.abs(cr-cp))
					{
						
						
						lll--;
						rrr++;
						if(lll==-1){
							np=0;
							break;
						}
						else if(rrr == r.size())
						{
							np = r.size()-1;
							break;
						}
					}
					
				}*/
			}
		}
		System.out.println(total);
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowrun.out")));
		out.println(total);
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
}