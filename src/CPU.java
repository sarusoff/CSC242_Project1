import java.util.List;

public class CPU {
	private char cpuType;
	public int visited = 0;
	
	public CPU(char cpuType) {
		this.cpuType = cpuType;
	}

//	public Action chooseRandomMove(Board board) {
//		Random rand = new Random();
//		while (true) {
//			int randCol = rand.nextInt(3);
//			int randRow = rand.nextInt(3);
//			
//			if (board.isSquareEmpty(randCol, randRow,board.gameboard)){
//				return new Action(randRow,randCol);
//			}
//		}
//	}
	
	public Action search(List<Action> a, State s){		
		int [] move = minimax(s,a,cpuType);
		return new Action(move[1],move[2]);
	}
	
	
	/*
	 -minimax returns an int array of {bestUtility, row, col}
	 -This is a recursive depth first search algorithm
	 */
	private int[] minimax(State s, List<Action> actions, char player){

		// set bestUtility the the worst starting place, so that the first move will be the new best
		int bestUtility = (player == cpuType) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
		int row = -999;
		int col = -999;
		char winner = s.wonGame(); // x, y, or Character.MIN_VALUE
		
		if (winner == cpuType) {
			bestUtility = -1;
		} else if (winner == Game.alternatePlayer(player)) {
			bestUtility = 1;
		} else if (s.isTie()) {
			bestUtility = 0;
		} else {			
			for (Action a : actions) {
				
				// make this move on the board (going to remove it from the board afterwards)
				s.move(a.getRow(), a.getCol(), player);
				
				// CPU's move, so trying to minimize utility
				if (player == cpuType) { 
					int currentUtility = minimax(s,s.applicableActions(),Game.alternatePlayer(player))[0];
					if (currentUtility < bestUtility) {
						bestUtility = currentUtility;
						row = a.getRow();
						col = a.getCol();
					}
				} 
				else { 	// Human's move, so trying to maximize utility
					int currentUtility = minimax(s,s.applicableActions(),Game.alternatePlayer(player))[0];
					if (currentUtility > bestUtility) {
						bestUtility = currentUtility;
						row = a.getRow();
						col = a.getCol();
					}
				}
				
				// undo the move from that state in order to perform the next iteration in the for loop
				s.move(a.getRow(), a.getCol(), Character.MIN_VALUE);
			}			
		}
		
		visited++;
		return new int[] {bestUtility,row,col};
	}
	
	public char getCPUType() {
		return cpuType;
	}
}
