package tp.pr3.control.command;

import tp.pr3.logic.multigames.Game;

public class UndoCommand extends NoParamsCommand {
	
	private static String nombreComando= "undo";
	private static String helpText ="undo the last command. \n";
	
	public UndoCommand() {
		super(nombreComando, helpText);
	}

	@Override
	public boolean execute(Game game) {
		game.undo();
		return true;
	}

}
