package tp.pr3.control.command;

import tp.pr3.logic.multigames.Game;

public class ExitCommand extends NoParamsCommand {
	private static String nombreComando= "exit";
	private static String helpText ="terminate the program. \n";
	
	public ExitCommand(){
		super(nombreComando, helpText);
	}

	@Override
	public boolean execute(Game game) {
		System.out.println("Game over...");
		game.setFin(true);
		return false;//Para que no muestre el tablero
		
	}

}
