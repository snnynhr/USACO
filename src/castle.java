import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
ID: sunnyna1
LANG: JAVA
TASK: castle
 */
/*
public class castle
{
	static int x,y;
	static int[][] grid, wall;
	static ArrayList<Integer> c;
	static int curr;
	static final int OCC=2;
	public static void main(String args[]) throws IOException
	{
		long time = System.currentTimeMillis();
		BufferedReader in = new BufferedReader(new FileReader("castle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter( new FileWriter("castle.out")));
		StringTokenizer z = new StringTokenizer(in.readLine());
		y = Integer.parseInt(z.nextToken());
		x = Integer.parseInt(z.nextToken());
		grid = new int[2*x+1][2*y+1];
		wall = new int[x][y];
		for(int i=0; i<x; i++)
		{
			z = new StringTokenizer(in.readLine());
			for(int j=0; j<y; j++)
				wall[i][j] = Integer.parseInt(z.nextToken());
		}
		buildGrid();
		int[][] tgrid = new int[2*x+1][2*y+1];
		for(int l=0; l<2*x+1; l++)
			for(int m=0; m<2*y+1; m++)
				tgrid[l][m]=grid[l][m];
		int max = maximum(tgrid);
		int maxi=0, maxj=0;
		for(int i=1; i<2*x; i++)
		{
			for(int j=1-i%2; j<2*y; j+=2)
			{
				if(grid[i][j]==1)
				{
					tgrid = new int[2*x+1][2*y+1];
					for(int l=0; l<2*x+1; l++)
						for(int m=0; m<2*y+1; m++)
							tgrid[l][m]=grid[l][m];
					tgrid[i][j]=0;
					int tmp = maximum(tgrid);
					if(tmp>=max){
						max=tmp;
						maxi=i;
						maxj=j;
					}
				}
			}
		}
		out.println(max+"   ("+ maxi+"," +maxj+ ")");
		//for(int i=0; i<grid.length; i++)
			//out.println(Arrays.toString(grid[i]));
		out.println(System.currentTimeMillis()-time);
		out.close();
		System.exit(0);
	}
	public static int maximum(int[][] g)
	{
		c=new ArrayList<Integer>(2505);
		for(int i=0; i<2505; i++)
			c.add(0);
		curr=3;
		for(int i=1; i<g.length-1; i+=2)
		{
			for(int j=1; j<g[i].length-1; j+=2)
			{
				if(g[i][j]==OCC)
				{
					floodfill(i,j,g);
					curr++;
				}
			}
		}
		//out.println((curr-3)+" rooms");
		Collections.sort(c);
		//out.println((c.get(c.size()-1))+" max room size");
		return c.get(c.size()-1);
	}
	public static void floodfill(int i, int j, int[][] g)
	{
		if(g[i][j]==OCC)
		{
			g[i][j]=curr;
			c.set(curr,c.get(curr)+1);
			//east
			if(j+2 < 2*y+1 && g[i][j+1]==0) floodfill(i,j+2,g);
			//west
			if(j >=2 && g[i][j-1]==0) floodfill(i,j-2,g);
			//north
			if(i>=2 && g[i-1][j]==0) floodfill(i-2,j,g);
			if(i+2 < 2*x+1 && g[i+1][j]==0) floodfill(i+2,j,g);
		}
	}
	public static String getBinary(int x)
	{
		String s=Integer.toBinaryString(x);
		return "0000".substring(s.length())+s;
	}
	public static void buildGrid()
	{
		for(int i=0; i<x; i++)
		{
			for(int j=0; j<y; j++)
			{
				grid[2*i+1][2*j+1]=OCC;
				String c = getBinary(wall[i][j]);
				if(c.charAt(0)=='1')
					grid[2*i+2][2*j+1]=1;
				if(c.charAt(1)=='1')
					grid[2*i+1][2*j+2]=1;
				if(c.charAt(2)=='1')
					grid[2*i][2*j+1]=1;
				if(c.charAt(3)=='1')
					grid[2*i+1][2*j]=1;
			}
		}
		for(int i=0; i<2*y+1; i++)
		{
			grid[0][i]=1;
			grid[grid.length-1][i]=1;
		}
		for(int j=0; j<2*x+1; j++)
		{
			grid[j][0]=1;
			grid[j][grid[j].length-1]=1;
		}
	}
}
*/
import java.util.*;
public class castle {

