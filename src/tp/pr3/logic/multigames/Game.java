package tp.pr3.logic.multigames;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Random;

import tp.pr3.exception.IncorrectFileException;
import tp.pr3.logics.Board;
import tp.pr3.logics.Direction;
import tp.pr3.logics.GameState;
import tp.pr3.logics.GameStateStack;
import tp.pr3.logics.GameType;
import tp.pr3.logics.MoveResults;

public class Game {

	private Board board;
	private int currentSize; 
	private int currentInitCells; 
	private Random myRandom;
	private int score;
	private int high;
	private boolean lost;
	private boolean fin;
	private GameStateStack undoStack = new GameStateStack();
	private GameStateStack redoStack = new GameStateStack();
	private GameRules currentRules;
	private long currentSeed;
	private GameType tipo;


	public Game(GameType rules, long seed, int dim, int initCells) {

		currentRules=rules.getRules();
		tipo=GameType.parse(rules.externalise());
		currentSeed = seed;
		myRandom = new Random(seed);
		currentSize = dim;
		currentInitCells = initCells;
		reset();
	}

	public void move(Direction dir) {
		undoStack.push(getState());//almacenamos en la pila de undo el estado actual del talero
		MoveResults mov= new MoveResults();
		mov=this.board.executeMove(dir,currentRules);
		if(mov.isMoved()) {
			score+=mov.getPoints();
			high=currentRules.getWinValue(board);
			currentRules.addNewCell(board, myRandom);
			redoStack.vaciar();//Al hacer un movimiento nuevo (distinto de undo y redo)se borra la pila del redo entera
		}
		if(currentRules.lose(board)) {
			lost=true;
			setFin(true);
		}

	}

	public void reset() {
		this.board = new Board(currentSize);
		currentRules.initBoard(board, currentInitCells, myRandom);
		score=0;
		high=currentRules.getWinValue(board);
	}

	public boolean win() {
		if(currentRules.win(board))
			setFin(true);
		return currentRules.win(board);

	}

	public boolean lost() {
		return lost;
	}

	public String toString() {
		return this.board + "Best Value: " + high + " Score: " + score + "\n";
	}
	public boolean isFin() {
		return fin;
	}

	public void setFin(boolean fin) {
		this.fin = fin;
	}

	public void undo(){
		try {
			GameState anterior= undoStack.pop();
			redoStack.push(getState());
			setState(anterior);
			System.out.println("Undoing one move...\n");

		}catch(EmptyStackException e){
			System.out.println("Undo is not available...\n");
		}

	}

	public void redo(){
		try {
			GameState siguiente=redoStack.pop();
			undoStack.push(getState());
			setState(siguiente);
			System.out.println("Redoing one move... \n");

		}catch(EmptyStackException e) {
			System.out.println("Nothing to redo\n");
		}
	}

	public GameState getState(){
		GameState game = new GameState(board.getState(), score);
		return game;
	} 

	public void setState(GameState aState){
		Board b = new Board(currentSize);
		b.setState(aState.getBoardState());
		board=b;
		score=aState.getScore();
	}

	public void vaciarPilas() {
		redoStack.vaciar();
		undoStack.vaciar();
	}
	
	public void changeGame(GameType rules, int randomSeed, int boardSize, int initCells) {

		this.currentRules=rules.getRules();
		this.currentSize=boardSize;
		this.currentInitCells=initCells;
		this.tipo=GameType.parse(rules.externalise());
		reset();
	}

	public void store(BufferedWriter bw) {		
		try {
			board.store(bw);
			bw.newLine();
			String fin= currentInitCells + " " + score + " " + tipo.externalise();
			bw.write(fin);

		}catch(IOException e) {
			e.printStackTrace();
		}

	}

	public GameType load(String archivo, BufferedReader br)throws IncorrectFileException{
		//Guardamos tablero, tipo, dim, celdasIniciales
		
		GameType tipoJuego=tipo;
		GameState aState=getState();
		int dim=currentSize;
		int cells=currentInitCells;
		GameRules rules =currentRules;
		long seed=currentSeed;
		try {
			
			br.readLine();
			board.load(archivo, br);
			br.readLine();
			String ultimaLinea=br.readLine();
			String cadena []=ultimaLinea.split("\\s+");
			currentSize=board.getSize();
			if(Integer.parseInt(cadena[0])<currentSize*currentSize)
				currentInitCells=Integer.parseInt(cadena[0]);
			else
				throw new IncorrectFileException("Load failed: invalid file format");
			score=Integer.parseInt(cadena[1]);
			String tipoJ=cadena[2];
			tipo=GameType.parse(tipoJ);
			if(tipo==null) 
				throw new IncorrectFileException("Load failed: invalid file format");
			else{
				currentRules=tipo.getRules();
				vaciarPilas();
			}

		}
		catch(NullPointerException e) {
			setGame(dim,cells,seed,tipoJuego,rules,aState);
			throw e;
			
		}
		catch(NumberFormatException e) {
			setGame(dim,cells,seed,tipoJuego,rules,aState);
			throw e;
		}
		catch(IncorrectFileException e) {
			setGame(dim,cells,seed,tipoJuego,rules,aState);
			throw e;

		}
		catch(Exception e) {
			setGame(dim,cells,seed,tipoJuego,rules,aState);
			e.getMessage();
		}
		
		return tipo;

	}
	void setGame(int dim,int celdas, long seed, GameType tipoJuego, GameRules rules, GameState aState) {
		tipo=tipoJuego;
		currentSize=dim;
		currentInitCells=celdas;
		currentRules=rules;
		currentSeed=seed;
		setState(aState);
		
	}
}