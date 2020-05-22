package tp.pr3.logic.multigames;

import java.util.Random;
import tp.pr3.logics.Board;
import tp.pr3.logics.Cell;
import tp.pr3.logics.Position;

public class Rules2048 implements GameRules {
	
	
	public Rules2048() {
	
	}

	private static int STOP_VALUE=2048;
	
	public void addNewCellAt(Board board, Position pos, Random rand) {
		int valor = 0;
		int al=rand.nextInt(100);
		if(al <= 90) {
			valor= 2;
		}else valor= 4;
		
		board.setCell(pos, valor);
	}
	
	public int merge(Cell self, Cell other) {
		int score=0;
		if(canMergeNeighbours(self, other)) {
			self.setValor(other.getValor()+self.getValor());
			other.setValor(0);
			score= self.getValor();
	}
		return score;
	}
	
	public int getWinValue(Board board) {
		return board.getMaxValue();
	}
	
	public boolean win(Board board) {
		return getWinValue(board)==STOP_VALUE;
	}

	
	public boolean canMergeNeighbours(Cell cell1, Cell cell2) {
		if(cell1.getValor() == cell2.getValor())
			return true;
		return false;
	}
}
