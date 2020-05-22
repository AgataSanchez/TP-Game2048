package tp.pr3.control.command;

import java.util.Scanner;

import tp.pr3.exception.CommandParseException;
import tp.pr3.logic.multigames.Game;

public abstract class Command {

	private String helpText;
	private String commandText;
	protected final String commandName;
	
	public Command(String commandInfo, String helpInfo){
		commandText = commandInfo;
		helpText = helpInfo;
		String[] commandInfoWords = commandText.split("\\s+");
		commandName = commandInfoWords[0];

	}
	
	public abstract boolean execute(Game game);
	public abstract Command parse(String[] commandWords, Scanner in)throws CommandParseException;
	
	public String helpText(){
		return " " + commandText + ": " + helpText;
	}
}

