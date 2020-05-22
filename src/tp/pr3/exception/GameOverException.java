package tp.pr3.exception;

import tp.pr3.logic.multigames.Game;

public class GameOverException extends Exception {
	
	public GameOverException(Game game) {
		if(game.win())
			System.out.println("Has ganado");
		else if(game.lost())
			System.out.print("Has perdido");
	}
	
}
