package tp.pr3.logics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import tp.pr3.exception.IncorrectFileException;
import tp.pr3.logic.multigames.GameRules;
import tp.pr3.util.ArrayAsList;
import tp.pr3.util.MyStringUtils;

public class Board {


	private Cell[][] board;

	private int boardSize;

	private ArrayAsList listaVacia;
	
	public Board(int boardSize) {
		this.boardSize = boardSize;
		this.board = new Cell [boardSize][boardSize];

		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				this.board[i][j] = new Cell(0) ;
			}
		}
		CrearArray();
	}
	
	

	public void CrearArray() {		
		this.listaVacia= new ArrayAsList(boardSize);
		for(int i=0; i< boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				Position pos= new Position(0,0);
				pos.setX(i);
				pos.setY(j);
				if(Vacia(i,j))
					listaVacia.insertar(pos);
			}
		}
	}
	

	public ArrayAsList devolverLista() {
		return new ArrayAsList(listaVacia);
	}


	public void setCell(Position pos, int value) {
		int x;
		int y;

		x = pos.getX();
		y = pos.getY();
		board[x][y].setValor(value);
		CrearArray();

	}


	public boolean Vacia(int i, int j) {
		if(board[i][j].isEmpty())
			return false;
		else return true;
	}

	public int getMaxValue() {
		int max = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if(this.board[i][j].getValor() > max) {
					max = this.board[i][j].getValor();
				}
			}
		}
		return max;	
	}

	
	public int getMinValue() {
		int min = getMaxValue();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if(this.board[i][j].getValor()  < min && this.board[i][j].getValor() !=0) {
					min = this.board[i][j].getValor();
				}
			}
		}
		return min;	
	}
	
	
	public MoveResults executeMove(Direction dir, GameRules currentRule) {
		MoveResults movimiento = new MoveResults();
		int fila;
		int columna;

		if(dir == Direction.UP) {
			fila=-1;
			columna=0;
			if(desplazarUP(fila, columna))
				movimiento.setMoved(true);
			movimiento.setPoints(FusionarLEFTYUP(columna,fila,currentRule));
			desplazarUP(fila, columna);//Deplazamos para que se nos junten los numeros depues de fusionar

		}
		else if(dir==Direction.DOWN) {
			fila = 1;
			columna=0;
			if(desplazarDOWN(fila, columna))
				movimiento.setMoved(true);
			movimiento.setPoints(FusionarDOWNYRIGHT(columna, fila, currentRule));
			desplazarDOWN(fila, columna);

		}
		else if(dir == Direction.LEFT) {
			columna =-1;
			fila = 0;
			if(desplazarLEFT(fila, columna))
				movimiento.setMoved(true);
			movimiento.setPoints(FusionarLEFTYUP(columna,fila,currentRule));
			desplazarLEFT(fila, columna);

		}else if(dir == Direction.RIGHT){ 
			columna = 1;
			fila=0;
			if(desplazarRIGHT(fila, columna))
				movimiento.setMoved(true);
			movimiento.setPoints(FusionarDOWNYRIGHT(columna, fila,currentRule));
			desplazarRIGHT(fila, columna);

		}

		if(movimiento.getPoints()!=0)
			movimiento.setMoved(true);// Por si no ha desplazado pero si fusionado

		return movimiento;//Devuelve un movimiento
	}
	

	public boolean  desplazarUP(int valorFila, int valorColumna) {
		boolean posible = false;
		int numHuecos;

		for(int columna=0; columna < boardSize; columna++){
			numHuecos=0;
			for(int fila=0; fila<boardSize;fila++){
				if(!board[fila][columna].isEmpty()){
					numHuecos++;
				}else if(numHuecos>0){
					Position pos = new Position(0,0);
					pos.setX(fila+valorFila*numHuecos);
					pos.setY(columna);
					setCell(pos,board[fila][columna].getValor());
					Position posNeig= new Position(0,0);
					posNeig.setX(fila);
					posNeig.setY(columna);
					setCell(posNeig, 0);
					posible=true;
				}
			}
		}
		return posible;
	}
	
