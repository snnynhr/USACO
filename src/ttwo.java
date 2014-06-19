import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/*
ID: sunnyna1
LANG: JAVA
PROG: ttwo
 */
public class ttwo {
	static char[][] grid = new char[10][10];
	static int farmX, farmY, cowX,cowY,cowDir=0,farmDir=0;
	public static void main(String args[]) throws IOException
	{

		Scanner in = new Scanner(new FileReader("ttwo.in"));
		for(int i=0; i<10; i++)
		{
			String s = in.next();
			for(int j=0; j<10; j++)
			{
				grid[i][j] = s.charAt(j);
				if(grid[i][j]=='F') 
				{
					farmX=i;
					farmY=j;
					grid[i][j]='.';
				}
				else if(grid[i][j]=='C')
				{
					cowX=i;
					cowY=j;
					grid[i][j]='.';
				}
			}
		}
		in.close();
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));
		int time=0;
		while(!(cowX==farmX && cowY==farmY) && time<160000)
		{
			time++;
			makeMove();
		}
		if(time<160000)
			out.println(time);
		else out.println(0);
		out.close();
		System.exit(0);
	}
	public static void makeMove()
	{
		grid[farmX][farmY]='.';
		grid[cowX][cowY]='.';
		if(farmDir==3)
		{
			if(farmY-1<0 || grid[farmX][farmY-1]=='*') farmDir=(farmDir+1)%4;
			else
				farmY--;
		}
		else if(farmDir==2)
		{
			if(farmX+1>9 || grid[farmX+1][farmY]=='*') farmDir=(farmDir+1)%4;
			else
				farmX++;
		}
		else if(farmDir==1)
		{
			if(farmY+1>9 || grid[farmX][farmY+1]=='*') farmDir=(farmDir+1)%4;
			else
				farmY++;
		}
		else if(farmDir==0)
		{
			if(farmX-1<0 || grid[farmX-1][farmY]=='*') farmDir=(farmDir+1)%4;
			else
				farmX--;
		}
		if(cowDir==3)
		{
			if(cowY-1<0 || grid[cowX][cowY-1]=='*') cowDir=(cowDir+1)%4;
			else
				cowY--;
		}
		else if(cowDir==2)
		{
			if(cowX+1>9 || grid[cowX+1][cowY]=='*') cowDir=(cowDir+1)%4;
			else
				cowX++;
		}
		else if(cowDir==1)
		{
			if(cowY+1>9 || grid[cowX][cowY+1]=='*') cowDir=(cowDir+1)%4;
			else
				cowY++;
		}
		else if(cowDir==0)
		{
			if(cowX-1<0 || grid[cowX-1][cowY]=='*') cowDir=(cowDir+1)%4;
			else
				cowX--;
		}
		grid[farmX][farmY]='F';
		grid[cowX][cowY]='C';
	}
}