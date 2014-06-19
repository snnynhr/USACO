import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/*ID: sunnyna1
LANG: JAVA
TASK: baleshare
 */
public class baleshare {
	static int N;
	static int[] bales;
	static int[][] buckets;
	static int[] counter= new int[3];

	static int MAXS;
	static boolean[][][] dp;
	static int n,bale,tsum;
	public static void main(String args[]) throws IOException
	{
		//test.main(null);
		long time = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("baleshare.in"));
		PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter("baleshare.out")));

		MAXS=700;
		n=in.nextInt();
		dp = new boolean[n+1][MAXS+100][MAXS+100];

		dp[0][0][0]=true;
		tsum=0;
		
		for(int i=0; i<n; i++)
		{
			bale = in.nextInt();
			tsum+=bale;
			for(int j=0; j<MAXS; j++)
				for(int k=0; k<MAXS; k++)
				{
					if(dp[i][j][k])
					{
						dp[(i+1)][j][k]=true;
						dp[(i+1)][j+bale][k]=true;
						dp[(i+1)][j][k+bale]=true;
					}
				}
		}
		in.close();
		int ans=MAXS;
		for(int i=0; i<MAXS; i++)
			for(int j=0; j<MAXS; j++)
				if(dp[n][i][j])
					ans=Math.min(ans, Math.max(i,Math.max(j,tsum-i-j)));
		System.out.println(ans);
		System.out.println("RUNTIME: "+(System.currentTimeMillis()-time));
		time = System.currentTimeMillis();
		mmain(null);
		System.out.println("RUNTIME: "+(System.currentTimeMillis()-time));
		out.close();
		System.exit(0);
	}
	public static void mmain(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("baleshare.in"));
		PrintWriter out = new PrintWriter( new BufferedWriter( new FileWriter("baleshare.out")));
		N=in.nextInt();
		bales = new int[N];
		buckets = new int[3][N+1];
		for(int i=0; i<N; i++)
			bales[i]=in.nextInt();
		selectionsort(bales, bales.length);
		//System.out.println(Arrays.toString(bales));
		for(int i=0; i<N; i++)
		{
			int a=getMinIndex();
			//buckets[a]+=bales[i];
			buckets[a][counter[a]]=bales[i];
			buckets[a][N]+=bales[i];
			counter[a]++;
			//for(int j=0; j<3; j++)
				//System.out.println(i+": "+Arrays.toString(buckets[j]));
		}
		int[] max = new int[10];
		max[0]=getMax();
		equalize(1,2);
		max[1]=getMax();
		equalize(2,0);
		max[2]=getMax();
		equalize(1,0);
		max[3]=getMax();

		equalize(1,2);
		max[4]=getMax();
		equalize(1,0);
		max[5]=getMax();
		equalize(2,0);
		max[6]=getMax();

		equalize(1,0);
		max[7]=getMax();
		equalize(1,2);
		max[8]=getMax();
		equalize(2,0);
		max[9]=getMax();


		Arrays.sort(max);
		//for(int j=0; j<3; j++)
		//System.out.println(j+": "+Arrays.toString(buckets[j]));
		in.close();
		System.out.println(max[0]);
		System.out.println("RUNTIME: "+(System.currentTimeMillis()-time));
		out.close();
		System.exit(0);
	}
	public static void equalize(int a, int b)
	{
		int max,min,mindiff=1000;
		int max_ind = 0,min_ind=0;
		int diff = Math.abs(buckets[a][N]-buckets[b][N])/2;
		if(diff<1) return;
		if(buckets[a][N]>buckets[b][N])
		{
			max=a;
			min=b;
		}
		else
		{
			min=a;
			max=b;
		}
		for(int i=0; i<counter[max]; i++)
		{
			for(int j=0; j<counter[min]; j++)
			{
				if(buckets[max][i]>buckets[min][j] && buckets[max][i]-buckets[min][j]<mindiff)
				{
					mindiff=buckets[max][i]-buckets[min][j];
					max_ind=i;
					min_ind=j;
				}
			}
		}
		if(mindiff!=1000)
		{
			int t=buckets[max][max_ind];
			buckets[max][max_ind]=buckets[min][min_ind];
			buckets[min][min_ind]=t;
			buckets[max][N]-=mindiff;
			buckets[min][N]+=mindiff;
		}
	}
	public static int getMinIndex()
	{
		int min=1000;
		min=Math.min(buckets[0][N], buckets[1][N]);
		min=Math.min(min, Math.min(buckets[1][N], buckets[2][N]));
		min=Math.min(min, Math.min(buckets[2][N], buckets[0][N]));
		for(int i=0; i<3; i++)
			if(min==buckets[i][N]) return i;
		return -1;
	}
	public static void selectionsort(int array[], int n){
		for(int x=0; x<n; x++){
			int index_of_min = x;
			for(int y=x; y<n; y++){
				if(array[index_of_min]<array[y]){
					index_of_min = y;
				}
			}
			int temp = array[x];
			array[x] = array[index_of_min];
			array[index_of_min] = temp;
		}
	}
	public static int getMax()
	{
		//for(int j=0; j<3; j++)
			//System.out.println(j+": "+Arrays.toString(buckets[j]));
		return Math.max(Math.max(buckets[0][N],buckets[1][N]),Math.max(buckets[0][N],buckets[2][N]));
	}
}
