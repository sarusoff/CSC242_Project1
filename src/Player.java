
public class Player {
	private char type;

	public Player(char type){
		this.type = type;
	}

	public char getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return "Player [you are " + type + "]";
	}
}

