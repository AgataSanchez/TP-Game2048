package tp.pr3.logics;

import tp.pr3.logic.multigames.*;

public class Cell {
	private int valor;
	
	public Cell(int valor) {
		this.valor = valor;
	}

	public boolean isEmpty(){
		if(this.valor==0) return false;
		else return true;
	}

	public int doMerge(Cell neighbour, GameRules rules) {
		int valor = rules.merge(this, neighbour);
		if(valor != 0) {
			return valor;
		}
		else return 0;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}


}