package tp.pr3.control.command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import tp.pr3.exception.CommandParseException;
import tp.pr3.logic.multigames.Game;
import tp.pr3.util.MyStringUtils;

public class SaveCommand extends Command {
	private static String nombreComando= "save";
	private static String helpText ="Saving new game. \n";

	private String nombre;
	private boolean filename_confirmed;
	public static final String filenameInUseMsg= "The file already exists ; do you want to overwrite it ? (Y/N)";
	
	public SaveCommand() {
		super(nombreComando, helpText);
		this.filename_confirmed=false;
		this.nombre="";
	}

	@Override
	public boolean execute(Game game) {

		String introduccion="This file stores a saved 2048 game";		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombre))) {
			bw.write(introduccion);
			bw.newLine();
			bw.newLine();
			game.store(bw);
			System.out.println("Game successfully saved to file; use load command to reload it.");
		}catch(IOException e) {
			e.printStackTrace();
		}
		return false;//Para que no muestre el tablero
	}

	@Override
	public Command parse(String[] commandWords, Scanner in) throws CommandParseException {
		
		if(commandWords[0].equalsIgnoreCase(nombreComando)) {
			if(commandWords.length==1)
				throw new CommandParseException("Save");
			else if(commandWords.length>2)
				throw new CommandParseException("Invalid filename: the filename contains spaces");
			else{
				this.nombre=confirmFileNameStringForWrite(commandWords[1],in);
				return this;
			}
		}
		else
			return null;
	}
	
	private String confirmFileNameStringForWrite(String filenameString, Scanner in) throws CommandParseException {
		String loadName = filenameString;
		filename_confirmed = false;
		while (!filename_confirmed) {
			if (MyStringUtils.validFileName(loadName)) {
				File file = new File(loadName);
				if (! file . exists () )
					filename_confirmed = true;
				else {
					loadName = getLoadName(filenameString, in);
				}
			} else
				throw new CommandParseException("Invalid filename: the filename contains invalid characteres");
		}
		return loadName;
	}
		
	public String getLoadName(String filenameString, Scanner in) {
		String newFilename = null;
		boolean yesOrNo = false;
		while (!yesOrNo) {
			//try
			System.out.print(filenameInUseMsg + ": ");
			String[] responseYorN = in.nextLine().toLowerCase().trim().split("\\s+");
			if (responseYorN.length == 1) {
				switch (responseYorN[0]) {
				case "y":
					yesOrNo = true;
					newFilename=filenameString;
					filename_confirmed=true;
					break;
				case "n":
					yesOrNo = true;
					System.out.print("Please enter another filename: " );
					newFilename=in.nextLine();
					break;
				default:
					System.out.println("Please answer 'Y' or 'N'");
					break;
				}
			} else {
				System.out.println("Please answer only 'Y' or 'N'");
			}
		}
		return newFilename;
	}
}
