import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/*
ID: sunnyna1
LANG: JAVA
PROG: rect1
 */
public class rect1 {
	static int A,B,N;
	static int grid[][],area[];
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("rect1.in"));
		A = in.nextInt();
		B = in.nextInt();
		N = in.nextInt();
		grid = new int[N+1][5];
		grid[0] = new int[] {0,0,A,B,1};
		area = new int[2501];
		for(int i=1; i<=N; i++)
			for(int j=0; j<5; j++)
				grid[i][j]=in.nextInt();
		in.close();
		for(int  i=N; i>= 0;i--) 
			area[grid[i][4]] += reverseColor(grid[i][0],grid[i][1],grid[i][2],grid[i][3],i+1); 
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("rect1.out")));
		for  (int i=1; i<=2500;i++) 
			if(area[i]>0) 
				out.println(i+" "+area[i]);
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
	public static int reverseColor(int lx,int ly,int rx,int ry,int n)
	{
		//System.out.println("("+lx+","+ly+","+rx+","+ry+","+n+") s");
		int res=0;
		if  (lx == rx || ly == ry)  return  0 ; 
		if  (n > N)  return  (rx - lx) * (ry - ly); 
		if  (lx >= grid[n][2] || ly >= grid[n][3] || rx <= grid[n][0] || ry <= grid[n][1])    
			return  reverseColor(lx, ly, rx, ry, n + 1 ); 
		int[] x = {lx,Math.max (lx,grid[n][0]),Math.min(rx,grid[n][2]),rx}; 
		int[] y = {ly,Math.max (ly,grid[n][1]),Math.min(ry,grid[n][3]),ry}; 
		for(int i=0;i<3; i++)  
			for(int j=0; j<3; j++) 
				if  (i!= 1 || j != 1 )  
					res+= reverseColor(x[i], y[j], x[i+1], y[j+1],n+1); 
		//System.out.println("("+lx+","+ly+","+rx+","+ry+","+n+")+"+res);
		return res; 
	}
}