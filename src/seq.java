import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;


public class seq {
	static int c=0;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("test.in"));
		int[] A = new int[1000000];
		Random a = new Random();
		for(int i=0; i<A.length; i++)
			A[i] = a.nextInt(1000);
		System.out.println(findMax(A, 0,A.length-1));
		System.out.println("iter"+c);
		in.close();
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.out")));
		out.close();
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
	public static int findMax(int[] A, int low, int high)
	{
		int min=Integer.MAX_VALUE;
		int sum=0;
		for(int i=low; i<=high; i++) 
		{
			sum+=A[i];
			min=Math.min(min, A[i]);
		}
		if (high == low) return A[low]*A[low];
		else 
		{
			int mid = (low+high)/2;
			int ls = findMax(A, low, mid);
			int rs = findMax(A, mid+1, high);
			int cs = findMaxC(A,low,mid,high);
			int ts = sum*min;
			return Math.max(ts,Math.max(cs,Math.max(rs,ls)));
		}
	}
	public static int findMaxC(int A[], int low, int mid, int high)
	{
		int i = mid-1;
		int j = mid+1;
		int sum=A[mid];
		int maxsum  = A[mid]*A[mid];
		int min = A[mid];
		while(i!=low && j!=high)
		{
			c++;
			if(i>=low && A[i]>A[j])
			{
				min = Math.min(min,A[i]);
				sum+=A[i];
				maxsum = Math.max(maxsum, min*sum);
				i--;
			}
			else if (j<=high)
			{
				min = Math.min(min,A[j]);
				sum+=A[j];
				maxsum = Math.max(maxsum, min*sum);
				j++;
			}
		}
		return maxsum;
	}
}
