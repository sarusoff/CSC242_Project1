

public class Action {
	public int row;
	public int col;
	public int util;
	public int row1;
	public int col1;
	
	public Action(int row, int col, int utility) {
		this.row = row;
		this.col = col;
		this.util = utility;
	}
	
	public Action(int row, int col) {
		this.row = row;
		this.col = col;
	}
	public Action(int row, int col, int row1, int col1){
		this.row=row;
		this.col=col;
		this.row1=row1;
		this.col1=col1;
	}

}
