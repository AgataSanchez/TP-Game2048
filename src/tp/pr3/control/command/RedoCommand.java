package tp.pr3.control.command;

import tp.pr3.logic.multigames.Game;

public class RedoCommand extends NoParamsCommand{
	private static String nombreComando= "redo";
	private static String helpText ="redo the last undone command.\n";
	
	public RedoCommand() {
		super(nombreComando, helpText);

	}

	@Override
	public boolean execute(Game game) {
		game.redo();
		return true;
	}

	
}