	public class Data
	{
		int x,y;
		public Data(int a,int b)
		{
			x = a;
			y= b;
		}
	}
	int[] dx ={0,-1,0,1};
	int[] dy ={-1,0,1,0};
	int[] shl ={1,2,4,8};
	int[][] map = new int[57][57];
	int[][] main = new int[57][57];
	int[] room_size = new int[2507];
	int n ,m,num_room=0,max_size=0,num_ans=0;
	Data[] list = new Data[2507];
	int[] ansx = new int[10007];
	int[] ansy = new int[10007];
	int[] ans_size = new int[10007];
	char[] ansc = new char[10007];
	Scanner cin;
	PrintWriter cout;
	public static void main(String[] args) throws IOException {
		new castle().run();

	}
	void run() throws IOException
	{
		cin=new Scanner(new FileReader("castle.in"));
		cout=new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
		m = cin.nextInt();
		n = cin.nextInt();
		for(int i = 1;i <= n;i++)
			for(int j = 1;j <= m;j++)
				map[i][j] = cin.nextInt();
		
		for(int i = 1;i <= n;i++)
			for(int j = 1;j <= m;j++)
				if(main[i][j] == 0)
					bfs(i,j);
		
		cout.println(num_room);
		for(int i = 1;i <= num_room;i++)
			if(room_size[i] > max_size)
				max_size = room_size[i];
		cout.println(max_size);
		cut();
		cout.close();

	}
	void cut()
	{
		int tmpx = 0,tmpy = 0;
		int cutx = 0,cuty = 0;
		int size;
		char wall = ' ';
		for(int i = 1;i <= n;i++)
			for(int j = 1;j <= m;j++)
				for(int k = 0;k <4 ;k++)
				{
					tmpx = i + dx[k];
					tmpy = j + dy[k];
					if(main[i][j] != main[tmpx][tmpy] && tmpx > 0 && tmpy > 0 && tmpx <= n && tmpy <= n)
					{
						size = room_size[main[i][j]] + room_size[main[tmpx][tmpy]];
						if(size >= max_size)
						{
							num_ans++;
							max_size = size;
							if(k == 0)
							{
								cutx = tmpx;
								cuty = tmpy;
								wall = 'E';
							}
							if(k == 3)
							{
								cutx = tmpx;
								cuty = tmpy;
								wall = 'N';
							}
							if(k == 1)
							{
								cutx = i;
								cuty = j;
								wall = 'N';
							}
							if(k == 2)
							{
								cutx = i;
								cuty = j;
								wall = 'E';
							}
							ansx[num_ans] = cutx;
							ansy[num_ans] = cuty;
							ansc[num_ans] = wall;
							ans_size[num_ans] = max_size;
						}
					}
				}
		tmpx = tmpy = 99;
		cout.println(max_size);
		for(int i = 1;i <= num_ans;i++)
		{
			if(ans_size[i] == max_size)
			{
				if(ansy[i] < tmpy)
				{
					tmpy = ansy[i];
					tmpx = ansx[i];
					wall = ansc[i];
				}else
					if(ansy[i] == tmpy)
					{
						if(ansc[i] == 'N')
						{
							tmpy = ansy[i];
							tmpx = ansx[i];
							wall = ansc[i];
						}else
							if(ansx[i] > tmpx)
							{
								tmpy = ansy[i];
								tmpx = ansx[i];
								wall = ansc[i];
							}
					}
			}
		}
		cout.println(tmpx+" "+tmpy+" "+wall);
	}
	void bfs(int x,int y)
	{
		Data u = new Data(0,0);
		int head = 1,tail = 1;
		list[1] = new Data(x,y);
		num_room++;
		main[x][y] = num_room;
		room_size[num_room]++;
		while (head <= tail)
		{
			u.x = list[head].x;
			u.y = list[head].y;
			for(int i = 0;i < 4;i++)
			{
				x = u.x + dx[i];
				y = u.y + dy[i];
				//out.println(map[u.x][u.y] & shl[i]);
				if(main[x][y] == 0 && (map[u.x][u.y] & shl[i]) == 0 && x > 0 && y > 0 && x <= n && y <= m)
				{
					tail++;
					list[tail] = new Data(x,y);
					main[x][y] = num_room;
					room_size[num_room]++;
				}
			}
			head++;
		}
	}
}