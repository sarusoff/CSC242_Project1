import java.util.Random;

public class CPU {
	private char cpuType;
	private char humanType;
	
	public CPU(char cpuType, char humanType) {
		this.cpuType = cpuType;
		this.humanType = humanType;
	}

	public void chooseRandomMove(Board board) {
		Random rand = new Random();
		while (true) {
			int randCol = rand.nextInt(3);
			int randRow = rand.nextInt(3);
			
			if (board.isSquareEmpty(randCol, randRow)){
				System.out.println("CPU chooses the move: ["+randRow+","+randCol+"]");
				board.move(randCol, randRow, cpuType);
				break;
			}
		}
	}
	
	public void chooseMinimaxMove(Board board){		
		int [] move = minimax(board,cpuType);
		System.out.println("CPU chooses the move: ["+move[1]+" " + move[2]+")");
		board.move(move[1], move[2], cpuType);
	}
	
	
	/*
	 -player changes from 'x' to 'y' depending on whose turn it is 
	 -minimax returns an int array of {bestScore, row, col}
	 
	 */
	private int[] minimax(Board board, char player){
		
		// set bestScore the the worst starting place, so that the first move will be the new bestScore
		int bestScore = (player == cpuType) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		int row = -999;
		int col = -999;
		char winner = board.wonGame(); // x, y, or Character.MIN_VALUE
		
		if (winner == cpuType) {
			bestScore = -1;
		} else if (winner == humanType) {
			bestScore = 1;
		} else if (board.isTie()) {
			bestScore = 0;
		} else {			
			for (int[] move : board.getPossibleMoves()) {
				
				// make this move on the board (going to remove it from the board afterwards)
				board.move(move[0], move[1],player);
				
				// CPU's move, so trying to minimize utility
				if (player == cpuType) { 
					int currentScore = minimax(board,humanType)[0];
					if (currentScore < bestScore) {
						bestScore = currentScore;
						row = move[0];
						col = move[1];
					}
				} 
				else { 	// Human's move, so trying to maximize utility
					int currentScore = minimax(board,cpuType)[0];
					if (currentScore > bestScore) {
						bestScore = currentScore;
						row = move[0];
						col = move[1];
					}
				}
				
				// undo the move from the board
				board.move(move[0], move[1], Character.MIN_VALUE);
			}			
		}
		return new int[] {bestScore,row,col};
	}
	
	public char getCPUType() {
		return cpuType;
	}
}
