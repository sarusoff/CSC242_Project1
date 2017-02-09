import java.util.Random;

public class CPU {

	private String name;
	

	public CPU(String s) {
		name = s;
	}

	
	public void chooseMove() {
		Random rand = new Random();
		while(true) {
			int randCol = rand.nextInt(3);
			int randRow = rand.nextInt(3);
			System.out.println("CPU chooses col: " + randCol);
			System.out.println("CPU chooses row: " + randRow);
			
			if(Board.isEmpty(randCol, randRow)){
				System.out.println("HI?");
				Board.moveCPU(randCol, randRow, name);
				break;
			}
			else {
				System.out.println("This space is already taken");
			}
			
		}
		System.out.println("HERE???");
	}
	
	public static String OtherGuyCPU(String human){
		if(human.equalsIgnoreCase("x")){
			return "y";
		}
		else{
			return "x";
		}
	}
	
	
}
