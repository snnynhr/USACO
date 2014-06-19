import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


/*ID: sunnyna1
LANG: JAVA
TASK: milk
*/
public class milk {
	static int[] price;
    static int[] amounts;
	public static void main(String args[]) throws IOException
	{
	    BufferedReader f = new BufferedReader(new FileReader("milk.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
	    StringTokenizer z = new StringTokenizer(f.readLine());
	    int amount=Integer.parseInt(z.nextToken());
	    int num=Integer.parseInt(z.nextToken());
	    price = new int[num];
	    amounts = new int[num];
	    for(int i=0; i<num; i++)
	    {	
	    	z=new StringTokenizer(f.readLine());
	    	price[i]=Integer.parseInt(z.nextToken());
	    	amounts[i]=Integer.parseInt(z.nextToken());
	    }
	    selectionSort(price,amounts);
	    int c=0;
	    int curr=0;
	    while(amount>0)
	    {
	    	if(amounts[c]>=amount) 
	    	{
	    		curr+=amount*price[c];
	    		amount-=amount;
	    	}
	    	else
	    	{
	    		curr+=amounts[c]*price[c];
	    		amount-=amounts[c];
	    	}
	    	c++;
	    }
	    out.println(curr);
	    out.close();
	    
	}
	public static void selectionSort(int[] arr, int[] link) {
	      int i, j, minIndex, tmp, tmp1;
	      int n = arr.length;
	      for (i = 0; i < n - 1; i++) {
	            minIndex = i;
	            for (j = i + 1; j < n; j++)
	                  if (arr[j] < arr[minIndex])
	                        minIndex = j;
	            if (minIndex != i) {
	                  tmp = arr[i];
	                  arr[i] = arr[minIndex];
	                  arr[minIndex] = tmp;
	                  tmp1 = link[i];
	                  link[i] = link[minIndex];
	                  link[minIndex] = tmp1;
	            }
	      }
	}
}
