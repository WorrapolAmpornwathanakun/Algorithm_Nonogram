import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class Main {
	StreamTokenizer st;
	
	int Nr, Nc, Mr[], Mc[], gridr[][], gridc[][];
	
	ArrayList<Integer> row[], col[];
	
	public static void main(String[] args)throws Exception{
		new Main().run();
	}
	
	public void run()throws Exception{
		System.setIn(new FileInputStream("in.txt"));
		st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		
		st.eolIsSignificant(true);
		
		//Input row
		st.nextToken();
		Nr = (int) st.nval;	st.nextToken();
		row = new ArrayList[Nr];
		Mr = new int[Nr];
		
		for(int n = 0; n < Nr; n++){
			row[n] = new ArrayList<Integer>();
			int token, M = 0;
			while((token = st.nextToken()) != st.TT_EOL){
				int num = (int)st.nval;
				row[n].add(num);
				M += num + 1;
			}
			Mr[n] = M - 1;
		}
		
		//Input Column
		st.nextToken();
		Nc = (int) st.nval;	st.nextToken();
		col = new ArrayList[Nc];
		Mc = new int[Nc];
		
		for(int n = 0; n < Nc; n++){
			col[n] = new ArrayList<Integer>();
			int token, M = 0;
			while((token = st.nextToken()) != st.TT_EOL){
				int num = (int)st.nval;
				col[n].add(num);
				M += num + 1;
			}
			Mc[n] = M - 1;
		}
		
		//setup table
		gridc = new int[Nr][Nc];
		gridr = new int[Nr][Nc];
		
		//1. Mark the collapse box
		Initialize();
		
		//2. Cross the box that used
		cross();
		
		for(int y = 0; y < Nr; y++){
			for(int x : gridr[y]){
				switch(x){
					case 0 :	System.out.print(" ");	break;
					case -1 :	System.out.print("#");	break;
					case -2 :	System.out.print("x");	break;
					default :	System.out.print(x);	break;
				}
			}
			System.out.print("\t");
			
			for(int x : gridc[y]){
				switch(x){
					case 0 :	System.out.print(" ");	break;
					case -1 :	System.out.print("#");	break;
					case -2 :	System.out.print("x");	break;
					default :	System.out.print(x);	break;
				}
			}
			
			System.out.println();
		}
		
	}
	
	public void Initialize(){
		
		//for each row
		for(int n = 0; n < Nr; n++){
			int space = Nc - Mr[n];
			int pointer = 0;
			if(space == 0){
				for(int x = 0; x < row[n].size(); x++){
					int num = row[n].get(x);
					for(int p = 0; p < num - space; p++){
						gridr[n][pointer] = x + 1;
//						if(gridc[n][pointer] < 0)	
							gridc[n][pointer] = -1;
						pointer += 1;
					}
					if(pointer != Nc){
						gridr[n][pointer] = -2;
						pointer += 1;
					}
				}
			}else {
				for(int x = 0; x < row[n].size(); x++){
					int num = row[n].get(x);
					if(num > space){
						pointer += space;
						for(int p = 0; p < num - space; p++){
							gridr[n][pointer] = x+1;
							if(gridc[n][pointer] < 0)	gridc[n][pointer] = -1;
							pointer += 1;
						}
						pointer += 1;
					}else {
						pointer += num + 1;
					}
				}
			}
		}
		
		//for each column
		for(int n = 0; n < Nc; n++){
			int space = Nr - Mc[n];
			int pointer = 0;
			if(space == 0){
				for(int y = 0; y < col[n].size(); y++){
					int num = col[n].get(y);
					for(int p = 0; p < num - space; p++){
						gridc[n][pointer] = y+1;
						if(gridr[n][pointer] < 0)	gridr[n][pointer] = -1;
						pointer += 1;
					}
					if(pointer != Nc){
						gridc[pointer][n] = -2;
						pointer += 1;
					}
				}
			}else {
				for(int y = 0; y < col[n].size(); y++){
					int num = col[n].get(y);
					if(num > space){
						pointer += space;
						for(int p = 0; p < num - space; p++){
							gridc[pointer][n] = y+1;
							if(gridr[n][pointer] < 0)	gridr[n][pointer] = -1;
							pointer += 1;
						}
						pointer += 1;
					}else {
						pointer += num + 1;
					}
				}
			}
		}
	}
	
	public void cross(){
		//row
		for(int j = 0; j < Nr; j++){
			for(int y = 0; y < row[j].size(); y++){
				int pointer = 0;
				System.out.println(gridr[pointer][y]);
			}
			System.out.println();
		}
	}
}
