package tp.pr3.logic.multigames;

import java.util.Random;
import tp.pr3.logics.Board;
import tp.pr3.logics.Cell;
import tp.pr3.logics.Position;
import tp.pr3.util.ArrayAsList;

public interface GameRules {
	void addNewCellAt(Board board, Position pos, Random rand);
	int merge(Cell self, Cell other);
	int getWinValue(Board board);
	boolean win(Board board);
	
	default boolean lose(Board board) {
		return board.JuegoFinalizado() && board.TableroLleno();
	}
	
	default Board createBoard(int size) {
		Board bo=new Board(size);
		return bo;
	}
	
	default void addNewCell(Board board, Random rand) {
		board.CrearArray();
		addNewCellAt(board,(Position) ArrayAsList.choice(board.devolverLista(), rand), rand);
	}
	
	default void initBoard(Board board,int numCells, Random rand) {
		for(int i = 0; i < numCells; i++) {
			addNewCell(board, rand);	
		}
	}
	
	boolean canMergeNeighbours(Cell cell1, Cell cell2);


}
