import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class FrequencyAnalyzer {
	static double[] maxdB;
	static double[] maxfrq;
	static double[] nmaxdB;
	static double[] nmaxfrq;
	static double[] frqdist;
	static double[] rmsfrqdist;
	static int N;
	static String dir;
	public static void mainn(String args[])
	{
		int n=15;
		int numdigits=7;
		int lower = 6;
		int upper = 71;
		for(int i=0; i<n; i++)
		{
		String s=lower + (int)(Math.random()*(upper-lower))+Math.random()+"";
		System.out.println(s.substring(0, numdigits+4));
		}
	}
	public static void main(String args[]) throws IOException
	{
		N=14;
		dir ="ControlTrials\\scontrol";
		maxdB = new double[N];
		maxfrq = new double[N];
		nmaxdB = new double[N];
		nmaxfrq = new double[N];
		frqdist = new double[N];
		rmsfrqdist = new double[N];
		for(int i=1; i<=N; i++)
		analyze(i);
		System.out.println("Results\n--------------------------------------------------------");
		System.out.println("db Mean:"+mean(nmaxdB)+"\tstDv:"+stdDev(nmaxdB)+"\n--------------------------------------------------------");
		System.out.println("natfrq Mean:"+mean(nmaxfrq)+"\tstDv:"+stdDev(nmaxfrq)+"\n--------------------------------------------------------");
		System.out.println("maxdB  Mean:"+mean(maxdB)+"\tstDv:"+stdDev(maxdB)+"\n--------------------------------------------------------");
		System.out.println("maxfrq  Mean:"+mean(maxfrq)+"\tstDv:"+stdDev(maxfrq)+"\n--------------------------------------------------------");
		System.out.println("frqdist Mean:"+mean(frqdist)+"\tstDv:"+stdDev(frqdist)+"\n--------------------------------------------------------");
		System.out.println("rmsfrqdist Mean:"+mean(rmsfrqdist)+"\tstDv:"+stdDev(rmsfrqdist)+"\n--------------------------------------------------------");
	}
	public static void analyze(int n) throws IOException
	{
		Scanner in = new Scanner(new FileReader("C:\\Users\\Ankur\\Desktop\\PJAS2012\\"+dir+n));
		ArrayList<Double> frq = new ArrayList<Double>();
		ArrayList<Double> db = new ArrayList<Double>();
		ArrayList<Double> filter = new ArrayList<Double>();
		ArrayList<Double> filterdB = new ArrayList<Double>();
		System.out.println("Trial: "+n);
		in.next();
		in.next();
		in.next();
		in.next();
		while(in.hasNext())
		{
			double d = in.nextDouble();
			double e = in.nextDouble();
			frq.add(d);
			if(d>=70 && d<=79) {
				filter.add(d);
				filterdB.add(e);
			}
			db.add(e);
		}
		System.out.print(nmax(filter, filterdB,n)+"  \t");
		System.out.print(max(frq, db,n));
		System.out.print("\tAverage frq :"+integralSoundDist(frq, db,n));
		System.out.println("\tRms frq :"+rmsIntegralSoundDist(frq, db,n)+"\n----------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	public static String max(ArrayList<Double> frq, ArrayList<Double> db,int n)
	{
		double mdB = -1000;
		double corrfrq=-1000;
		for(int i=0; i<db.size(); i++)
		{
			if(db.get(i)>mdB)
			{
				mdB=db.get(i);
				corrfrq=frq.get(i);
			}
		}
		maxdB[n-1]=mdB;
		maxfrq[n-1]=corrfrq;
		return "Maximum dB: "+ mdB+"   Frequency: "+corrfrq;
	}
	public static String nmax(ArrayList<Double> frq, ArrayList<Double> db,int n)
	{
		double mdB = -1000;
		double corrfrq=-1000;
		for(int i=0; i<db.size(); i++)
		{
			if(db.get(i)>mdB)
			{
				mdB=db.get(i);
				corrfrq=frq.get(i);
			}
		}
		nmaxdB[n-1]=mdB;
		nmaxfrq[n-1]=corrfrq;
		return "dB: "+ mdB+"   natFrequency: "+corrfrq;
	}
	public static double integralSoundDist(ArrayList<Double> frq, ArrayList<Double> db, int n)
	{
		double nsum=0.0;
		double sum=0.0;
		double weight;
		for(int i=0; i<db.size(); i++)
		{
			weight=Math.pow(10, db.get(i));
			sum+=weight;
			nsum+=(weight*frq.get(i));
		}
		frqdist[n-1]=nsum/sum;
		return nsum/sum;
	}
	public static double rmsIntegralSoundDist(ArrayList<Double> frq, ArrayList<Double> db, int n)
	{
		double nsum=0.0;
		double sum=0.0;
		double weight;
		for(int i=0; i<db.size(); i++)
		{
			weight=Math.pow(10, db.get(i));
			sum+=weight;
			//System.out.println(sum);
			nsum+=(weight*frq.get(i)*frq.get(i));
		}
		rmsfrqdist[n-1]=Math.sqrt(nsum/sum);
		return Math.sqrt(nsum/sum);
	}
	public static double mean(double[] n)
	{
		double sum=0;
		for(int i=0; i<n.length; i++)
			sum+=n[i]/n.length;
		return sum;
	}
	public static double stdDev(double[] n)
	{
		double mean = mean(n);
		double sum=0;
		for(int i=0; i<n.length; i++)
			sum+=(n[i]-mean)*(n[i]-mean);
		return Math.sqrt(sum/(n.length-1));
	}
}
