public class Board {
	private int size;
	private char[][] gameboard;
	
	public Board(int n){
		size = n;
		gameboard= new char[n][n];
	}
	
	public boolean isSquareEmpty(int a, int b){
		return gameboard[a][b] == Character.MIN_VALUE;
	}
	
	public void move(int x, int y, char type){
		gameboard[x][y] = type;	
	}
	
	public boolean isBoardFull(){
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
	
//	public List<Move> MovesPossible(Player p3, Board b){
//	List<Move>AllMoves = new ArrayList<Move>();
//	for(int t=0;t<size;t++){
//		for(int u=0;u<size;u++){
//			if(isEmpty(t,u)==true){
//				AllMoves.add(new Move(b,t,u,p3));
//				
//			}
//		}
//	}
//	return AllMoves;
//}
}

