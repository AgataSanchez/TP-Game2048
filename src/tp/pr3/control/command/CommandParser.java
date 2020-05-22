package tp.pr3.control.command;

import java.util.Scanner;

import tp.pr3.exception.CommandParseException;

public class CommandParser {
	private static Command[] availableCommands = {
			new HelpCommand(), 
			new ResetCommand(),
			new ExitCommand(), 
			new MoveCommand(),
			new UndoCommand(),
			new RedoCommand(),
			new PlayCommand(),
			new SaveCommand(),
			new LoadCommand()
		};
	
	public static Command parseCommand (String[] commandWords, Scanner in) throws CommandParseException{
		Command lista= null;
		for(Command com: availableCommands){
			lista=com.parse(commandWords, in);
			if(lista!=null)
				 return lista;					
		}
		
		return lista;
		
	}
	public static String commandHelp () {
		String cadena="";
		for(Command com: availableCommands){
			cadena+=com.helpText();
		}
		
		return cadena;
	}
	
}
