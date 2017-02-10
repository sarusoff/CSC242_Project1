import java.util.Scanner;

public class PlayGame {
	private Player player;
	private CPU cpu;
	private Scanner scanner;
	private Board board;
	
	public PlayGame() {
		board = new Board(3); // takes in a parameter for dimension of the board
		scanner = new Scanner(System.in);
		chooseSides();
	}

	public void start() {
		while (true) {
			move('x');
			board.drawBoard();
			
			if (isGameDone()) {
				break;
			}
			
			move('y');
			board.drawBoard();

			if (isGameDone()) {
				break;
			}
		}	
		
		scanner.close();
	}
	
	private void chooseSides() {
		System.out.println("Do you want to play as as x or y?");
		
		char playerType = scanner.next().charAt(0);
		while (playerType != 'x' && playerType != 'y') { // bad input
			System.out.println("Please enter <x> or <y> : ");
			playerType = scanner.next().charAt(0);
		}
		
		char cpuType = (playerType == 'x') ? 'y' : 'x';	// assigns the cpu to the opposite player type
		player = new Player(playerType);
		cpu = new CPU(cpuType);
	}

	private void move(char type) {
		if (player.getType() == type) {
			playerMove();
		} else {
			cpuMove();
		}
	}
	
	private void playerMove() {
		System.out.println(player.getType()+ ", it is your turn");
		while (true){
			System.out.print("Enter the column for a move: ");
			int col = scanner.nextInt();
			System.out.print("Enter the row for a move: ");
			int row = scanner.nextInt();
			
			if (board.isSquareEmpty(row,col)) {
				board.move(row, col, player.getType());		
				break;
			}
			else {
				System.out.println("That spot is already occupied");
			}
		}
	}

	private void cpuMove() {
		System.out.println("The CPU moves as " + player.getType());
		cpu.chooseMove(board);	
	}
	
	private boolean isGameDone() {
		char winner;
		if (board.isBoardFull()){
			System.out.println("It's a tie!");
			return true;
		} 
		else if ((winner = board.wonGame()) != Character.MIN_VALUE){
			System.out.println(winner + " wins!");
			return true;
		}
		return false;
	}

}