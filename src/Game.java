import java.util.Scanner;

public class Game {
	private Player player;
	private CPU cpu;
	private Scanner scanner;
	private int boardSize;
	
	public Game(Scanner scanner, int boardSize) {
		this.scanner = new Scanner(System.in);
		this.boardSize = boardSize;
	}

	public void newGame() {
		chooseSides();
		State s = getInitialState();
		
		while (!isGoalState(s)) {			
			Action action;
			if (s.getTurn() == player.getPlayerType()) { // get move from human
				action = playerMove(s);
			} else { 									// have AI pick move
				cpu.visited = 0;
				action = pick(s);		
				System.err.println("Number of visits in minimax(): " + cpu.visited);

			}
			s = result(s,action);
		}	
	}

	// returns the state reached after performing action a in state s
	private State result(State s, Action a) {
		System.err.println(s.getTurn() + " chooses the move: ["+a.row+" " + a.col+"]");
		s.move(a.row, a.col,a.row1, a.col1, s.getTurn());
		s.drawBoard();
		s.drawNineBoard();
		return new State(s.getStateBoard(),alternatePlayer(s.getTurn()), s.getNineBoard());
	}
	
	public static char alternatePlayer(char s) {
		return (s == 'x') ? 'o' : 'x';
	}

	private State getInitialState() {
		return new State(new char[boardSize][boardSize],'x', new char[boardSize][boardSize][boardSize][boardSize]);
	}

	private void chooseSides() {
		System.err.println("Do you want to play as as X or O?");
		
		char playerType = scanner.next().toLowerCase().charAt(0);
		while (playerType != 'x' && playerType != 'o') { // bad input
			System.err.println("Please enter <x> or <o> : ");
			playerType = scanner.next().toLowerCase().charAt(0);
		}
		
		char cpuType = (playerType == 'x') ? 'o' : 'x';	// assigns the cpu to the opposite player type
		player = new Player(playerType);
		cpu = new CPU(cpuType);
	}

	private Action pick(State s) {
		System.err.println("The CPU moves as " + cpu.cpuType);
		return cpu.minimax(s,cpu.cpuType);
//		return cpu.chooseRandomMove(board);	
	}
	
	private Action playerMove(State s) {
		System.err.println(player.getPlayerType()+ ", it is your turn");
		while (true){
			/*
			 * takes in last move
			 * sets board to play on that board
			 * 
			 */
			int BoardLayout=getMoveFromHuman();
			int col1=BoardLayout%3-1;
			col1 = (col1 >= 0) ? col1 : col1 + boardSize;
			int row1 = BoardLayout/ boardSize;	
			row1 = (col1 == boardSize-1) ? row1-1 : row1;
			int userInput = getMoveFromHuman();
			int col = userInput % 3 - 1;
			col = (col >= 0) ? col : col + boardSize;
			int row = userInput / boardSize;	
			row = (col == boardSize-1) ? row-1 : row;
			if (s.isSquareEmpty(row,col)) {
				return new Action(row,col,col1,row1);
			}
			else {
				System.err.println("That spot is already occupied");
			}
		}
	}
	
	private int getMoveFromHuman() {
		System.err.print("Enter the number for your move: ");
		while(!scanner.hasNextInt()) {
			scanner.next(); // next input is not an int, so consume it and move on
		}
		int userInput = scanner.nextInt();
		
		while (userInput < 1 || userInput > 9) { // bad input
			System.err.println("Please enter an integer between 1 and 9: ");
			while(!scanner.hasNextInt()) {
				scanner.next(); // next input is not an int, so consume it and move on
			}
			userInput = scanner.nextInt();
		}
		return userInput;
	}
	
	public boolean isGoalState(State s) {
		char winner;
		if (s.isTie()){
			System.err.println("It's a tie!");
			return true;
		} 
		else if ((winner = s.wonGame()) != Character.MIN_VALUE){
			System.err.println(winner + " wins!");
			return true;
		}
		return false;
	}

}
