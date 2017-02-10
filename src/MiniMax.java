import java.util.List;

public class MiniMax{

	public static int[] minimax(int turn,int depth,char type, Board b,CPU cpu, Player p1)
	{
		char winner=b.wonGame();
		char p= 'a';
		if(type=='x'){
			p='y';
			
		}
		else{
			p='x';
		}
		int bestR=-1;
		int bestC=-1;
		int bestscore =(turn==1)? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int Score;
		List<int[]> l1= b.PossibleMoves();
		if(b.isBoardFull()==true){
			bestscore=0;
				}
			else if(winner==cpu.getType()){
				bestscore=-1;
			}
			else if (winner == p1.getType()){
				bestscore=1;
			}

		else{
			for(int[] move: l1){
				b.move(move[0], move[1],type);
				if(turn == 1){
					Score = minimax(-1,depth-1,p,b,cpu,p1)[0]; 
					//bestscore = Math.max(Score,bestscore);
					for(int y=0; y<l1.size();y++){
						for(int u =0; u<l1.get(y).length;u++){
							System.out.println(l1.get(y)[u]);
						}
						System.out.println("MAX PART");
					}
					if(bestscore<Score){
						bestscore=Score;
					bestR= move[0];
					bestC=move[1];
					}
					System.out.println("First if Statement");


				}
				if(turn==-1){
					Score = minimax(1, depth-1,type,b,cpu,p1)[0];
					//bestscore=Math.min(Score,bestscore);
					for(int y=0; y<l1.size();y++){
						for(int u =0; u<l1.get(y).length;u++){
							System.out.println(l1.get(y)[u]);
						}
						System.out.println("MIN PART");
					}
					if(bestscore>Score){
						bestscore=Score;
						bestR= move[0];
						bestC=move[1];
						}
				
					System.out.println("Second if Statement!");

				}
				b.voidMove(move[0], move[1]);
			}
		}
	
	return new int[] {bestscore, bestR, bestC};
	}
}
