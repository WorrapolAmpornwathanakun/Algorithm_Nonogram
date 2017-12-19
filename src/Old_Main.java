import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Scanner;

public class Old_Main {
	StreamTokenizer st;
	Scanner scan;
	int[][] grid = new int[10][10];
	ArrayList<Integer> row[] = new ArrayList[10];
	ArrayList<Integer> col[] = new ArrayList[10];
	int total = 0;
	int MaxR = 0, MaxC = 0;
	
	public static void main(String[] args)throws Exception{
		new Old_Main().run();	
	}
	
	public void run()throws Exception{
		System.setIn(new FileInputStream("in.txt"));
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		scan = new Scanner(System.in);
		st.eolIsSignificant(true);
		
//		st.nextToken();
//		int N = (int) st.nval;
		int N = scan.nextInt();
		scan.nextLine();
		for(int n = 0; n < N; n++){
			total = 0;
			row[n] = new ArrayList<Integer>();
//			int token, i = 0;
			for(String num : scan.nextLine().split(" ")){
				row[n].add(Integer.parseInt(num));
				total = total + Integer.parseInt(num) + 1;
			}
			total = total - 1;
			if(MaxR < row[n].size()) MaxR = row[n].size();
			row[n].add(total);
//			
//			while((token = st.nextToken()) != st.TT_EOF){
//				row[n].add((int) st.nval);
//				i++;
//			}
//			if(MaxR < i) MaxR = i;
		}
		
//		for(int n = 0; n < N; n++){
//			col[n] = new ArrayList<Integer>();
//			int token, i = 0;
//			while((token = st.nextToken()) != st.TT_EOF){
//				col[n].add((int) st.nval);
//				i++;
//			}
//			if(MaxR < i) MaxR = i;
//		}
		System.out.println();
		
		N = scan.nextInt();
		scan.nextLine();
		
		for(int n = 0; n < N; n++){
			total = 0;
			col[n] = new ArrayList<Integer>();
//			int token, i = 0;
			for(String num : scan.nextLine().split(" ")){
				col[n].add(Integer.parseInt(num));
				total = total + Integer.parseInt(num) + 1;
			}
			if(MaxR < col[n].size()) MaxR = col[n].size();
			total = total - 1;
			col[n].add(total);
		}
			
		for(int n = 0; n < N; n++){
			System.out.println(row[n]);
		}
		System.out.println();

		for(int n = 0; n < N; n++){
			System.out.println(col[n]);
		}
		
		Initialize();
	}
	
	public void Initialize(){
		for(int y = 0; y < row.length; y++)
		{
			int space = 10 - row[y].get(row[y].size()-1);
			int pointer = 0;
			for(int j = 0;j<row[y].size()-1;j++)
			{
				if(row[y].get(j) > space){
					pointer += space;
					for(int a = 0; a < row[y].get(j) - space; a++){
						grid[y][pointer + a] = 9;
					}
					pointer += row[y].get(j) - space + 1;
				}else {
					pointer += row[y].get(j) + 1;
				}
				
			}
		}
		
		for(int x = 0; x < col.length; x++)
		{
			int space = 10 - col[x].get(col[x].size()-1);
			int pointer = 0;
			for(int j = 0;j<col[x].size()-1;j++)
			{
				if(col[x].get(j) > space){
					pointer += space;
					for(int a = 0; a < col[x].get(j) - space; a++){
						grid[pointer + a][x] = 9;
					}
					pointer += 1;
				}else {
					pointer += col[x].get(j) + 1;
				}
				
			}
		}
		
		for(int j = 0; j < 10; j++){
			for(int i = 0; i < 10; i++){
				System.out.print((grid[j][i] == 9) ? "X" : "-");
			}
			System.out.println();
		}
	}
}
