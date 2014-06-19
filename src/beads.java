import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*ID: sunnyna1
LANG: JAVA
TASK: beads
 */
public class beads {
	public static int count(String s, int pos,int l)
	{
		int countleft=0, countright =0;
		int indexl=pos;
		int indexr=pos+1;
		
		if(pos+1>=l) indexr=0;
		int indexl1=indexl;
		int indexr1=indexr;

		char left=s.charAt(indexl1); 
		while(left=='w')
		{
			left=s.charAt(indexl1);
			indexl1--;
			if(indexl1<0) indexl1=l-1;
		}	
		char right=s.charAt(indexr1);
		while(right=='w')
		{ 
			right=s.charAt(indexr1);
			indexr1++;
			if(indexr1>=l) indexr1=0;
		}
		char universal = 'w';
		while((s.charAt(indexr)==right || s.charAt(indexr)==universal) && indexr!=indexl) 
		{ 
			
			countright++;
			indexr++;
			if(indexr>=l) indexr=0;
		}
		indexr-=1;
		while((s.charAt(indexl)==left || s.charAt(indexl)==universal) && indexr!=indexl)
		{
			
			countleft++;
			indexl--;
			if(indexl<0) indexl=l-1;
		}
		return countleft+countright;
	}
	public static boolean checkInfinite(String s, int  num)
	{
		char z = s.charAt(0);
		for(int i= 1; i<num; i++)
		{
			if(z!=s.charAt(i)) return false;
		}
		return true;
	}
	public static void main(String args[]) throws IOException
	{
		BufferedReader f = new BufferedReader(new FileReader("beads.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
		int num = Integer.parseInt(f.readLine());
		String beads = f.readLine();
		if(checkInfinite(beads, num))
		{
			out.println(num);
			out.close(); 
			System.exit(0);
		}
		else
		{
			int max = count(beads,7,num); 
			for(int i=1; i<num; i++)
			{
				int temp = count(beads,i,num);
				if(temp>max) max=temp;
			}
			out.println(max);
			out.close(); 
			System.exit(0);
		}
	}
}
