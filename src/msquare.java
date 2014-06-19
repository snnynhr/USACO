import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
ID: sunnyna1
LANG: JAVA
PROG: msquare
 */
public class msquare {
	static int[] m = new int[8];
	static int[][] combo = new int[40320][4];
	static int[] dist = new int[40320];
	static boolean[] visited = new boolean [40320];
	static int[] prev = new int[40320];
	static int[] prevVal = new int[40320];
	static int num=0;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("msquare.in"));
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i=0; i<8; i++)
			m[i]=Integer.parseInt(st.nextToken());
		in.close();
		boolean[] d = new boolean[8];
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		dfs(0,0,d);
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		for(int i=1; i<=3; i++)
			for(int j=0; j<40320; j++)
				combo[j][i]=applyMove(i,combo[j][0]);
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		for(int j=0; j<40320; j++)
			dist[j]=Integer.MAX_VALUE;
		//bfs
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(12345678);
		dist[0]=0;
		while(!q.isEmpty())
		{
			int c = q.poll();
			int index = fH(c);
			if(!visited[index])
			{
				visited[index]=true;
				for(int i=1; i<=3; i++)
				{
					int adjIndex = fH(combo[index][i]);
					if(!visited[adjIndex])
						q.add(combo[index][i]);
					if(dist[index]+1<dist[adjIndex])
					{
						dist[adjIndex]=dist[index]+1;
						prev[adjIndex]=i;
						prevVal[adjIndex]=c;
					}
				}
			}
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));
		int res=0;
		for(int i=0; i<8; i++)
			res=10*res+m[i];
		out.println(dist[fH(res)]);
		String moves="";
		int currInd = fH(res);
		while(currInd!=0)
		{
			char a = (char) (prev[currInd]+64);
			moves= a+moves;
			currInd = fH(prevVal[currInd]);
		}
		int i;
		for(i=0; i<(moves.length()-1)/60; i++)
			out.println(moves.substring(60*i, 60*(i+1)));
		out.println(moves.substring(60*i));
		System.out.println(moves);
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
	public static int applyMove(int k, int num)
	{
		int[] n = new int[8];
		for(int i=0; i<8; i++)
		{
			n[7-i]=num%10;
			num/=10;
		}
		if(k==1)
		{
			for(int i=0; i<4; i++)
			{
				int temp=n[i];
				n[i]=n[7-i];
				n[7-i]=temp;
			}
			//int temp=n[i];
		}
		else if(k==2)
		{
			int tenp = n[3];
			for(int i=2; i>=0; i--)
				n[i+1]=n[i];
			n[0]=tenp;
			tenp = n[4];
			for(int i=5; i<=7; i++)
				n[i-1]=n[i];
			n[7]=tenp;
		}
		else if(k==3)
		{
			int tenp = n[1];
			n[1]=n[6];
			n[6]=n[5];
			n[5]=n[2];
			n[2]=tenp;
		}
		int res=0;
		for(int i=0; i<8; i++)
			res=10*res+n[i];
		return res;
	}
	public static void dfs(int lvl,int n, boolean[] done)
	{
		if(lvl==8) combo[fH(n)][0]=n;
		else
		{
			for(int i=1; i<=8; i++)
			{
				if(!done[i-1])
				{
					boolean[] d = done.clone();
					d[i-1]=true;
					dfs(lvl+1,10*n+i,d);
				}
			}
		}
	}
	public static int fH(int num){
		
		int[] s = new int[8];
		for(int i=0; i<8; i++)
		{
			s[7-i]=num%10;
			num/=10;
		}
		int h[]={0,1,2,6,24,120,720,5040};
		int res=0;
		for(int i=0;i<=7;++i)
			for(int j=0;j<i;++j)
				if(s[i]<s[j]) res+=h[i];
		return res;
	}
}