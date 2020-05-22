package tp.pr3.logic.multigames;

import java.util.Random;

import tp.pr3.logics.Board;
import tp.pr3.logics.Cell;
import tp.pr3.logics.Position;
import tp.pr3.util.*;


public class RulesFib implements GameRules{
	private static int STOP_VALUE = 144;
	
	public int getWinValue(Board board) {
		return board.getMaxValue();
	}
	
	public void addNewCellAt(Board board, Position pos, Random rand) {
		int valor = 0;
		int al=rand.nextInt(100);
		if(al <= 90) {
			valor= 1;
		}else valor= 2;
		
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
	
	public boolean win(Board board) {
		return getWinValue(board)==STOP_VALUE;
	}
	public boolean lose(Board board) {
		return JuegoFinalizado(board) && board.TableroLleno();
		//return true;
	}
	public boolean JuegoFinalizado(Board board) {
		boolean fin=true;
		int i=0;
		int j=0;

		while(i<board.getSize() && fin) {
			while(j<board.getSize() && fin) {
				if(board.ValorCelda(i, j)==0)
					fin=false;
				if(i>0 && !comprobarCeldas(board.ValorCelda(i, j), board.ValorCelda(i - 1, j) ))
					fin=false;
				if(i< board.getSize() -1 && !comprobarCeldas(board.ValorCelda(i, j), board.ValorCelda(i + 1, j)))
					fin=false;
				if( j>0 && !comprobarCeldas(board.ValorCelda(i, j), board.ValorCelda(i, j-1) ))
					fin=false;
				if(j < board.getSize() -1  && !comprobarCeldas(board.ValorCelda(i, j), board.ValorCelda(i, j+1)))
					fin=false;
				j++;	
			}
			j=0;
			i++;
		}
		
		return fin;
	}
	private boolean comprobarCeldas(int i, int j) {
		boolean fin=true;
		if((i==j && i==1) || (i==MyMathsUtil.nextFib(j)|| j==MyMathsUtil.nextFib(i)) ) {
			fin=false;
		}
		return fin;
	}

	@Override
	public boolean canMergeNeighbours(Cell cell1, Cell cell2) {
		int suma=cell1.getValor()+cell2.getValor();
		if(suma==MyMathsUtil.nextFib(cell1.getValor()) || suma==MyMathsUtil.nextFib(cell2.getValor()))
			return true;
		return false;
	}
}


