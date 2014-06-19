import java.io.*;
import java.util.*;


/*
ID: sunnyna1
LANG: JAVA
PROG: packrec
*/

public class packrec {

	private static List<Rectangle> main = new ArrayList<Rectangle>();

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("packrec.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(
				"packrec.out")),true);
		Rectangle[] arr = new Rectangle[4];
		for(int i = 0; i < 4; i++){
			StringTokenizer st = new StringTokenizer(in.readLine());
			arr[i] = new Rectangle(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}
		permutate(0,arr);
		Collections.sort(main, new Comparator<Rectangle>(){
			public int compare(Rectangle arg0, Rectangle arg1) {
				if(arg0.size() < arg1.size()) return -1;
				if(arg0.size() > arg1.size()) return 1;
				if(arg0.width < arg1.width) return -1;
				if(arg0.width > arg1.width) return 1;
				return 0;
			}});
		int size = main.get(0).size();
		out.println(size);
		int width = main.get(0).width;
		int height = main.get(0).height;
		out.println(width + " " + height);
		for(int i = 1; i < main.size(); i ++){
			Rectangle r = main.get(i);
			if(r.size() != size)
				break;
			if(r.width == width && r.height == height)
				continue;
			out.println(r.width + " " + r.height);
			width = r.width;
			height = r.height;
		}
		System.exit(0);
	}

	private static Rectangle[] calculate(int w1, int w2, int w3, int w4,
			int h1, int h2, int h3, int h4){
		Rectangle[] a = new Rectangle[5];

		//1: 
		int width = w1 + w2 + w3 + w4;
		int height = Math.max(Math.max(h1, h2),Math.max(h3, h4));
		a[0] = new Rectangle(width, height);

		//2:
		height = w1 + Math.max(Math.max(h2, h3),h4);
		width = Math.max(h1, w2 + w3 + w4);
		a[1] = new Rectangle(width, height);

		//3:
		height = Math.max(h1, w2 + Math.max(h3, h4));
		width = w1 + Math.max(h2, w3 + w4);
		a[2] = new Rectangle(width, height);

		//4,5:
		width = Math.max(w2,w4) + w1 + w3;
		height= Math.max(Math.max(h1, h3),h2 + h4);
		a[3] = new Rectangle(width, height);

		//6
		height = Math.max(h1+h3, h2+h4);
		if(h3 >= h2 + h4)
			width = Math.max(w1, Math.max(w3+w2,w3+w4));
		else if (h3 > h4 && h3 < h2+h4)
			width = Math.max(Math.max(w1+w2, w2+w3),w3+w4);
		else if( h3 < h4 && h4 < h1+h3)
			width = Math.max(Math.max(w1+w2, w1+w4), w3+w4);
		else if (h4 >=h1+h3)
			width = Math.max(Math.max(w1+w4, w3+w4), w2);
		else 
			width = Math.max(w1+w2, w3+w4);
		a[4] = new Rectangle(width,height);

		return a;
	}

	private static void permutate(int t, Rectangle[] arr){
		if(t == arr.length)
			subset(0,arr);
		else
			for(int i = t; i < arr.length; i++){
				swap(arr,t,i);
				permutate(t+1,arr);
				swap(arr,t,i);
			}
	}

	private static void swap(Rectangle[] a, int p1, int p2){
		Rectangle tmp = a[p1];
		a[p1] = a[p2];
		a[p2] = tmp;
	}

	private static void subset(int t, Rectangle[] arr){
		if(t == arr.length)
			Collections.addAll(main,calculate(arr[0].width,arr[1].width,arr[2].width,arr[3].width,
					arr[0].height,arr[1].height,arr[2].height,arr[3].height));
		else{
			subset(t+1,arr);
			int tmp = arr[t].height; arr[t].height = arr[t].width; arr[t].width = tmp; 
			subset(t+1,arr);
		}
	}
}

class Rectangle{
	public int height;
	public int width;
	public Rectangle(int x, int y){
		width = (x < y ? x : y);
		height = (x > y ? x : y);
	}
	public int size(){
		return height * width;
	}
}