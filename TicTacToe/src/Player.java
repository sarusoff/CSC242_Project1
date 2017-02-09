
public class Player {
		String t;
	
		public Player(String t){
			if(t.equalsIgnoreCase("x")){
				this.t="x";
			}
			else if(t.equalsIgnoreCase("y")){
				this.t="y";}
			else{
				System.out.println("Please enter either x or y");
			}
		}
		
		@Override
		public String toString() {
			return "Player [you are " + t + "]";
		}

		public Player OtherGuy(){
			if(t.equalsIgnoreCase("x")){
				return new Player("y");
				}
			else{
				return new Player("x");
			}
		}

		public String getT() {
			return t;
		}

		public void setT(String t) {
			this.t = t;
		}
}

