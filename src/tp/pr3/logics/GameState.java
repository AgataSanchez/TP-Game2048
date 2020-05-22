package tp.pr3.logics;


public class GameState {
	private int[][] boardState; // estado del tablero
	private int score;
	
	public GameState(int [][] boardState, int score) {
		this.boardState=new int[boardState.length][boardState.length];
		for(int i=0; i<boardState.length; i++) {
			for (int j = 0; j < boardState.length; j++) {
			this.boardState[i][j]= boardState[i][j];
			}
		}
		this.score = score;
	}
	
	public int[][] getBoardState() {
		return boardState;
	}
	public int getScore() {
		return score;
	}
	
}
