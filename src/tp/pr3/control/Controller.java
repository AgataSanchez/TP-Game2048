package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.control.command.Command;
import tp.pr3.control.command.CommandParser;
import tp.pr3.exception.CommandParseException;
import tp.pr3.exception.GameOverException;
import tp.pr3.logic.multigames.Game;

public class Controller {
	private Game game;
	private Scanner in;


	public Controller(Game game, Scanner in) {
		super();
		this.game = game;
		this.in = in;

	}

	public void run() {
		System.out.println(this.game.toString());
		while(!game.isFin()) {
			try {
				System.out.print("Command > ");
				String [] ask=in.nextLine().split("\\s+");
				Command command = CommandParser.parseCommand(ask, in);
				if(command!=null) {	
					if(command.execute(game))
						System.out.print(game.toString());
				}
				else
					throw new CommandParseException("Unknown command. Use ’help’ to see the available commands.");
			}
			catch(CommandParseException e) {
				e.getMessage();
			}
			finally {
				new GameOverException(game);
			}

		}
	}
}