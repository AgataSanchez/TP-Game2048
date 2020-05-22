package tp.pr3.control.command;

import tp.pr3.logic.multigames.Game;

public class ResetCommand extends NoParamsCommand {

	private static String nombreComando= "reset";
	private static String helpText ="start a new game.\r\n";
	
	public ResetCommand(){
		super(nombreComando, helpText);
	}
	

	public boolean execute(Game game) {
		game.reset();
		return true;
	}


}
