package tp.pr3.logic.multigames;

import java.util.Random;

import tp.pr3.logics.Board;
import tp.pr3.logics.Cell;
import tp.pr3.logics.Position;

public class RulesInverse implements GameRules {
	private static int STOP_VALUE=1;
	
	public int getWinValue(Board board) {
		return board.getMinValue();
	}
	
	public void addNewCellAt(Board board, Position pos, Random rand) {
		int valor = 0;
		int al=rand.nextInt(100);
		if(al <= 90) {
			valor= 2048;
		}else valor= 1024;
		
		board.setCell(pos, valor);
	}
	
	public int merge(Cell self, Cell other) {
		int score=0;
		if(canMergeNeighbours(self, other)) {
			self.setValor(self.getValor()/2);
			other.setValor(0);
			score= self.getValor();
			return (2048/score);
		}
		return 0;
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
