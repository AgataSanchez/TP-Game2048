package tp.pr3.control.command;

import java.util.Random;
import java.util.Scanner;

import tp.pr3.exception.CommandParseException;
import tp.pr3.exception.OtherExceptions;
import tp.pr3.logic.multigames.*;
import tp.pr3.logics.GameType;


public class PlayCommand extends Command{

	private static String nombreComando= "play <game>";
	private static String helpText ="start a new game of one of the game types: original, fib, inverse. \n";
	protected static int randomSeedDef= new Random().nextInt(1000), boardSizeDef=4, initCellsDef=2;
	protected int randomSeed, boardSize, initCells;
	protected GameType gameType;

	public PlayCommand(){
		super(nombreComando, helpText);
		this.gameType = null;
		this.randomSeed=randomSeedDef;
		this.boardSize=boardSizeDef;
		this.initCells=initCellsDef;
	}

	public boolean execute(Game game) {
		if(gameType == GameType.FIB || gameType == GameType.ORIG || gameType == GameType.INV) {
			game.changeGame(gameType, randomSeed, boardSize, initCells);
			return true;
		}
		else
			return false;
	}

	private int comprobar(String intro, String mensaje, int valorDefecto, Scanner in) {
		boolean ok = false;
		int valor = valorDefecto;
		String []num = in.nextLine().split("\\s+") ;
		while(!ok) {
			try {
				if(num.length > 1) {
					throw new OtherExceptions("Please provide a single positive integer or press return");
				}else if(num[0].equals("")) {
					ok = true;
					System.out.println("Using default " + mensaje + ": " + valorDefecto);
				}else if(Integer.parseInt(num[0]) < 0) {
					throw new OtherExceptions("The " + mensaje + " must be positive");
				}else {
					valor = Integer.parseInt(num[0]);
					ok = true;
				}
			}catch(OtherExceptions e) {
				e.getMessage();
				System.out.print(intro + mensaje+ ": ");
				num = in.nextLine().split("\\s+") ;
			}catch(NumberFormatException e) {
				System.out.println("The "+ mensaje+ " must be a number.");
				System.out.print(intro + mensaje+ ": ");
				num = in.nextLine().split("\\s+") ;
			}
		}
		return valor;
	}



	public Command parse(String[] commandWords, Scanner in) throws CommandParseException {

		if(commandWords[0].equalsIgnoreCase(this.commandName)){

			String introducir = "Please enter the ";

			if(commandWords.length==1)
				throw new CommandParseException("Play");
			else {
				gameType=GameType.parse(commandWords[1]);
				if(gameType!=null) {
					System.out.println("*** You have chosen to play the game: " + gameType.toString() + " ***");
					System.out.print(introducir + "size of the board: ");
					boardSize = comprobar(introducir, "size of the board", boardSizeDef, in);

					System.out.print(introducir + "number of initial cells: ");
					initCells = comprobar(introducir,"number of initial cells", initCellsDef, in);

					System.out.print(introducir + "size for the pseudo-random number generator: ");
					randomSeed = comprobar(introducir, "size for the pseudo-random number genertor", randomSeedDef, in);

					if(initCells >= boardSize*boardSize)
						throw new CommandParseException("The number of initial cells must be less than the number of cells on the board.");

				}else
					throw new CommandParseException("Unknown game type for play command");
			}
		}
		else
			return null;
		return this;

	}
}
