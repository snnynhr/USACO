import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*ID: sunnyna1
LANG: JAVA
TASK: cowids
 */
public class cowids {

	static int N;
	static int K;
	public static void main(String args[]) throws IOException
	{
		//long time = System.currentTimeMillis();
		//Scanner in = new Scanner(new FileReader("cowids.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowids.out")));
		//N = in.nextInt();
		//K = in.nextInt();
		N=22222;
		K=2;
		long st=(long)Math.pow(2, K)-1;
		//System.out.println(st);
		for(int j=0; j<N-1; j++)
			st=next(st);
		System.out.println(Long.toBinaryString(st));
		//System.out.println("RUNTIME:"+(System.currentTimeMillis()-time));
		out.close();
		System.exit(0);
	}
	/*public static void main1(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		Scanner in = new Scanner(new FileReader("cowids.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowids.out")));
		N = in.nextInt();
		K = in.nextInt();
		N=1000000;
		K=10;
		
		long st=(long)Math.pow(2, K)-1;
		BigInteger aa = BigInteger.valueOf(st);
		for(int j=0; j<N-1; j++)
			aa=nextBI(aa);
		System.out.println(aa.toString(2));
		System.out.println("RUNTIME:"+(System.currentTimeMillis()-time));
		out.close();
		
		main1(null);
		System.exit(0);
	}*/
	public static long next(long x)
	{
		long s,r,o;
		s=x&(-x);
		r=x+s;
		o=x^r;
		o=(o>>2)/s;
		return r|o;
	}
	/*public static BigInteger nextBI(BigInteger x)
	{
		BigInteger s,r,o;
		s=x.and(x.negate());
		r=x.add(s);
		o=x.xor(r);
		o=o.shiftRight(2).divide(s);
		return r.or(o);
	}*/
}
