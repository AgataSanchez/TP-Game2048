package tp.pr3.logics;

import java.util.EmptyStackException;

public class GameStateStack {
	private static final int CAPACITY = 20;
	private GameState[] buffer = new GameState[CAPACITY+1];
	private int cont;
	private int indice;

	public GameStateStack() {
		super();
		this.cont = 0;
		this.indice = 0;
	}

	public GameState pop(){ // Devuelve el ultimo estado almacenado
		if(isEmpty())
			throw new EmptyStackException();
		GameState game= null;
		if(cont==0)//Si cont llega a 0 coge la posicion anterior que es CAPACITY
			cont=CAPACITY;
		else
			cont--;

		game=buffer[cont];
		return game;
	}

	public void push(GameState state){// Almacena un nuevo estado
		buffer[cont]=state;
		if(!isFull(cont)) 
			cont++;
		else
			cont=0;

		if(isEmpty()) {//Si cont==indice y indice=CAPACITY el indice se vuelve a inicializar
			if(isFull(indice))
				indice=0;
			else
				indice++;
		}
			
	} 

	public boolean isEmpty(){//vacia
		return cont==indice;
	}
	
	public boolean isFull(int param) {//Array lleno
		return param==CAPACITY;		
	}
	public void vaciar() {
		indice=0;
		cont=0;
	}
}