//La funcion desplazar DOWN desplaza todas las fichas hacia abajo
	public boolean desplazarDOWN(int valorFila, int valorColumna) {
		boolean posible = false;
		int numHuecos;
		for(int columna=0; columna < boardSize; columna++){
			numHuecos=0;
			for(int fila=boardSize-1; fila>=0;fila--){
				if(!board[fila][columna].isEmpty()){
					numHuecos++;
				}else if(numHuecos>0){
					Position pos = new Position(0,0);
					pos.setX(fila+valorFila*numHuecos);
					pos.setY(columna);
					setCell(pos,board[fila][columna].getValor());
					Position posNeig= new Position(0,0);
					posNeig.setX(fila);
					posNeig.setY(columna);
					setCell(posNeig, 0);
					posible=true;
				}
			}
		}
		return posible;
	}

	public boolean desplazarLEFT(int valorFila, int valorColumna) {
		boolean posible = false;
		int numHuecos;

		for(int fila=0; fila < boardSize; fila++){
			numHuecos=0;
			for(int columna=0; columna<boardSize;columna++){
				if(!board[fila][columna].isEmpty()){
					numHuecos++;
				}else if(numHuecos>0){
					Position pos=new Position(0,0);
					pos.setX(fila);
					pos.setY(columna+valorColumna*numHuecos);
					setCell(pos,board[fila][columna].getValor());
					Position posNeig= new Position(0,0);
					posNeig.setX(fila);
					posNeig.setY(columna);
					setCell(posNeig, 0);
					posible = true;
				}
			}
		}
		return posible;
	}

	public boolean desplazarRIGHT(int valorFila, int valorColumna) {
		boolean posible = false;
		int numHuecos;

		for(int fila=0; fila < boardSize; fila++){
			numHuecos=0;
			for(int columna=boardSize-1; columna>=0;columna--){
				if(!board[fila][columna].isEmpty()){
					numHuecos++;
				}else if(numHuecos>0){
					Position pos=new Position(0,0);
					pos.setX(fila);
					pos.setY(columna+valorColumna*numHuecos);
					setCell(pos,board[fila][columna].getValor());
					Position posNeig= new Position(0,0);
					posNeig.setX(fila);
					posNeig.setY(columna);
					setCell(posNeig, 0);
					posible = true;
				}
			}
		}
		return posible;
	}

	public int FusionarDOWNYRIGHT(int columna, int fila, GameRules currentRule) {
		Cell neighbour;
		Cell celda;
		int score=0;
		for(int i=boardSize-1; i >= 0; i--) {
			for(int j=boardSize-1; j >=  0; j--){
				celda=board[i][j];
				if(celda.isEmpty()) {
					if((i-fila >= 0) && (j-columna >=0)) {
						neighbour=board[i-fila][j-columna];
						score+=celda.doMerge(neighbour, currentRule);
					}
				}
			}
		}
		return score;
	}

	public int FusionarLEFTYUP(int columna, int fila, GameRules currentRule) {
		Cell neighbour;
		Cell celda;
		int score=0;

		for(int i=0; i < boardSize; i++) {
			for(int j=0; j <  boardSize; j++){
				celda=board[i][j];
				if(celda.isEmpty()) {
					if((i - fila <boardSize)&&(j-columna<boardSize)){
						neighbour=board[i-fila][j-columna];
						score+=celda.doMerge(neighbour, currentRule);
					}
				}
			}
		}
		return score;
	}
	
	public boolean TableroLleno() {
		boolean lleno=true;
		int i=0;
		int j=0;

		while(i<boardSize && lleno) {
			while(j<boardSize && lleno) {
				if(Vacia(i,j))
					lleno=false;
				j++;	
			}
			j=0;
			i++;
		}
		return lleno;
	}

	public boolean JuegoFinalizado() {
		boolean fin=true;
		int i=0;
		int j=0;

		while(i<boardSize && fin) {
			while(j<boardSize && fin) {

				if(i>0 &&board[i][j].getValor()==board[i-1][j].getValor())
					fin=false;
				if(i< boardSize -1 && board[i][j].getValor()==board[i+1][j].getValor())
					fin=false;
				if( j>0 && board[i][j].getValor()==board[i][j-1].getValor() )
					fin=false;
				if(j < boardSize -1  && board[i][j].getValor()==board[i][j+1].getValor())
					fin=false;
				j++;	
			}
			j=0;
			i++;
		}
		
		return fin;
	}
	
	public int[][] getState(){ // Devuelve el estado actual del tablero
		int [][]matriz = new int [boardSize][boardSize];
		for(int i=0; i < boardSize; i ++) {
			for(int j=0; j < boardSize; j++) {
				matriz[i][j]=board[i][j].getValor();
			}
		}
		return matriz;
		
	}
	
	public void setState(int[][] aState){// Pone el tablero en el estado definido por aState
		for(int i=0; i <boardSize; i++) {
			for(int j=0; j<boardSize; j++) {
				board[i][j].setValor(aState[i][j]);
			}
		}
		
	} 
	public int getSize() {
		return boardSize;
	}
	public int ValorCelda(int i, int j) {
		return board[i][j].getValor();
	}
	
	public void store(BufferedWriter bw) {
		try {
			
			for(int i =0; i <boardSize; i++) {
				for(int j=0; j < boardSize; j++) {
					bw.write(board[i][j].getValor() + "\t");
				}
				bw.newLine();					
			}

		}catch(IOException e) {
			e.printStackTrace();
			
		}
		
	}
	public void load(String archivo, BufferedReader br) throws IncorrectFileException {
		try {
			br.readLine();
			String linea =br.readLine();
			String [] tam=linea.split("\\s+");
			
			boardSize=tam.length;
			Board b=new Board(boardSize);
			this.board=b.board;
			
			for(int i=0; i < boardSize; i++) {
				for(int j=0; j<boardSize; j++) {
					this.board[i][j].setValor(Integer.parseInt(tam[j]));
					tam=linea.split("\\s+");
				}
				if(i!=boardSize-1) {
					linea=br.readLine();
					tam=linea.split("\\s+");
					if(tam.length!=boardSize)
						throw new IncorrectFileException("Load failed: invalid file format");
				}
				
			}
			
		}catch(IOException e) {
			e.getMessage();	
		}
		
	}

	public String toString() {
		MyStringUtils dibujo = new MyStringUtils();
		String resultado = "";
		int cellSize=7;
		String space= " ";
		String vDelimiter= "|";
		String hDelimiter="-";
		int val;
		for (int h = 0; h <= board.length; h++) {
			resultado += "\n";
			for (int i = 0; i <board.length; i++) {
				resultado+= dibujo.centre(dibujo.repeat(hDelimiter, cellSize), cellSize);
			}
			resultado+="\n";
			if(h< board.length) {
				for (int j = 0; j <board.length; j++) {
					val=this.board[h][j].getValor();
					if(val!=0){
						resultado+=dibujo.centre(vDelimiter+ dibujo.centre(String.valueOf(val), cellSize),cellSize);
					}
					else
						resultado+=dibujo.centre(vDelimiter+ dibujo.repeat(space, cellSize),cellSize);

				}
				resultado+=vDelimiter;
			}

		}
		return resultado;
	}

}