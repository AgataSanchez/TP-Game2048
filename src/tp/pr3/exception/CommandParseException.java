package tp.pr3.exception;

import tp.pr3.logics.Direction;
import tp.pr3.logics.GameType;

public class CommandParseException extends Exception {

	public CommandParseException(String mensaje) {
		String def=" must be followed by a ";
		if(mensaje.equals("Play"))
			System.out.println(mensaje+ def + "game type: " + GameType.externaliseAll());
		else if(mensaje.equals("Move"))
			System.out.println(mensaje+ def + "direction: " + Direction.externaliseAll());
		else if(mensaje.equals("Save"))
			System.out.println(mensaje+ def + "filename");
		else if(mensaje.equals("Load"))
			System.out.println(mensaje+ def + "filename");
		else System.out.println(mensaje);
		
	}

}
