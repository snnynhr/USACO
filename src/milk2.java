
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;


/*ID: sunnyna1
LANG: JAVA
TASK: milk2
*/
public class milk2 {
	public static void linkedsort(int[] x,int[] link) {
	    for (int i=0; i<x.length-1; i++) {
	        int minIndex = i;      // Index of smallest remaining value.
	        for (int j=i+1; j<x.length; j++) {
	            if (x[minIndex] > x[j]) {
	                minIndex = j;  // Remember index of new minimum
	            }
	        }
	        if (minIndex != i) { 
	            //...  Exchange current element with smallest remaining.
	            int temp = x[i];
	            x[i] = x[minIndex];
	            x[minIndex] = temp;
	            int tem  = link[i];
	            link[i]=link[minIndex];
	            link[minIndex]=tem;
	        }
	    }
	}
	public static void main(String args[]) throws IOException
	{
	    BufferedReader f = new BufferedReader(new FileReader("milk2.in"));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
	    int n = Integer.parseInt(f.readLine());
	    int[] timeIn = new int[n];
	    int[] timeOut= new int[n];
	    int maxMilkTime, maxBreakTime;
	    for(int j=0; j<n; j++)
	    {
	    	StringTokenizer st = new StringTokenizer(f.readLine());
	    	timeIn[j]=Integer.parseInt(st.nextToken());
	    	timeOut[j]=Integer.parseInt(st.nextToken());
	    }    
	    linkedsort(timeIn, timeOut);
	   /*
	    for(int j=0; j<n; j++)
	    {
	    	System.out.print(timeIn[j]+"\t");
	    }
	    System.out.println();
	    for(int j=0; j<n; j++)
	    {
	    	System.out.print(timeOut[j]+"\t");
	    }
	    System.out.println();*/
	    ////////////////////////////
	    
	    maxBreakTime=0;
	    int currentMilkTime=timeOut[0]-timeIn[0];
	    maxMilkTime=currentMilkTime;
	    int endMilkTime= timeOut[0];
	    
	    for(int j=1; j<n; j++)
	    {
	    	
	    	if(timeIn[j] > endMilkTime)
	    	{
	    		
	    		int currentGap= timeIn[j]-endMilkTime;
	    		if(currentGap>maxBreakTime) maxBreakTime=currentGap;
	    		
	    		currentMilkTime = timeOut[j]-timeIn[j];
	    		if(currentMilkTime>maxMilkTime) maxMilkTime=currentMilkTime;
	    		
	    		endMilkTime=timeOut[j];
	    	}
	    	else if( (timeIn[j]<=endMilkTime) && (timeOut[j]>endMilkTime))
	    	{
	    		currentMilkTime+=timeOut[j]-endMilkTime;
	    		endMilkTime=timeOut[j];
	    		if(currentMilkTime>maxMilkTime) maxMilkTime=currentMilkTime;
	    		//
	    	}
	    	//else
	    	//	System.out.print("nothing");
	    }
	    out.println(maxMilkTime+" "+maxBreakTime);
	    out.close();                                  
	    System.exit(0);                               
	}
}
