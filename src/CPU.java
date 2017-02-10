import java.util.Random;

public class CPU {
	private char type;
	
	public CPU(char type) {
		this.type = type;
	}

	public void chooseMove(Board board) {
		Random rand = new Random();
		while (true) {
			int randCol = rand.nextInt(3);
			int randRow = rand.nextInt(3);
			
			if (board.isSquareEmpty(randCol, randRow)){
				System.out.println("CPU chooses the move: ["+randRow+","+randCol+"]");
				board.move(randCol, randRow, type);
				break;
			}
		}
	}
	
	public void TestMini(Board board, CPU cpu, Player player){
		int [] test = MiniMax.minimax(1,2,type,board,cpu,player);
		System.out.println("CPU chooses the move: ["+test[1]+" " + test[2]+")");

		board.move(test[1], test[2], type);
	}
	
	public char getType() {
		return type;
	}
}
