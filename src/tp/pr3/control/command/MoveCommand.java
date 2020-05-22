package tp.pr3.control.command;

import java.util.Scanner;

import tp.pr3.exception.CommandParseException;
import tp.pr3.logic.multigames.Game;
import tp.pr3.logics.Direction;

public class MoveCommand extends Command {

	private Direction dir;
	private boolean correcta=true;
	private static String nombreComando= "move <direction>";
	private static String helpText ="execute a move in one of the directions: up, down, left, right. \n";

	public MoveCommand(){
		super(nombreComando, helpText);
		this.dir=null;
	}

	public boolean execute(Game game){
		if(correcta) {
			game.move(this.dir);
			return true;//Para que muestre el tablero
		}else 
			return false;//Para que no muestre el tablero

	}


	public Command parse(String[] commandWords, Scanner in) throws CommandParseException {
		correcta=true;

		if(commandWords[0].equalsIgnoreCase(this.commandName)){
			if(commandWords.length==1)
				throw new CommandParseException("Move");
			else {
				if(commandWords[1].equalsIgnoreCase("left"))
					dir=Direction.LEFT;
				else if(commandWords[1].equalsIgnoreCase("right"))
					dir=Direction.RIGHT;
				else if(commandWords[1].equalsIgnoreCase("up"))
					dir=Direction.UP;
				else if(commandWords[1].equalsIgnoreCase("down"))
					dir=Direction.DOWN;
				else
					throw new CommandParseException("Unknow direction for move command");
			}
		}
		else
			return null;

		return this;

	}
}


