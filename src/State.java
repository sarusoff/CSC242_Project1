public class State implements Comparable<State> {
	
	char[][]StateofBoard;
	char player;
	Integer value = null;
	
	public State(char[][] StateofBoard, char player){
		this.StateofBoard=StateofBoard;
		this.player=player;
	}
	
	protected void setUtiliy(int s){
			value=s;
	}

	@Override
	public int compareTo(State o) {
		return Integer.compare(this.value, o.value);
	}
}
