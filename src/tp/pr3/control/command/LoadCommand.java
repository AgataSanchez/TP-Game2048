package tp.pr3.control.command;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import tp.pr3.exception.CommandParseException;
import tp.pr3.exception.IncorrectFileException;
import tp.pr3.logic.multigames.Game;
import tp.pr3.logics.GameType;
import tp.pr3.util.MyStringUtils;

public class LoadCommand extends Command{
	private static String nombreComando= "load";
	private static String helpText ="load a new game. \n";
	private String nombreArch;
	
	public LoadCommand() {
		super(nombreComando, helpText);
		this.nombreArch="";
	}

	@Override
	public boolean execute(Game game) {
		GameType tipoJuego=null;
		
		try(BufferedReader br=new BufferedReader(new FileReader(nombreArch))) {
			tipoJuego=game.load(nombreArch, br);
				System.out.println("Game successfully loaded from file: " + tipoJuego.toString());
			return true;
		}
		catch(IncorrectFileException e) {
			e.getMessage();
			return false;
		}
		catch(FileNotFoundException e) {		
			System.out.println("File not found");
			return false;
		}
		catch (IOException e) {
			System.out.println("Game unsuccessfully loaded from file: " + nombreArch);
			return false;
		}
	}

	@Override
	public Command parse(String[] commandWords, Scanner in)throws CommandParseException {
		
		if(commandWords[0].equalsIgnoreCase(nombreComando)) {
			if(commandWords.length==1)
				throw new CommandParseException("Load");
			else if(commandWords.length>2)
				throw new CommandParseException("Invalid filename: the filename contains spaces");
			else{
				if (MyStringUtils.validFileName(commandWords[1])) {
					nombreArch=commandWords[1];
				}else
					throw new CommandParseException("Invalid filename: the filename contains invalid characteres");
				
				return this;
			}
		}
		else
			return null;
	}
	

}
