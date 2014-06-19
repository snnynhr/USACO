import java.io.IOException;
import java.math.BigDecimal;
/*
ID: sunnyna1
LANG: JAVA
PROG: eight
 */
public class eight {
	static BigDecimal[][] mem = new BigDecimal[101][101];
	static BigDecimal xx = new BigDecimal(-999);
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		//Scanner in = new Scanner(new FileReader("eight.in"));
		//in.close();
		int c=0;
		for(int i=20; i<=100; i++)
			c+=f(i);
		System.out.println(c);
		System.out.println("Runtime: "+(System.currentTimeMillis()-time));
		System.exit(0);
	}
	public static int f(int n)
	{
		int max=0;
		for(int i=1; i<=n/2; i++)
			max=Math.max(max,n%i);
		return max;
	}
	public static int totient(int n)
	{
		int c=0;
		for(int i=1; i<=n; i++)
			if(gcd(n,i)==1) c++;
		return c;
	}
	public static int div(int n)
	{
		int c=0;
		for(int i=1; i<=n; i++)
		{
			if(n%i==0)c++;
		}
		return c;
	}
	public static int gcd(int a, int b)
	{
		if (b==0) return a;
		return gcd(b,a%b);
	}
	public static BigDecimal f(int x, int y)
	{
		if(mem[x][y]!=xx) return mem[x][y];
		else
		{
			if(x==1) 
			{
				BigDecimal d = new BigDecimal(1);
				d.divide(new BigDecimal(y));
				return mem[x][y]=d;
			}
			else if(y==1) 
			{
				BigDecimal d = new BigDecimal(1);
				d.divide(new BigDecimal(x));
				return mem[x][y]=d;
			}
			else
				return mem[x][y]=f(x-1,y).multiply(f(x,y-1)).add(new BigDecimal(1)).divide(f(x-1,y-1));
		}
	}
	public static boolean nextPermutation(int[] a)
	{
		int N=a.length;
		int i=N-2;
		for (; i>=0; i--)
			if (a[i]<a[i+1])
				break;
		if (i<0) return false;

		for (int j=N-1; j>=i; j--)
		{
			if (a[j]>a[i])
			{
				int temp=a[i];
				a[i]=a[j];
				a[j]=temp;
				break;		
			}
		}
		for (int j=i+1; j<(N+i+1)/2; j++)		//reverse from a[i+1] to a[N-1]
		{
			int temp=a[j];
			a[j]=a[N+i-j];
			a[N+i-j]=temp;
		}
		return true;
	}
}
