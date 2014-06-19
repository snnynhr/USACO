import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/*
ID: sunnyna1
LANG: JAVA
PROG: ratios
 */
public class ratios {
	static int[] goal = new int[3];
	static int[][] r = new int[3][3];
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("ratios.in"));
		for(int i=0; i<3; i++) 
			goal[i]=in.nextInt();
		for(int i=0; i<3; i++) 
			for(int j=0; j<3; j++) 
				r[i][j]=in.nextInt();
		in.close();
		int maxVal=Integer.MAX_VALUE;
		String s="NONE";
		for(int a=0; a<=100; a++)
			for(int b=0; b<=100; b++)
				for(int c=0; c<=100; c++)
				{
					boolean c1=true,c2=true,c3=true;
					int x = a*r[0][0]+b*r[1][0]+c*r[2][0];
					int y = a*r[0][1]+b*r[1][1]+c*r[2][1];
					int z = a*r[0][2]+b*r[1][2]+c*r[2][2];
					if((goal[0]!=0 && x%goal[0]!=0) || (goal[0]==0)&&x!=0)
						c1= false;
					if((goal[1]!=0 && y%goal[1]!=0) || (goal[1]==0)&&y!=0)
						c2= false;
					if((goal[2]!=0 && z%goal[2]!=0) || (goal[2]==0)&&z!=0 )
						c3= false;
					if(c1&&c2&&c3)
					{
						//System.out.println(x +" "+y+" "+z);
						int i=0,j=0,k=0;
						if(goal[0]!=0)
							i=x/goal[0];
						if(goal[1]!=0)
							j=y/goal[1];
						if(goal[2]!=0)
							k=z/goal[2];
						int max = Math.max(Math.max(i, j),k);
						if(x==max*goal[0] && y==max*goal[1] && z==max*goal[2] && a+b+c>0 && a+b+c<maxVal)
						{
							maxVal=a+b+c;
							s=a+" "+b+" "+c+" "+max;
						}
					}
				}
		System.out.println(s);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ratios.out")));
		out.println(s);
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
}