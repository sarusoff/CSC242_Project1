import java.util.ArrayList;
import java.util.List;

public class Board {
	private int size;
	private char[][] gameboard;
	
	public Board(int n){
		size = n;
		gameboard = new char[n][n];
	}
	
	public boolean isSquareEmpty(int row, int col){
		return gameboard[row][col] == Character.MIN_VALUE;
	}
	
	public void move(int row, int col, char type){
		gameboard[row][col] = type;	
	}

	public boolean isTie(){
		for (int i=0;i<size;i++){
			for (int j=0;j<size;j++){
				if (gameboard[i][j] == Character.MIN_VALUE){
					return false;
				}
			}
		}
		return true;
	}
	
	/* returns x, y, or null, for x winning, y winning, or nobody winning*/
	public char wonGame() {
		char p;
		
		p = CheckV();
		if (p!=Character.MIN_VALUE){
			return p;
		}
		
		p = CheckH();
		if (p!=Character.MIN_VALUE){
			return p;
		}
		
		p = CheckD();
		if (p!=Character.MIN_VALUE){
			return p;
		}
		return Character.MIN_VALUE;		
	}

	public char CheckAnswer(int x,int y, int x1, int y1){	 
		char p1 = gameboard[x][y];
		if (p1==Character.MIN_VALUE){
			return Character.MIN_VALUE;
		}
		while (x >= 0 && x < size && y >= 0 && y < size) {
			if (gameboard[x][y] != p1) {
				return Character.MIN_VALUE;
			}
			x += x1;
			y += y1;
		}
		return p1;
	}
	
	public char CheckH(){
		for (int i=0; i < size; i++) {
			char p = CheckAnswer(i,0,0,1);
			if (p != Character.MIN_VALUE) {
				return p;
			}
		}
		return Character.MIN_VALUE;
	}
	
	public char CheckV(){
		for(int i = 0; i<size;i++){
			char p = CheckAnswer(0,i,1,0);
			if (p!=Character.MIN_VALUE){
				return p;
			}
		}	return Character.MIN_VALUE;
	}
	
	public char CheckD(){
		char p = CheckAnswer(0, 0, 1, 1);
		if (p != Character.MIN_VALUE) {
			return p;
		}
		p = CheckAnswer(0, size-1, 1, -1);
		if (p != Character.MIN_VALUE) {
			return p;
		}
		return Character.MIN_VALUE;
	}
	
	public void drawBoard(){
		int row = gameboard.length;
		int col = gameboard[0].length;;

		System.out.print("    ");
		for (int i = 0; i < col; i++)
			System.out.print(i + "   ");
		System.out.println();

		for (int i = 0; i < row; i++) {
			System.out.print(i + "  ");
			for (int j = 0; j < col; j++) {
				if (j ==0){

				}
				else{
					System.out.print(".");
				}
				System.out.print(" " + (gameboard[i][j] == Character.MIN_VALUE ? "-" :  gameboard[i][j]) + " "); // replaces null with -
			}
			System.out.println();
			if (i != (row - 1)) {
				System.out.print("   ");
				for (int j = 0; j <col; j++) {
					if (j != 0)
						System.out.print("......");
				}
				System.out.println();
			}
		}
		System.out.println();
	}
	public int returnMin(List<Integer> list){
		int min= Integer.MAX_VALUE;
		for(int i=0; i<list.size();++i){
			if(list.get(i)<min){
				min=list.get(i);
			}
		}
		return min;
	}
	public List<int[]> getPossibleMoves(){
		List<int[]> PossibleMoves = new ArrayList<int[]>();
		for(int p =0; p<3;++p){
			for(int r=0; r<3;++r){
				if(isSquareEmpty(p, r)==true){
					PossibleMoves.add(new int[]{p,r});
				}
			}
		}
		
		return PossibleMoves;
	}
	
	public int returnMax(List<Integer> list){
		int max= Integer.MIN_VALUE;
		for(int i=0; i<list.size();++i){
			if(list.get(i)>max){
				max = list.get(i);
			}
		}
		return max;
	}
}
