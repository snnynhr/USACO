import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/*
ID: sunnyna1
LANG: JAVA
PROG: grader
 */
public class grader {
	static double score=0.0;
	static int tests=10;
	public static void main(String args[]) throws IOException
	{
		for(int i=1; i<=tests; i++)
		{
			long time = System.currentTimeMillis();	
			String[] arg = {"C:\\Users\\Ankur\\Desktop\\Java Projects\\USACO\\bookshelf_gold\\"+i+".in"};
			running.main(arg);
			long runtime = System.currentTimeMillis()-time;
			Scanner in = new Scanner(new FileReader("bookshelf.out"));
			int res = in.nextInt();
			Scanner sol = new Scanner(new FileReader("C:\\Users\\Ankur\\Desktop\\Java Projects\\USACO\\bookshelf_gold\\"+i+".out"));
			int s = sol.nextInt();
			if(res==s && runtime<1000)
			{
				System.out.println("Passed Test Case: "+i);
				System.out.println("Runtime: "+runtime);
				score+=1000.0/tests;
			}
			else
				System.out.println("Failed Test Case: "+i);
		}
		System.out.println("Problem Score: "+score);
		System.exit(0);
	}
}