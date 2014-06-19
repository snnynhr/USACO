import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*ID: sunnyna1
LANG: JAVA
TASK: calfflac
 */
public class calfflac {
	static int len;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("calfflac.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("calfflac.out")));
		StringBuilder sb = new StringBuilder();
		String temp = null;
		while ((temp = f.readLine()) != null) {
			sb.append(temp);
			sb.append("\n");
		}
		String main =sb.toString();
		String lower=main.toLowerCase();
		char[] m=main.toCharArray();
		char[] l=lower.toCharArray();
		char[] p=new char[20000];
		int[] index = new int[20000];
		int z=0;
		for(int i=0; i<lower.length(); i++)
		{
			char tmp=lower.charAt(i);
			if(isChar(tmp)) 
			{
				p[z]=tmp;
				index[z]=i;
				z++;
				len++;
			}
		}
		int max=0;
		int maxo=0,indexo=0;
		int maxe=0,indexe=0;
		int beginindex=0;
		for(int i=0; i<len; i++)
		{
			int tmpo=C(p,i-1,i+1)+1;
			if(tmpo>maxo)
			{
				maxo=tmpo;
				indexo=i;
			}
			int tmpe=C(p,i,i+1);
			if(tmpe>maxe)
			{
				maxe=tmpe;
				indexe=i;
			}
			if(maxo>maxe)
			{
				beginindex=indexo-(maxo-1)/2;
				max=maxo;
			}
			else
			{
				beginindex=indexe-maxe/2+1;
				max=maxe;
			}
		}
		int d=0;
		String g = "";
		int a =index[beginindex];
		while(d<max)
		{
			if(p[beginindex+d]==l[a])
			{ 
				d++;
			}
			g+=m[a];
			a++;
		}
		System.out.println(max);
		out.println(g);
		out.close();
		System.out.println("RUNTIME: " +(System.currentTimeMillis()-time));
		System.exit(0);
	}
	public static int C(char[] arr, int left, int right)
	{
		int count=0;
		while(isBounded(left, arr) && isBounded(right, arr) && arr[left]==arr[right])
		{
			left--;
			right++;
			count+=2;
		}
		return count;
	}
	public static boolean isBounded(int i, char[] arr)
	{
		return i>=0 && i<len;
	}
	public static boolean isChar(char m)
	{
		return ('a'<=m && m<='z')||('A'<=m && m<='Z');
	}
	/*public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader f = new BufferedReader(new FileReader("calfflac.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("calfflac.out")));
		String x="",y="";
		StringBuilder sb = new StringBuilder();
        String temp = null;
        while ((temp = f.readLine()) != null) {
                sb.append(temp);
                sb.append("\n");
        }

        x = sb.toString();

		x=x.substring(0,x.length()-2);
		ArrayList<Integer> z= new ArrayList<Integer>();
		long time2 = System.currentTimeMillis();
		for( int i=0; i<x.length();i++)
		{
			char m=x.charAt(i);
			if(('a'<=m && m<='z')||('A'<=m && m<='Z'))
			{
				y+=m;
				z.add(i);
			}
		}
		System.out.println("Input Time: "+(System.currentTimeMillis()-time2));
		long time1=System.currentTimeMillis();
		int max=0;
		int index=0;
		int eOo=0;
		for(int i=0; i<y.length(); i++)
		{
			int tmpe= lengthe(y,i);
			if( tmpe> max)
			{
				max=tmpe; 
				eOo=0; 
				index =i;
			}
			int tmpo = lengtho(y,i);

			if(tmpo>max) 
			{
				max=tmpo; 
				eOo=1;
				index =i;
			}
		}
		System.out.println("Algorithm time: "+(System.currentTimeMillis()-time1));
		int beginindex=0;
		if(eOo==0) beginindex=index-max/2+1;
		if(eOo==1) beginindex=index-(max-1)/2;
		int d=0;
		String g = "";
		int a =z.get(beginindex);
		while(d<max)
		{
			String q = x.substring(a,a+1);
			if(y.substring(beginindex+d,beginindex+d+1).equals(x.substring(a,a+1)))
			{ 
				d++;
			}
			g+=q;
			a++;
		}
		out.println(max);
		out.println(g);
		out.close();
		System.out.println("total time taken: " +(System.currentTimeMillis()-time));
		System.exit(0);
	}

	public static int lengthe(String p, int index)
	{
		int count=0;
		int j=index, k=index+1;
		int l=p.length();
		while(k<l && j>=0 && p.substring(j,j+1).equalsIgnoreCase(p.substring(k,k+1)))
		{
			count+=2;
			j--;
			k++;
		}
		return count;
	}
	public static int lengtho(String p, int index)
	{
		int count1=1;
		int j=index-1;
		int k=index+1;
		int l=p.length();
		while(k<l && j>=0 && p.substring(j,j+1).equalsIgnoreCase(p.substring(k,k+1)))
		{
			count1+=2;
			j--;
			k++;
		}
		return count1;
	}
	/*public void fastLongestPalindromes(int[] seq):
	{
		int seqLen = seq.length;
		int[] l = new int[seqLen];
		int i = 0;
		int palLen = 0;
		while (i < seqLen)
			if (i > palLen and seq[i - palLen - 1] == seq[i])
			{
				palLen += 2
				i += 1
				continue		    		 
				s = len(l) - 2
				e = s - palLen
				for j in xrange(s, e, -1):	
					d = j - e - 1	
					if l[j] == d: # *
					palLen = d
					break
					l.append(min(d, l[j]))
					else:
						palLen = 1
						i += 1
						l.append(palLen)
						lLen = len(l)
						s = lLen - 2
						e = s - (2 * seqLen + 1 - lLen)
						for i in xrange(s, e, -1):	 
							d = i - e - 1
							l.append(min(d, l[i]))
							return l
	}*/
}
