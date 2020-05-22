package tp.pr3.control.command;

import java.util.Scanner;

public abstract class NoParamsCommand extends Command{


	public NoParamsCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
	}

	public Command parse(String[] commandWords, Scanner in){
		
		if(commandWords[0].equalsIgnoreCase(this.commandName))
			return this;
			
		else
			return null;
	}



}
