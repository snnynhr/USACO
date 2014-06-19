import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
ID: sunnyna1
LANG: JAVA
PROG: comehome
 */
public class comehome {
	static int[][] adj = new int[52][52];
	static boolean[] used = new boolean[25];
	static boolean[] visited;
	static int[] dist = new int[52];
	public static void main(String args[]) throws IOException
	{
		Scanner in = new Scanner(new FileReader("comehome.in"));
		int N = in.nextInt();
		for(int i=0; i<52; i++)
		{
			for(int j=0; j<52; j++)
			{
				if(i==j) adj[i][j]=0;
				else adj[i][j] = Integer.MAX_VALUE;
			}
		}
		for(int i=0; i<N; i++)
		{
			int s = getIndex(in.next());
			int t = getIndex(in.next());
			int res = in.nextInt();
			adj[s][t]=Math.min(adj[s][t],res);
			adj[t][s]=Math.min(adj[t][s],res);
			if(t<25) used[t]=true;
			if(s<25) used[s]=true;
		}
		
		in.close();
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
		int minIndex=0;
		int minDist=Integer.MAX_VALUE;
		for(int i=0; i<25; i++)
		{
			if(used[i])
			{
				for(int j=0; j<dist.length; j++)
					dist[j]=Integer.MAX_VALUE;
				dist[i]=0;
				visited = new boolean[52];
				Queue<Integer> z = new LinkedList<Integer>();
				z.add(i);
				while(!z.isEmpty())
				{
					int curr = z.poll();
					visited[curr]=true;
					for(int j=0; j<52; j++)
					{
						if(adj[curr][j]!=Integer.MAX_VALUE)
						{
							if(!visited[j])
								z.add(j);
							dist[j] = Math.min(dist[j], dist[curr]+adj[curr][j]);
						}
					}
				}
				if(dist[25]<minDist)
				{
					minIndex=i;
					minDist=dist[25];
				}
			}
		}
		out.println(getChar(minIndex)+" "+minDist);
		out.close();
		System.exit(0);
	}
	public static int getIndex(String s)
	{
		if(s.charAt(0)>64 && s.charAt(0)<91) return s.charAt(0)-65;
		else return s.charAt(0)-71;
	}
	public static char getChar(int index)
	{
		if(index<26) return (char) (index+65);
		else return (char)(71+index);
	}
}