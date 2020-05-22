package tp.pr3.control.command;

import tp.pr3.logic.multigames.Game;

public class HelpCommand extends NoParamsCommand{

	private static String nombreComando= "help";
	private static String helpText ="print this help message.\n";
	
	public HelpCommand(){
		super(nombreComando, helpText);
	}

	@Override
	public boolean execute(Game game) {
		System.out.println(CommandParser.commandHelp());
		return false;//Para que no muestre el tablero
	}


}
